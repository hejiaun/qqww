package com.example.module_account.presenter;

import com.example.module_account.view_interface.IValidateCodeLoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import example.common_base.base.BasePresenter;
import example.common_base.entity.CountDownMessage;
import example.common_base.util.ConstantValuesUtil;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  验证码登陆Activity的Presenter层
 */
public class ValidateCodeLoginPresenter extends BasePresenter<IValidateCodeLoginView> {
    private Timer timer;
    private int second = 60;

    /**
     * 构造方法，初始化View层
     *
     * @param iValidateCodeLoginView View层接口
     */
    public ValidateCodeLoginPresenter(IValidateCodeLoginView iValidateCodeLoginView) {
        super(iValidateCodeLoginView);
        EventBus.getDefault().register(this);
    }


    /**
     * 开始倒计时
     */
    public void startCountDonw() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                EventBus.getDefault().post(new CountDownMessage(ConstantValuesUtil.COUNTDOWN_MESSAGE_KEY));
            }
        }, 0, 1000);
    }

    /**
     * 设置获取验证码倒数时间
     *
     * @param countDownMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setCountDown(CountDownMessage countDownMessage) {
        if (countDownMessage.getKey() != ConstantValuesUtil.COUNTDOWN_MESSAGE_KEY) return;
        getView().setGetValidateCodeText(second + "秒");
        getView().setClickable(false);
        second--;
        if (second == 0) {
            timer.cancel();
            getView().setGetValidateCodeText("重新获取验证码");
            second = 60;
            getView().setClickable(true);
        }
    }

//    /**
//     * 获取确认对话框
//     */
//    public void showConfirmDialog() {
//
//        final AlertDialog confirmDialog = DialogUtil.getInstence().getConfirmDialog(getView().getContext(), "确认手机号码", "+165465165", new DialogUtil.DialogSure() {
//            @Override
//            public void doSure() {
//                //开始倒计时
//                startCountDonw();
//            }
//        });
//        confirmDialog.show();
//    }

    /**
     * 关闭定时器
     */
    public void cancelTimer() {
        if (timer != null) timer.cancel();
    }


}
