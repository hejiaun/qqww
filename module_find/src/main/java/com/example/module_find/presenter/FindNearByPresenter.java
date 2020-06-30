package com.example.module_find.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_find.R;
import com.example.module_find.adapter.NearByPersonAdapter;
import com.example.module_find.view_interface.IFindNearByView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.NearbyPersonEntity;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:发现附近的人的Activity的presenter
 */
public class FindNearByPresenter extends BasePresenter<IFindNearByView> {
    private NearByPersonAdapter nearByPersonAdapter;
    int[] bannerData = new int[]{R.drawable.paiwei_mode_bg,
            R.drawable.pipei_mode_bg,
            R.drawable.pipei_mode_bg,
            R.drawable.icon_mine_erweima};

    /**
     * 构造方法，初始化View层
     *
     * @param iFindNearByView View层接口
     */
    public FindNearByPresenter(IFindNearByView iFindNearByView) {
        super(iFindNearByView);
    }


    /**
     * 获取RecyclerView的Item间隔修饰器
     *
     * @return RecyclerView的Item间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 2;
            }
        };
    }

    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {
        // TODO: 2018/12/5 获取第一次进入界面的数据
    }

    /**
     * 请求列表数据
     */
    public void requestListData() {

    }

    /**
     * 请求更多数据
     */
    public void requestMoreData() {
        // TODO: 2018/12/5 获取更多数据
    }

    /**
     * 获取附近的人列表数据集合
     *
     * @return 附近的人列表数据集合
     */
    public ArrayList<NearbyPersonEntity> getPersonData() {
        // TODO: 2018/10/8 获取附近的人列表
        ArrayList<NearbyPersonEntity> nearbyPersonEntities = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            nearbyPersonEntities.add(new NearbyPersonEntity("name", 100 + i * 8, 1, R.drawable.example));
        }
        return nearbyPersonEntities;
    }

    /**
     * 获取附近的人列表适配器
     *
     * @return 附近的人列表适配器
     */
    public NearByPersonAdapter getAdapter() {
        if (nearByPersonAdapter == null) {
            nearByPersonAdapter = new NearByPersonAdapter(R.layout.item_nearby_person, getPersonData());
        }
        return nearByPersonAdapter;
    }

    /**
     * 获取轮播图数据
     *
     * @return 轮播图数据
     */
    public ArrayList getBannerData() {
        // TODO: 2018/10/8 获取轮播图集合
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < bannerData.length; i++) {
            arrayList.add(bannerData[i]);
        }
        return arrayList;
    }

    /**
     * 获取spinner填充数据集合
     *
     * @return spinner填充数据集合
     */
    public ArrayList getSpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("距离");
        strings.add("距离");
        strings.add("距离");
        return strings;
    }

}
