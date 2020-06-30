package example.common_base.net.controller;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;

import cn.vfighter.communication.EndPointRouter;
import cn.vfighter.communication.http.HttpClientFactory;
import cn.vfighter.communication.http.InMemoryCookieStore;
import example.common_base.app.MyApplication;
import example.common_base.eventbusevent.ExceptionEvent;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public abstract class BaseController {
    private static String routerPath = "";

    public void setUp() {
        //如果继续加载同一个路由文件，则跳出方法,不加载
        if (routerPath.equals(getEndPointFilePaht())) {
            return;
        }
        InputStream configStream = null;
        try {
            HttpClientFactory.get().setCookieStore(new InMemoryCookieStore());
            routerPath = getEndPointFilePaht();
            configStream = MyApplication.getApplication().getAssets().open(routerPath);
            // 加载端点路由
            EndPointRouter.get().load(configStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("==== server start error! ====");
            EventBus.getDefault().post(new ExceptionEvent(999));
        } finally {
            try {
                if (configStream != null) {
                    configStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置路由配置文件路径
     *
     * @return
     */
    public abstract String getEndPointFilePaht();


    public void fondException(String logTag, String exceptionMessage, int exceptionCode) {
        System.out.println(logTag + "=============" + exceptionMessage + ":" + exceptionCode);
        EventBus.getDefault().post(new ExceptionEvent(exceptionCode));
    }
}
