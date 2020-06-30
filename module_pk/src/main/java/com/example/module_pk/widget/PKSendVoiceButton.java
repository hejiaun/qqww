package com.example.module_pk.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import example.common_base.util.SystemServiceUtil;
import example.common_base.widget.RecordDialog;

public class PKSendVoiceButton extends AppCompatImageView {
    /**
     * Y轴的偏移距离
     */
    final int DISTENCE_Y = 30;
    private boolean isRecording = false;
    private RecordDialog recordDialog = new RecordDialog(getContext());

    public PKSendVoiceButton(Context context) {
        this(context, null);
    }

    public PKSendVoiceButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PKSendVoiceButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        recordDialog.setCancelText("取消说话");
        recordDialog.setRecordingText("正在说话");
        setLongClickListener();
    }

    /**
     * 设置按钮的长按事件
     * 长按事件触发第一次时询问用户是否赋予权限
     * 若已经有权限，则开始录音，设置为正在录音状态
     */
    public void setLongClickListener() {
        //设置长按事件
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                recordDialog.show();
                //震动
                SystemServiceUtil.getInstence().vibrateForTime(getContext(), 100);
                return false;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下
                //设置背景
                break;
            case MotionEvent.ACTION_MOVE://手指移动
                if (!isRecording) break;
                if (isOutSide((int) event.getX(), (int) event.getY())) {//已经出界
                    recordDialog.changeState(RecordDialog.CANCEL);
                    break;
                } else {//没有出界
                    recordDialog.changeState(RecordDialog.RECORDING);
                }
                break;
            case MotionEvent.ACTION_UP://手指抬起
                if (isRecording) {
                    if (isOutSide((int) event.getX(), (int) event.getY())) { //如果出界
                    } else {//没有出界
                        sendVoiceMessage();
                    }
                }
                //重置状态
                up2Reset();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 手指抬起  重置状态
     */
    private void up2Reset() {
        //设置背景
        isRecording = false;
        recordDialog.changeState(RecordDialog.RECORDING);
        //释放对话框
        recordDialog.dismiss();
    }

    /**
     * 发送消息
     */
    public void sendVoiceMessage() {
    }

    /**
     * 判断当前位置是否移出到按钮外
     *
     * @param x 手指位置X坐标
     * @param y 手指位置Y坐标
     * @return true：已经出界  false:没有出界
     */
    private boolean isOutSide(int x, int y) {
        if (x < 0 || x > getWidth() || y < -DISTENCE_Y || y > getHeight() + DISTENCE_Y) {
            return true;
        }
        return false;
    }


}
