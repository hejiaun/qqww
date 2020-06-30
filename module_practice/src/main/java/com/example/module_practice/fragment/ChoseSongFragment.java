package com.example.module_practice.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.module_practice.PracticeSongListView;
import com.example.module_practice.R;
import com.example.module_practice.activity.PracticeRoomActivity;
import com.example.module_practice.activity.SingModeActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.entity.SongListEntity;
import example.common_base.entity.TabEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  已点歌曲Framgnet
 */
public class ChoseSongFragment extends BaseFragment {

    PracticeSongListView lvSongList;
    CommonTabLayout ctl;
    int tabVisibility = View.GONE;
    private ArrayList<SongListEntity> songListEntities;
    private ArrayList<CustomTabEntity> tabEntities;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        ctl = view.findViewById(R.id.ctl);
        lvSongList = view.findViewById(R.id.lv_songList);
    }

    /**
     * 更新列表数据
     */
    public void notifyListDataChange() {
        if (lvSongList != null) {
            lvSongList.notifyDataChange();
        }
    }


    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        //添加列表歌曲实体
        songListEntities = new ArrayList<>();
        songListEntities.add(new SongListEntity("水星记", "4M", "``", R.drawable.example, 3));
        songListEntities.add(new SongListEntity("保留", "4M", "``", R.drawable.example, 3));
        songListEntities.add(new SongListEntity("流浪记", "4M", "``", R.drawable.example, 3));
        songListEntities.add(new SongListEntity("For U", "4M", "``", R.drawable.example, 3));

        //添加选择器数据实体
        tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("全部"));
        tabEntities.add(new TabEntity("男声"));
        tabEntities.add(new TabEntity("女声"));
        tabEntities.add(new TabEntity("初级"));
        tabEntities.add(new TabEntity("中级"));
        tabEntities.add(new TabEntity("高级"));
        tabEntities.add(new TabEntity("热门"));

        ctl.setVisibility(tabVisibility);
        ctl.setTabData(tabEntities);

        lvSongList.setItemButtonClickListener(new PracticeSongListView.ItemButtonClickListener() {
            public void selectSong(SongListEntity songListEntity, TextView textView) {
                if (songListEntity.isSelect() == false) {
                    songListEntity.setSelect(true);
                    //添加歌曲到目标歌曲
                    ((PracticeRoomActivity) getActivity()).addSong(songListEntity);
                } else {
                    songListEntity.setSelect(false);
                    //移除歌曲
                    ((PracticeRoomActivity) getActivity()).removeSong(songListEntity);
                }
                //刷新列表
                lvSongList.notifyDataChange();
            }

            public void singSong(SongListEntity songListEntity) {
                startActivity(new Intent(getContext(), SingModeActivity.class));
            }
        });
        //添加歌曲实体
        lvSongList.setData(songListEntities, ((PracticeRoomActivity) getActivity()).getSelectSongEntities());
        ctl.setTabData(tabEntities);

    }


    /**
     * 创建presenter
     *
     * @return presenter
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
     * 设置tab选择器可见性
     */
    public void setTabVisibility(int tabVisibility) {
        this.tabVisibility = tabVisibility;
    }

}