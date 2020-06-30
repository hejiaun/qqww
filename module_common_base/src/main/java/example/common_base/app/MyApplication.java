package example.common_base.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.billy.android.loading.Gloading;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import example.common_base.GolbalAdapter;
import example.common_base.crash.CrashHandler;
import example.common_base.dao.DaoMaster;
import example.common_base.dao.DaoSession;
import example.common_base.entity.ChatSessionEntity;
import example.common_base.entity.UserEntity;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.SpUtil;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import vfighter.android.util.Log;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  自定义Application
 */
public class MyApplication extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    //静态单例
    private static MyApplication application;

    /**
     * 判断是否为debug模式
     *
     * @param context
     * @return
     */
    public boolean isDebug(Context context) {
        boolean isDebug = context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return isDebug;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //----------------------开启全局异常捕获-----------------------//
//        CrashHandler
////                .getInstance()
////                .init(this);
        //----------------------注册EvenetBus-----------------------//
        EventBus
                .getDefault()
                .register(this);
        application = this;
        initDao();
        initARouter();
        initNineGridImageLoader();
        insertDummyData();
        iniGLoading();
    }

    private void iniGLoading() {
        Gloading.initDefault(new GolbalAdapter());
    }

    /**
     * 九宫格图片显示器的图片加载器
     */
    private void initNineGridImageLoader() {
        NineGridView.setImageLoader(new NineGridView.ImageLoader() {
            @Override
            public void onDisplayImage(Context context, ImageView imageView, String url) {
                Picasso.get()
                        .load(url)
                        .into(imageView);
            }

            @Override
            public Bitmap getCacheImage(String url) {
                return null;
            }
        });
    }

    /**
     * 路由器初始化
     */
    private void initARouter() {
        if (isDebug(this)) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
    /**
     * 数据库初始化
     */
    private void initDao() {
        // TODO: 2018/11/9  在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "shengdoushi", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 提供application对象
     *
     * @return application
     */
    public static MyApplication getApplication() {
        return application;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * 假数据初始化
     */
    private void insertDummyData() {
        Observable
                .create(new Observable.OnSubscribe() {
                    @Override
                    public void call(Object o) {
                        //----------------------------------------防止插入第二次--------------------------------------------
                        SharedPreferences sp = getSharedPreferences("InsertDummyData_sp", MODE_PRIVATE);
                        if (sp.contains("isInsertDummyData")) {
                            return;
                        } else {
                            SpUtil.getInstence().saveBoolean(application, "InsertDummyData_sp", "isInsertDummyData", true);
                        }
                        //-----------------------------------------插入联系人数据-----------------------------------------
                        for (int i = 0; i < 30; i++) {
                            if (i % 2 == 0) {
                                getDaoSession().getUserEntityDao().insert(new UserEntity(ConstantValuesUtil.IMAGE_URL_EXAMPLE1, 1l + i, "1dsfe" + ""));
                            } else if (i % 3 == 0) {
                                getDaoSession().getUserEntityDao().insert(new UserEntity(ConstantValuesUtil.IMAGE_URL_EXAMPLE2, 1l + i, "75fvfg" + ""));
                            } else if (i % 4 == 0) {
                                getDaoSession().getUserEntityDao().insert(new UserEntity(ConstantValuesUtil.IMAGE_URL_EXAMPLE3, 1l + i, "erwer" + ""));
                            } else if (i % 5 == 0) {
                                getDaoSession().getUserEntityDao().insert(new UserEntity(ConstantValuesUtil.IMAGE_URL_EXAMPLE4, 1l + i, "okodf" + ""));
                            } else {
                                getDaoSession().getUserEntityDao().insert(new UserEntity(ConstantValuesUtil.IMAGE_URL_EXAMPLE7, 1l + i, "vjkdf" + ""));
                            }
                        }

                        //-----------------------------------------插入对话数据-------------------------------------------

                        for (int i = 0; i < 40; i++) {
                            if (i % 2 == 0) {
                                getDaoSession().getChatSessionEntityDao().insert(new ChatSessionEntity("名称" + i, "测试" + i, System.currentTimeMillis(), new ArrayList<String>() {{
                                    add(ConstantValuesUtil.IMAGE_URL_EXAMPLE1);
                                }}, 2));
                            } else if (i % 3 == 0) {
                                getDaoSession().getChatSessionEntityDao().insert(new ChatSessionEntity("名称" + i, "测试" + i, System.currentTimeMillis(), new ArrayList<String>() {{
                                    add(ConstantValuesUtil.IMAGE_URL_EXAMPLE2);
                                }}, 2));
                            } else if (i % 5 == 0) {
                                getDaoSession().getChatSessionEntityDao().insert(new ChatSessionEntity("名称" + i, "测试" + i, System.currentTimeMillis(), new ArrayList<String>() {{
                                    add("http://pic.cdhdky.com/download/20170523_145513530.jpg");
                                }}, 2));
                            } else if (i % 7 == 0) {
                                getDaoSession().getChatSessionEntityDao().insert(new ChatSessionEntity("名称" + i, "测试" + i, System.currentTimeMillis(), new ArrayList<String>() {{
                                    add(ConstantValuesUtil.IMAGE_URL_EXAMPLE5);
                                }}, 2));
                            } else {
                                getDaoSession().getChatSessionEntityDao().insert(new ChatSessionEntity("名称" + i, "测试" + i, System.currentTimeMillis(), new ArrayList<String>() {{
                                    add(ConstantValuesUtil.IMAGE_URL_EXAMPLE5);
                                }}, 2));
                            }
                        }
                        //-----------------------------------------插入群聊数据------------------------------------------------------
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        Log.d("vfighter", "application inited");
                    }
                });


    }

    /**
     * 接收异常，发送Toast
     *
     * @param exceptionEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveException(ExceptionEvent exceptionEvent) throws IOException {
        Properties properties = new Properties();
        properties.load(getAssets().open("messages.properties"));
        String exceptionMessage = properties.getProperty(exceptionEvent.getExceptionCode() + "");
        if (exceptionMessage != null) {
            Toast.makeText(this, exceptionMessage, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "unknow error!", Toast.LENGTH_SHORT).show();
        }
    }

}
