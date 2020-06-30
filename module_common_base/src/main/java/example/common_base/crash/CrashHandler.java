package example.common_base.crash;

import android.app.Application;
import android.content.Intent;

import java.lang.Thread.UncaughtExceptionHandler;

import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:全局异常捕获Handler
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler crashHandler = null;
    private UncaughtExceptionHandler defaultHandler = null;
    private Application context;


    private CrashHandler() {


    }

    /**
     * 初始化
     */
    public void init(Application application) {
        context = application;
        //获取默认异常处理器
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取单例对象
     *
     * @return CrashHandler单例对象
     */
    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                if (crashHandler == null) {
                    crashHandler = new CrashHandler();
                }
            }
        }
        return crashHandler;
    }

    /**
     * 处理未捕获异常
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //测试，当出现异常时，弹出Toast提示
        if (!handleException(e) && defaultHandler != null) {
            // 未经过人为处理,则调用系统默认处理异常,弹出系统强制关闭的对话框
            defaultHandler.uncaughtException(t, e);
        } else {
            // 已经人为处理,系统自己退出
            startCrashActivity(e.getMessage());
            ActivityUtil.getInstance().appExit();
        }
    }

    /**
     * 是否人为捕获异常
     *
     * @param e
     * @return <ul>
     * <li>true:已经处理</li>
     * <li>false:未处理</li>
     * </ul>
     */
    private boolean handleException(Throwable e) {
        if (e == null) {
            return true;
        }
        if (e.getLocalizedMessage() == null) {
            return false;
        }
//        new Thread() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(context, "应用发生未知异常,3秒后自动退出！！", Toast.LENGTH_LONG).show();
//                Looper.loop();
//            }
//        }.start();
        return true;
    }

    private void startCrashActivity(String message) {
        Intent intent = new Intent(context, CrashActivity.class);
        intent.putExtra("errorMessage", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
