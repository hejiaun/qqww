package com.example.module_practice.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.module_practice.PracticeSongListView;
import com.example.module_practice.R;
import com.example.module_practice.activity.PracticeRoomActivity;
import com.example.module_practice.activity.SingModeActivity;
import com.flyco.tablayout.CommonTabLayout;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.entity.SongListEntity;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  目标歌曲Fragment
 */
public class TargetFragment extends BaseFragment {
    PracticeSongListView lvSongList;
    CommonTabLayout ctl;
    int tabVisibility = View.GONE;
    private ArrayList<SongListEntity> songListEntities = new ArrayList<>();

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        lvSongList = view.findViewById(R.id.lv_songList);
        ctl = view.findViewById(R.id.ctl);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        lvSongList.setData(songListEntities, ((PracticeRoomActivity) getActivity()).getSelectSongEntities());
        lvSongList.setItemButtonClickListener(new PracticeSongListView.ItemButtonClickListener() {
            @Override
            public void selectSong(SongListEntity songListEntity, TextView textView) {
                if (songListEntity.isSelect()) {
                    //设置歌曲实体为未选中并移除缓存中的实体
                    songListEntity.setSelect(false);
                    songListEntities.remove(songListEntity);
                } else {
                    //设置歌曲实体为选中并增加缓存中的实体
                    songListEntity.setSelect(true);
                    songListEntities.add(songListEntity);
                }
                //刷新所有列表
                ((PracticeRoomActivity) getActivity()).notifyAllListDataChange();
            }

            @Override
            public void singSong(SongListEntity songListEntity) {
                startActivity(new Intent(getContext(), SingModeActivity.class));
            }
        });
        lvSongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        ctl.setVisibility(tabVisibility);
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    @Override
    public int initLayout() {
        return R.layout.fragment_practice_list;
    }

    /**
     * 添加歌曲
     *
     * @param songListEntity
     */
    public void addSong(SongListEntity songListEntity) {
        songListEntities.add(songListEntity);
    }

    /**
     * 移除已经添加的歌曲
     *
     * @param songListEntity
     */
    public void reomveSong(SongListEntity songListEntity) {
        songListEntities.remove(songListEntity);
    }

    /**
     * 列表数据更新
     */
    public void notifyListDataChange() {
        if (lvSongList != null) {
            lvSongList.notifyDataChange();
        }
    }

    /**
     * 获取歌曲列表实体
     */
    public ArrayList<SongListEntity> getSongListEntities() {
        return songListEntities;
    }
}
