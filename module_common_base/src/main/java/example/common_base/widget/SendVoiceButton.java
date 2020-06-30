package example.common_base.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.common_base.R;
import com.example.lib_media.voice.VoiceMessageRecorder;

import example.common_base.entity.ChatMessageEntity;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.PermissionUtil;
import example.common_base.util.SystemServiceUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SendVoiceButton extends android.support.v7.widget.AppCompatTextView {
    /**
     * Y轴的偏移距离
     */
    final int DISTENCE_Y = 30;
    private boolean isRecording = false;
    private RecordDialog recordDialog = new RecordDialog(getContext());
    private long currentTimeMillis;
    /**
     * 录音时长  max：60s，min:1s
     */
    private float duration = 0;
    private Up2SendMessageListener up2SendMessageListener;

    public SendVoiceButton(Context context) {
        this(context, null);
    }

    public SendVoiceButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendVoiceButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWellPrepareListener();
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
                //开始录音
                //获取权限
                PermissionUtil.getInstance().chekPermissions((Activity) getContext(), new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.VIBRATE}
                        , new PermissionUtil.IPermissionsResult() {
                            @Override
                            public void passPermissons() {
                                isRecording = true;
                            }

                            @Override
                            public void forbitPermissons() {
                                isRecording = false;
                            }
                        });
                if (isRecording) {
                    currentTimeMillis = System.currentTimeMillis();
                    recordDialog.show();
                    //震动
                    SystemServiceUtil.getInstence().vibrateForTime(getContext(), 100);
                    VoiceMessageRecorder.getInstence().prepareAudio(ConstantValuesUtil.VOICE_RECORD_PATH);
                }
                return false;
            }
        });
    }

    /**
     * 设置录音准备完毕的回调方法
     * <p>
     * 主要工作有：
     * 开启线程
     * 获取音量大小
     * 更新对话框音量
     * 判断录音是否超时 若超时，则自动发送消息
     */
    public void setWellPrepareListener() {
        VoiceMessageRecorder.getInstence().setAudioStateListener(new VoiceMessageRecorder.AudioStateListener() {
            @Override
            public void wellPrepared() {
                rx.Observable
                        .create(new rx.Observable.OnSubscribe<Integer>() {
                            @Override
                            public void call(Subscriber<? super Integer> subscriber) {
                                try {
                                    duration = 0;
                                    while (isRecording) {
                                        subscriber.onNext(VoiceMessageRecorder.getInstence().getVoiceLevel(7));
                                        Thread.sleep(200);
                                        duration += 200;
                                        if (duration >= 60000) {//超时了
                                            subscriber.onNext(654321);
                                            isRecording = false;
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                if (integer.intValue() == 654321) {//超时了
                                    sendVoiceMessage();
                                    up2Reset();
                                } else {
                                    recordDialog.setVoiceLevel(integer);
                                }
                            }
                        });
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下
                //设置背景
                setBackground(getResources().getDrawable(R.drawable.shape_button_press));
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
                        VoiceMessageRecorder.getInstence().cancel();
                    } else {//没有出界
                        if (System.currentTimeMillis() - currentTimeMillis < 1000) {//录音时间小于一秒
                            Toast.makeText(getContext(), "录音时间太短", Toast.LENGTH_SHORT).show();
                            VoiceMessageRecorder.getInstence().cancel();
                        } else {//发送语音
                            sendVoiceMessage();
                        }
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
    public void up2Reset() {
        //设置背景
        setBackground(getResources().getDrawable(R.drawable.shape_button_normal));
        isRecording = false;
        recordDialog.changeState(RecordDialog.RECORDING);
        //释放对话框
        recordDialog.dismiss();
    }

    /**
     * 发送消息
     */
    public void sendVoiceMessage() {
        int finalDuration = (int) (duration / 1000) == 0 ? 1 : (int) (duration / 1000);
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity(ChatMessageEntity.MESSAGE_TYPE_VOICE, ChatMessageEntity.RIGHT, 4L, "userID#", ConstantValuesUtil.IMAGE_URL_EXAMPLE3, System.currentTimeMillis());
        chatMessageEntity.setDuration(finalDuration);
        chatMessageEntity.setVoiceFilePath(VoiceMessageRecorder.getInstence().getAudioFilePath());
        up2SendMessageListener.sendVoiceMessage(chatMessageEntity);
        VoiceMessageRecorder.getInstence().release();
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

    /**
     * 发送消息接口
     */
    public interface Up2SendMessageListener {
        /**
         * 发送语音消息
         */
        void sendVoiceMessage(ChatMessageEntity chatMessageEntity);
    }

    /**
     * 设置发送消息接口
     *
     * @param up2SendMessageListener
     */
    public void setUp2SendMessageListener(Up2SendMessageListener up2SendMessageListener) {
        this.up2SendMessageListener = up2SendMessageListener;
    }
}
