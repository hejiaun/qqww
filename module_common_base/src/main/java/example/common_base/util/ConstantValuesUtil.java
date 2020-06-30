package example.common_base.util;


import android.os.Environment;

import okhttp3.MediaType;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  常量工具类
 */
public class ConstantValuesUtil {

    public static final int MYSELF = 0;
    public static final int OTHERS = 1;

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    public static final String SPEACE_NAME = "http://schemas.android.com/apk/res/com.example.administrator.shengdoushi";
    //    public static final String WX_APP_ID = "wxeb58a4d64355000d";
//    public static final String WX_SECRET = "486d29cbb7f18bbdcfb16dd22df57a2c";
    public static final String IMAGE_URL_EXAMPLE2 = "https://upload.jianshu.io/users/upload_avatars/7235939/9db9689c-c97f-4040-bfb2-6c1e7e399a7a?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE1 = "https://cdn2.jianshu.io/assets/default_avatar/10-e691107df16746d4a9f3fe9496fd1848.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE3 = "https://upload.jianshu.io/users/upload_avatars/4155288/18d5f99d-8b81-47b9-aac0-dd52c7e1ebf6.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE4 = "http://wanandroid.com/resources/image/pc/logo.png";
    public static final String IMAGE_URL_EXAMPLE5 = "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE6 = "https://cdn2.jianshu.io/assets/default_avatar/4-3397163ecdb3855a0a4139c34a695885.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE7 = "https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE8 = "https://upload.jianshu.io/users/upload_avatars/1594111/a08355973f7c.png?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE9 = "https://upload.jianshu.io/users/upload_avatars/1693221/f5cac1de-8230-40b9-b508-1698b97955eb.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE10 = "https://cdn2.jianshu.io/assets/default_avatar/11-4d7c6ca89f439111aff57b23be1c73ba.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
    public static final String IMAGE_URL_EXAMPLE11 = "https://upload-images.jianshu.io/upload_images/6167143-4d3c94b8ea9c4652.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/724/format/webp";


//    /**
//     * 获取验证码倒数中的handler中的msg
//     */
//    public static final int HANLDER_MESSAGE_GET_VALIDATECODE = 3231;
//
//    /**
//     * 接口基本地址
//     */
//    public static final String BASE_URL = "";
//
//    public static final int KEY_COUNT_DOWN = 3232;
//    public static final String FRAGMENT_MATCH_KEY ="match" ;
//    public static final String FRAGMENT_ENTERTAIN_KEY ="entertain" ;

    public static int COUNTDOWN_MESSAGE_KEY = 3233;


    /**
     * 评委模式
     */
    public static String GAME_ROLE_RATER = "rater";

    /**
     * 选手模式
     */
    public static String GAME_ROLE_SINGER = "player";

    //----------------------排位模式-----------------------//
    public static final String GAME_MODE_RANK = "game_mode_rank";

    //----------------------娱乐模式-----------------------//
    public static final String GAME_MODE_ENTERTAINMENT = "game_mode_entertainment";

    /**
     * 聊天语音保存路径
     */
    public static String VOICE_RECORD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Shengdoushi" + "/shengdoushi_chat_record";

    /**
     * 图片保存路径
     */
    public static String IMAGE_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Shengdoushi" + "/shengdoushi_image";

    /**
     * 视频保存路径
     */
    public static String VIDEO_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Shengdoushi" + "/shenddoushi_video";


}
