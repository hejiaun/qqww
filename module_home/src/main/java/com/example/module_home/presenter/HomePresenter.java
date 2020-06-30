package com.example.module_home.presenter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_home.model.HomeFragmentModel;
import com.example.module_home.view_interface.IHomeView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.RankingListEntity;
import example.common_base.util.DensityUtils;
import rx.functions.Action1;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  主页Fragment服务
 */
public class HomePresenter extends BasePresenter<IHomeView> {
    private RecyclerView.ItemDecoration itemDecoration;
    private HomeFragmentModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iHomeView View层接口
     */
    public HomePresenter(IHomeView iHomeView) {
        super(iHomeView);
        model = new HomeFragmentModel();
    }


    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {
        requestFamousRank();
    }

    /**
     * 请求名人榜数据
     */
    public void requestFamousRank() {
        model.getFamousRankListData(new Action1<ArrayList<RankingListEntity>>() {
            @Override
            public void call(ArrayList<RankingListEntity> rankingListEntities) {
                getView().getAdapter().addData(rankingListEntities);
            }
        });
    }

    /**
     * 获取RecyclerView的Item间隔的修饰器
     *
     * @return RecyclerView的Item间隔的修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        if (itemDecoration == null) {
            itemDecoration = new RecyclerView.ItemDecoration() {
                @Override
                public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                    super.onDraw(c, parent, state);
                }

                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.bottom = DensityUtils.dp2px(view.getContext(), 2);
                }
            };
        }
        return itemDecoration;
    }

}
