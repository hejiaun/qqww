package com.example.module_pk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.module_pk.R;

import java.util.ArrayList;

import example.common_base.entity.SongListEntity;


/**
 * Author: HeJiaJun
 * Date:
 * Description:搜索歌曲列表适配器
 */
public class SearchSongAdapter extends BaseAdapter {
    Context context;
    ArrayList<SongListEntity> arrayList;

    public SearchSongAdapter(Context context, ArrayList<SongListEntity> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_song_practice, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvSongName.setText(arrayList.get(i).getSongName());
        viewHolder.btnSelect.setVisibility(View.GONE);
        viewHolder.btnSing.setVisibility(View.GONE);
        viewHolder.btnSing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
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

