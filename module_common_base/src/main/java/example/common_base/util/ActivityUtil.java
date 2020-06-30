package example.common_base.util;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  Activity工具类
 */
public class ActivityUtil {
    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    private static Stack<Activity> activityStack;
    private static ActivityUtil instance;

    private ActivityUtil() {
    }

    /**
     * 单一实例
     */
    public static ActivityUtil getInstance() {
        if (instance == null) {
            synchronized (ActivityUtil.class) {
                if (instance == null) {
                    instance = new ActivityUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public ActivityUtil addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        if (!activityStack.contains(activity)) {
            activityStack.add(activity);
        }
        return this;
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public ActivityUtil finishTopActivity() {
        Activity activity = activityStack.lastElement();
        activityStack.remove(activity);
        finishActivity(activity);
        return this;
    }


    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public ActivityUtil finishActivity(Class<?> cls) {
        Iterator iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = (Activity) iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
        return this;
    }

    /**
     * 结束所有Activity
     */
    @SuppressWarnings("WeakerAccess")
    public ActivityUtil finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        return this;
    }

    /**
     * 除了某个Activity外的其他Activity都finish
     *
     * @param activity
     */
    public ActivityUtil finishAllActivityExcept(Class activity) {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                if (!activityStack.get(i).getClass().equals(activity))
                    activityStack.get(i).finish();
            }
        }
        return this;
    }

    /**
     * 退出应用程序
     */
    public ActivityUtil appExit() {
        try {
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());

        } catch (Exception e) {
        }
        return this;
    }

    /**
     * 结束指定的Activity
     */
    public ActivityUtil finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
        return this;
    }

    /**
     * 得到指定类名的Activity
     */
    public Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

}
