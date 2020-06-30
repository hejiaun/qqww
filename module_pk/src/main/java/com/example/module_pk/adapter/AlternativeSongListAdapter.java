package com.example.module_pk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module_pk.R;

import java.util.ArrayList;

import example.common_base.entity.SongListEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  备选歌曲列表适配器
 */
public class AlternativeSongListAdapter extends BaseAdapter {
    Context context;
    ArrayList<SongListEntity> songListEntities;

    public AlternativeSongListAdapter(Context context, ArrayList<SongListEntity> songListEntities) {
        this.context = context;
        this.songListEntities = songListEntities;
    }

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
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_alternative_song, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvSongName;
        RelativeLayout rlBg;

        ViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
            tvSongName = view.findViewById(R.id.tv_songName);
            rlBg = view.findViewById(R.id.rl_bg);
        }
    }
}
