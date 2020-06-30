package com.example.module_pk.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_pk.R;
import com.example.module_pk.activity.PkRaterActivity;
import com.example.module_pk.adapter.GameoverRecyclerViewAdapter;
import com.example.module_pk.entity.GameoverMultiEntity;
import com.example.module_pk.entity.RaterEntity;
import com.example.module_pk.entity.SingerEntity;

import java.util.ArrayList;

import example.common_base.base.BaseFragment;
import example.common_base.base.BasePresenter;
import example.common_base.util.DensityUtils;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:评委模式的唱歌环节Fragment
 */
public class GameOverFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView rcv;
    private ArrayList<GameoverMultiEntity> gameoverEntities;
    private GameoverRecyclerViewAdapter adapter;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        rcv = view.findViewById(R.id.rcv);
        gameoverEntities = new ArrayList<>();
        gameoverEntities.add(new GameoverMultiEntity(GameoverMultiEntity.TITLE));
        gameoverEntities.add(new GameoverMultiEntity(new SingerEntity("陈绮贞", R.drawable.yizhen)));
        gameoverEntities.add(new GameoverMultiEntity(new SingerEntity("张学友", R.drawable.xueyou)));
        gameoverEntities.add(new GameoverMultiEntity(GameoverMultiEntity.TITLE));
        gameoverEntities.add(new GameoverMultiEntity(new RaterEntity("周杰伦", R.drawable.jielun)));
        gameoverEntities.add(new GameoverMultiEntity(new RaterEntity("汪峰", R.drawable.wanfeng)));
        gameoverEntities.add(new GameoverMultiEntity(new RaterEntity("杨坤", R.drawable.yangkun)));
        adapter = new GameoverRecyclerViewAdapter(gameoverEntities);
        rcv.setAdapter(adapter);
        rcv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                //画笔
                Paint dividerPaint = new Paint();
                //item条数
                int childCount = parent.getChildCount();
                //左右开始位置（每次都一样，所以获取一次就足以）
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                //上下开始位置（每次都不一样，所有必须每次都重新获取）
                for (int i = 0; i < childCount; i++) {
                    View view = parent.getChildAt(i);
                    int height = 1;
                    int color = getResources().getColor(R.color.white_transparent);
                    if (i == 0 || i == 3) {
                        height = DensityUtils.dp2px(parent.getContext(), 3);
                        color = getResources().getColor(R.color.statusBar);
                    }
                    dividerPaint.setColor(color);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + height;
                    //获取具体位置完成，即可画
                    c.drawRect(left, top, right, bottom, dividerPaint);
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildLayoutPosition(view);
                int height = 1;
                if (position == 0 || position == 3) {
                    height = DensityUtils.dp2px(getContext(), 3);
                }
                outRect.bottom = height;
                if (position == 3) {
                    outRect.top = DensityUtils.dp2px(getContext(), 10);
                }
            }
        });
    }

    @Override
    public int initLayout() {
        isViewpagerFragment = false;
        return R.layout.fragment_gameover;
    }


    @Override
    public void onClick(View v) {

    }

    /**
     * 获取对应的Activity
     *
     * @return
     */
    public PkRaterActivity getThisActivity() {
        return (PkRaterActivity) getActivity();
    }

}
