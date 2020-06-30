package com.example.module_chat.presenter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.module_chat.R;
import com.example.module_chat.model.ChatSessionFragmentModel;
import com.example.module_chat.view_interface.IChatRecordFragmentView;

import java.util.ArrayList;
import java.util.Collections;

import example.common_base.base.BasePresenter;
import example.common_base.entity.ChatSessionEntity;
import example.common_base.util.DensityUtils;
import rx.functions.Action1;

public class ChatSessionFragmentPresenter extends BasePresenter<IChatRecordFragmentView> {
    private RecyclerView.ItemDecoration itemDecoration = null;
    ArrayList<ChatSessionEntity> topEntities = new ArrayList<>();
    ArrayList<ChatSessionEntity> ordinaryEntites = new ArrayList<>();
    private final ChatSessionFragmentModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iChatRecordFragmentView View层接口
     */
    public ChatSessionFragmentPresenter(IChatRecordFragmentView iChatRecordFragmentView) {
        super(iChatRecordFragmentView);
        model = new ChatSessionFragmentModel();
    }


    /**
     * 获取界面第一次可见时的数据
     */
    public void requestFirtEntryData() {
        model.getAllChatSessionData(new Action1<ArrayList<ChatSessionEntity>>() {
            @Override
            public void call(ArrayList<ChatSessionEntity> rawData) {
                sortAllData(rawData);
            }
        });
    }

    /**
     * 区分出顶置对话和普通对话；
     * 并且顶置对话根据顶置时间排序，普通对话根据对话最新消息时间排序；
     *
     * @param rawData 需要进行排序的集合
     */
    private void sortAllData(ArrayList<ChatSessionEntity> rawData) {
        for (ChatSessionEntity entity : rawData) {
            //将顶置聊天和普通聊天分开
            if (entity.isSetTop()) {
                topEntities.add(entity);
            } else {
                ordinaryEntites.add(entity);
            }
        }

        // 将集合进行时间排序
        if (topEntities.size() != 0) {
            Collections.sort(topEntities);
        }
        if (ordinaryEntites.size() != 0) {
            Collections.sort(ordinaryEntites);
        }
        getView().getAdapter().addData(topEntities);
        getView().getAdapter().addData(ordinaryEntites);
    }


    /**
     * 获取列表item间隔装饰器
     *
     * @return 列表item间隔装饰器
     */
    public RecyclerView.ItemDecoration getItemDecoration() {
        if (itemDecoration == null) {
            itemDecoration = new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.bottom = 1;
                }

                @Override
                public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                    super.onDraw(c, parent, state);
                    //画笔
                    Paint dividerPaint = new Paint();
                    dividerPaint.setColor(parent.getResources().getColor(R.color.lineGray));
                    //item条数
                    int childCount = parent.getChildCount();
                    //左右开始位置（每次都一样，所以获取一次就足以）
                    int left = parent.getPaddingLeft();
                    int right = parent.getWidth() - parent.getPaddingRight();
                    //上下开始位置（每次都不一样，所有必须每次都重新获取）
                    for (int i = 0; i < childCount - 1; i++) {
                        View view = parent.getChildAt(i);
                        float top = view.getBottom();
                        float bottom = view.getBottom() + DensityUtils.dp2px(parent.getContext(), 1);
                        //获取具体位置完成，即可画
                        c.drawRect(left, top, right, bottom, dividerPaint);
                    }
                }
            };
        }
        return itemDecoration;
    }


    /**
     * 顶置聊天对话
     */
    public void setTop(ChatSessionEntity chatSessionEntity) {
        //设置该聊天为顶置和设置置顶时间
        chatSessionEntity.setSetTopTime(System.currentTimeMillis());
        chatSessionEntity.setSetTop(true);
        //添加对象到顶置集合
        topEntities.add(0, chatSessionEntity);
        //从普通对象集合中移除该对象
        ordinaryEntites.remove(chatSessionEntity);
        Collections.sort(topEntities);
        //重新给列表添加数据，顶置集合+普通集合
        getView().getAdapter().setNewData(new ArrayList<ChatSessionEntity>());
        getView().getAdapter().addData(topEntities);
        getView().getAdapter().addData(ordinaryEntites);
    }

    /**
     * 取消顶置对话
     */
    public void cancelSetTop(ChatSessionEntity chatSessionEntity) {
        chatSessionEntity.setSetTop(false);
        //从顶置集合移除
        topEntities.remove(chatSessionEntity);
        //添加到普通集合中，并且根据时间重新排序
        ordinaryEntites.add(chatSessionEntity);
        Collections.sort(ordinaryEntites);
        //重新给列表添加数据，顶置集合+普通集合
        getView().getAdapter().setNewData(new ArrayList<ChatSessionEntity>());
        getView().getAdapter().addData(topEntities);
        getView().getAdapter().addData(ordinaryEntites);
    }

    /**
     * 删除对话
     */
    public void deleteSession(int position) {
        //判断被删除的对话存在顶置集合还是普通集合中，并删除
        if (position < topEntities.size()) {
            topEntities.remove(position);
        } else {
            ordinaryEntites.remove(position - topEntities.size());
        }
        getView().getAdapter().remove(position);
    }

}
