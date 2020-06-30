package example.common_base.util;

import com.alibaba.android.arouter.launcher.ARouter;

import example.common_base.base.BaseFragment;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ARouterUtil {

    public static final String Main_Activity = "/main/activity";
    public static final String Home_fragment = "/home/fragment";
    public static final String Chat_Fragment = "/chat/fragment";
    public static final String Find_Fragment = "/find/fragment";
    public static final String Me_Fragment = "/me/fragment";
    public static final String Setting_Activity = "/setting/activity";
    public static final String Login_Activity = "/login/activity";
    public static final String PkMode_Activity = "/pkmode/activity";
    public static final String Task_Activity = "/task/activity";
    public static final String OthersInformation_Activity = "/otherinformation/activity";
    public static final String StudySing_Activity = "/studysing/activity";
    public static final String Practice_Activity = "/practice/activity";
    public static final String MultiplayerRoom_Activity = "/MultiplayerRoom/activity";

    public static BaseFragment getFragment(String routhPath) {
        return (BaseFragment) ARouter.getInstance().build(routhPath).navigation();
    }

}
