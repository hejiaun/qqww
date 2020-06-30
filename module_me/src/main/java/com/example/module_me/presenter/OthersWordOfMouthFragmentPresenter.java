package com.example.module_me.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_me.R;
import com.example.module_me.adapter.WordOfMouthAdapter;
import com.example.module_me.view_interface.IOthersWordOfMouthFragmentView;

import java.util.ArrayList;

import example.common_base.base.BasePresenter;
import example.common_base.entity.WordOfMouthEvaluateEntity;
import example.common_base.entity.WordOfMouthSectionEntity;
import example.common_base.util.DensityUtils;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:别人的口碑Fragment的Presenter
 */
public class OthersWordOfMouthFragmentPresenter extends BasePresenter<IOthersWordOfMouthFragmentView> {
    private WordOfMouthAdapter wordOfMouthAdapter = null;
    private ArrayList<WordOfMouthSectionEntity> data = null;

    /**
     * 构造方法，初始化View层
     *
     * @param iOthersWordOfMouthFragmentView View层接口
     */
    public OthersWordOfMouthFragmentPresenter(IOthersWordOfMouthFragmentView iOthersWordOfMouthFragmentView) {
        super(iOthersWordOfMouthFragmentView);
    }


    public ArrayList getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(new WordOfMouthSectionEntity("已评价的课程(2)", true));
        for (int i = 0; i < 11; i++) {
            data.add(new WordOfMouthSectionEntity(new WordOfMouthEvaluateEntity(true)));
        }
        return data;
    }

    public WordOfMouthAdapter getAdapter() {
        if (wordOfMouthAdapter == null) {
            wordOfMouthAdapter = new WordOfMouthAdapter(R.layout.item_word_of_mouth_not_evaluate, R.layout.item_word_of_mouth_section_head, getData());
            wordOfMouthAdapter.addHeaderView(View.inflate(getView().getFragment().getContext(), R.layout.headview_word_of_mouth, null));
            setOnItemChildClickListener();
        }
        return wordOfMouthAdapter;
    }

    /**
     * 列表项子控件点击事件监听器
     */
    private void setOnItemChildClickListener() {
        wordOfMouthAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int viewId = view.getId();
                if (viewId == R.id.spCourseType) {

                }
            }
        });
    }

    /**
     * 获取RecyclerView的Item间隔修饰器
     *
     * @return RecyclerView的Item间隔修饰器
     */
    public RecyclerView.ItemDecoration getItemDecoraction() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) == 0) {//如果是headView
                    return;
                }
                if (parent.getChildAdapterPosition(view) == 1) {
                    outRect.top = DensityUtils.dp2px(view.getContext(), 4);
                } else {
                    WordOfMouthAdapter adapter = (WordOfMouthAdapter) parent.getAdapter();
                    WordOfMouthSectionEntity wordOfMouthSectionEntity = adapter.getData().get(parent.getChildAdapterPosition(view) - 1);
                    if (wordOfMouthSectionEntity.isHeader) {
                        outRect.top = DensityUtils.dp2px(view.getContext(), 12);
                    } else {
                        outRect.bottom = DensityUtils.dp2px(view.getContext(), 6);
                    }
                }
            }
        };
    }
}
