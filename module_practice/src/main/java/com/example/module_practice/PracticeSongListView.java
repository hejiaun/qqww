package com.example.module_practice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import example.common_base.entity.SongListEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  练歌房自定义封装列表
 */
public class PracticeSongListView extends ListView {
    ItemButtonClickListener itemButtonClickListener;
    private Context context;
    private MyAdapter myAdapter;
    private ArrayList<SongListEntity> songListEntities;
    private ArrayList<SongListEntity> selectSongListEntities;

    public PracticeSongListView(Context context) {
        this(context, null);
    }

    public PracticeSongListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PracticeSongListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * 设置填充内容
     */
    public void setData(ArrayList<SongListEntity> songListEntities, ArrayList<SongListEntity> selectSongListEntities) {
        this.songListEntities = songListEntities;
        this.selectSongListEntities = selectSongListEntities;
        myAdapter = new MyAdapter();
        setAdapter(myAdapter);
    }

    /**
     * 更新数据
     */
    public void notifyDataChange() {
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return songListEntities.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = View.inflate(context, R.layout.item_song_practice, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final SongListEntity songListEntity = songListEntities.get(i);
            //设置封面
            viewHolder.ivSongFace.setImageResource(songListEntity.getCoverImage());
            //设置歌曲名
            viewHolder.tvSongName.setText(songListEntity.getSongName());
            //设置星级
            viewHolder.ratingBar.setRating(3);
            //设置歌曲详情  歌手、歌曲大小 等
            String details;
            details = songListEntity.getSongSize();
            details += songListEntity.getSinger();
            viewHolder.tvSongMessageDetial.setText(details);
            //设置按钮状态（点击、未点击）
            if (songListEntity.isSelect()) {
                setSelectState(viewHolder.btnSelect);
                viewHolder.btnSelect.setText("第" + getSongEntityIndex(songListEntity) + "目标");
            } else {
                setUnSelectState(viewHolder.btnSelect);
            }

            //设置 item中的button点击接口
            viewHolder.btnSelect.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemButtonClickListener.selectSong(songListEntity, viewHolder.btnSelect);
                }
            });
            viewHolder.btnSing.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemButtonClickListener.singSong(songListEntity);
                }
            });
            return view;
        }

        class ViewHolder {
            ImageView ivSongFace;
            TextView tvSongName;
            TextView tvSongMessageDetial;
            RatingBar ratingBar;
            TextView btnSelect;
            TextView btnSing;

            ViewHolder(View view) {
                ivSongFace = view.findViewById(R.id.iv_songFace);
                tvSongName = view.findViewById(R.id.tv_songName);
                tvSongMessageDetial = view.findViewById(R.id.tv_songMessageDetial);
                ratingBar = view.findViewById(R.id.ratingBar);
                btnSelect = view.findViewById(R.id.btn_select);
                btnSing = view.findViewById(R.id.btn_sing);
            }
        }
    }

    public interface ItemButtonClickListener {
        /**
         * 列为目标歌曲
         *
         * @param songListEntity
         */
        void selectSong(SongListEntity songListEntity, TextView textView);

        /**
         * 演唱歌曲
         *
         * @param songListEntity
         */
        void singSong(SongListEntity songListEntity);
    }

    /**
     * 设置item中的button点击
     *
     * @param itemButtonClickListener
     */
    public void setItemButtonClickListener(ItemButtonClickListener itemButtonClickListener) {
        this.itemButtonClickListener = itemButtonClickListener;
    }

    /**
     * 设置选中歌曲状态
     *
     * @param textView
     */
    private void setSelectState(TextView textView) {
        Drawable drawableLeft = getResources().getDrawable(R.drawable.liangefang_icon_mubiao_s);
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        textView.setCompoundDrawables(drawableLeft, null, null, null);
        textView.setBackgroundResource(R.drawable.shape_border_purple_round);
    }

    /**
     * 设置未选中歌曲状态
     *
     * @param textView
     */
    private void setUnSelectState(TextView textView) {
        Drawable drawableLeft = getResources().getDrawable(R.drawable.liangefang_icon_mubiao_n);
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        textView.setCompoundDrawables(drawableLeft, null, null, null);
        textView.setBackgroundResource(R.drawable.shape_border_white_round_4dp);
        textView.setText("列为目标");
    }

    /**
     * 获取已点歌曲实体序号
     */
    private int getSongEntityIndex(SongListEntity songListEntity) {
        if (selectSongListEntities != null) {
            for (int i = 0; i < selectSongListEntities.size(); i++) {
                if (songListEntity.equals(selectSongListEntities.get(i))) {
                    return i + 1;
                }
            }
        }
        return -1;
    }
}
