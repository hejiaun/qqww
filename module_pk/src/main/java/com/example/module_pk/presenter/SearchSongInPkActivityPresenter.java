package com.example.module_pk.presenter;

import com.example.module_pk.model.SearchSongInPkActivityModel;
import com.example.module_pk.view_interface.ISearchSongInPkActivityView;

import java.util.ArrayList;
import java.util.Collection;

import cn.vfighter.songlib.bean.Accompaniment;
import cn.vfighter.songlib.param.SearchByKeyWordParam;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.songlib.AccompanimentModel;
import rx.functions.Action1;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class SearchSongInPkActivityPresenter extends BasePresenter<ISearchSongInPkActivityView> {

    private final SearchSongInPkActivityModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iSearchSongInPkActivityView View层接口
     */
    public SearchSongInPkActivityPresenter(ISearchSongInPkActivityView iSearchSongInPkActivityView) {
        super(iSearchSongInPkActivityView);
        model = new SearchSongInPkActivityModel();
    }

    /**
     * 根据关键字搜索伴奏
     *
     * @param keyWord 关键字
     */
    public void search(String keyWord) {
        SearchByKeyWordParam keyWordParam = new SearchByKeyWordParam();
        keyWordParam.setPageLength(10);
        keyWordParam.setPageIndex(0);
        keyWordParam.setKeyWord(keyWord);
        new AccompanimentModel()
                .getPubKeyWordAccompanimentByName(keyWordParam, new Action1<Collection<Accompaniment>>() {
                    @Override
                    public void call(Collection<Accompaniment> accompaniments) {
                        if (accompaniments == null) return;
                        ArrayList<Accompaniment> accompanimentList = (ArrayList<Accompaniment>) accompaniments;
                        for (int i = 0; i < accompanimentList.size(); i++) {
                            getView().getAdapter().addData(accompanimentList.get(i));
                        }
                    }
                });
    }
}
