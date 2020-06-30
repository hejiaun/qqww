package com.example.module_home.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_home.adapter.SearchSectionAdapter;
import com.example.module_home.model.SearchInHomeActivityModel;
import com.example.module_home.view_interface.ISearchInHomeView;

import java.util.ArrayList;
import java.util.Collection;

import cn.vfighter.songlib.bean.Accompaniment;
import cn.vfighter.songlib.param.SearchByKeyWordParam;
import example.common_base.base.BasePresenter;
import example.common_base.entity.AccompanimentEntity;
import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.TitleMultiplyentity;
import example.common_base.net.model.songlib.AccompanimentModel;
import example.common_base.util.DensityUtils;
import rx.functions.Action1;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  主页搜索Activity服务
 */
public class SearchInHomePresenter extends BasePresenter<ISearchInHomeView> {
    private SearchSectionAdapter adapter;
    private ArrayList<MyMultiplyEntity> accompanimentEntities = new ArrayList<>();
    private ArrayList<MyMultiplyEntity> userEntities = new ArrayList<>();
    private ArrayList<MyMultiplyEntity> matchEntities = new ArrayList<>();
    private ArrayList<MyMultiplyEntity> workEntities = new ArrayList<>();
    private ArrayList<MyMultiplyEntity> allEntities = new ArrayList<>();
    private RecyclerView.ItemDecoration itemDecoration;
    private final SearchInHomeActivityModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iSearchInHomeView View层接口
     */
    public SearchInHomePresenter(ISearchInHomeView iSearchInHomeView) {
        super(iSearchInHomeView);
        model = new SearchInHomeActivityModel();
    }

    /**
     * 搜索伴奏
     *
     * @param keyword 搜索的关键字
     */
    public void requestAccompanimentData(String keyword) {
        SearchByKeyWordParam searchByKeyWordParam = new SearchByKeyWordParam();
        searchByKeyWordParam.setKeyWord(keyword);
        searchByKeyWordParam.setPageIndex(0);
        searchByKeyWordParam.setPageLength(10);
        new AccompanimentModel()
                .getPubKeyWordAccompanimentByName(searchByKeyWordParam, new Action1<Collection<Accompaniment>>() {
                    @Override
                    public void call(Collection<Accompaniment> accompaniments) {
                        ArrayList accompanimentList = (ArrayList<Accompaniment>) accompaniments;
                        if (accompanimentList == null) return;
                        for (int i = 0; i < accompanimentList.size(); i++) {
                            accompanimentEntities.clear();
                            accompanimentEntities.add(new AccompanimentEntity((Accompaniment) accompanimentList.get(i)));
                            completeRequest();
                        }
                    }
                });
    }

    /**
     * 搜索用户
     *
     * @param keyword 搜索的关键字
     */
    public void requestUserData(String keyword) {
        model.getAccompanimentData(keyword, new Action1<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(ArrayList<MyMultiplyEntity> data) {
                userEntities.clear();
                userEntities.addAll(data);
            }
        });
    }

    /**
     * 搜索用户
     *
     * @param keyword 搜索的关键字
     */
    public void requestMatchtData(String keyword) {
        model.getAccompanimentData(keyword, new Action1<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(ArrayList<MyMultiplyEntity> data) {
                matchEntities.clear();
                matchEntities.addAll(data);
            }
        });
    }

    /**
     * 搜索用户
     *
     * @param keyword 搜索的关键字
     */
    public void requestWorkData(String keyword) {
        model.getAccompanimentData(keyword, new Action1<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(ArrayList<MyMultiplyEntity> data) {
                workEntities.clear();
                workEntities.addAll(data);
            }
        });
    }

    //获取全部数据
    public void initAll() {
        addTitle(accompanimentEntities.size(), "伴奏");
        getThreeOrLess(accompanimentEntities);
        addTitle(userEntities.size(), "用户");
        getThreeOrLess(userEntities);
        addTitle(matchEntities.size(), "比赛");
        getThreeOrLess(matchEntities);
        addTitle(workEntities.size(), "作品");
        getThreeOrLess(workEntities);
    }

    //获取伴奏数据
    public void initAccompanimentEntities() {
        for (int i = 0; i < 8; i++) {
            accompanimentEntities.add(new MyMultiplyEntity(MyMultiplyEntity.ACCOMPANIENT));
        }
    }

    public void completeRequest() {
        initAll();
        adapter.notifyDataSetChanged();
    }

    //获取用户数据
    public void initUserEntities() {
        for (int i = 0; i < 2; i++) {
            userEntities.add(new MyMultiplyEntity(MyMultiplyEntity.USER));
        }
    }

    //获取比赛数据
    public void initMatchEntities() {
        for (int i = 0; i < 8; i++) {
            matchEntities.add(new MyMultiplyEntity(MyMultiplyEntity.MATCH));
        }
    }

    //获取作品数据
    public void initWorkEntities() {
        for (int i = 0; i < 8; i++) {
            workEntities.add(new MyMultiplyEntity(MyMultiplyEntity.WORK));
        }
    }

    /**
     * 获取集合前3条或者小于3条实体数据
     */
    public void getThreeOrLess(ArrayList<MyMultiplyEntity> entities) {
        int i = 0;
        if (entities.size() >= 3) {
            i = 3;
        } else {
            i = entities.size();
        }
        for (int j = 0; j < i; j++) {
            allEntities.add(entities.get(j));
        }
    }

    /**
     * 添加标题
     *
     * @param size
     * @param title
     */
    public void addTitle(int size, String title) {
        boolean isMore;
        if (size > 3) {
            isMore = true;
        } else {
            isMore = false;
        }
        allEntities.add(new TitleMultiplyentity(title, isMore));
    }

    public SearchSectionAdapter getAdapter() {
        if (adapter == null) {
            adapter = new SearchSectionAdapter(new ArrayList<MyMultiplyEntity>());
            adapter.setNewData(allEntities);
        }
        return adapter;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        if (itemDecoration == null) {
            itemDecoration = new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    MyMultiplyEntity myMultiplyEntity = ((SearchSectionAdapter) parent.getAdapter()).getItem((parent.getChildAdapterPosition(view)));
                    if (!(myMultiplyEntity.getItemType() == MyMultiplyEntity.TITLE)) {
                        outRect.bottom = DensityUtils.dp2px(view.getContext(), 2);
                    } else {
                        outRect.top = DensityUtils.dp2px(view.getContext(), 12);
                    }
                }
            };
        }
        return itemDecoration;
    }

    /**
     * 搜索
     *
     * @param keyword 关键字
     */
    public void search(String keyword) {
        requestAccompanimentData(keyword);
        initMatchEntities();
        initUserEntities();
        initWorkEntities();
//        initAll();
    }

    public ArrayList<MyMultiplyEntity> getAccompanimentEntities() {
        return accompanimentEntities;
    }

    public ArrayList<MyMultiplyEntity> getUserEntities() {
        return userEntities;
    }

    public ArrayList<MyMultiplyEntity> getMatchEntities() {
        return matchEntities;
    }

    public ArrayList<MyMultiplyEntity> getWorkEntities() {
        return workEntities;
    }

    public ArrayList<MyMultiplyEntity> getAllEntities() {
        return allEntities;
    }

}
