package util;

import android.content.Context;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Date:2018/11/23 14:15
 * Description:
 */
public class ShareUtil {
    private static ShareUtil shareUtil = null;

    private ShareUtil() {

    }

    public static ShareUtil getInstence() {
        if (shareUtil == null) {
            synchronized (ShareUtil.class) {
                if (shareUtil == null) {
                    shareUtil = new ShareUtil();
                }
            }
        }
        return shareUtil;
    }

    private IWXAPI initWXShare(Context context) {
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(context, ShareKey.WX_APP_ID, true);
        mWxApi.registerApp(ShareKey.WX_APP_ID);
        return mWxApi;
    }

    /**
     * 微信文本类型分享
     */
    public void shareWXTextMessage(Context context, int scene) {
        IWXAPI iWxapi = initWXShare(context);
        //检查是否安装了微信
        if (!iWxapi.isWXAppInstalled()) {
            Toast.makeText(context, "该手机未安装微信", Toast.LENGTH_LONG).show();
            return;
        }

        //初始化一个WXTextObject
        WXTextObject textObject = new WXTextObject();
        //用WXTextObject 对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(textObject);

        textObject.text = "测试测试";
        msg.description = "描述测试测试";

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = msg;
        req.transaction = String.valueOf(System.currentTimeMillis());//transaction字段用于唯一标识一个请求
        req.scene = scene;
        //调用api接口发送数据到微信
        iWxapi.sendReq(req);
    }

    /**
     * 微信图片类型分享
     */
    public void shareWXImageMessage(Context context, int scene) {
//        IWXAPI iWxapi = initWXShare(context);
//        //初始化一个WXTextObject
//        WXImageObject imageObject = new WXImageObject();
//        //用WXTextObject 对象初始化一个WXMediaMessage对象
//        WXMediaMessage msg = new WXMediaMessage(imageObject);
//
//        //设置缩略图
//        msg.thumbData = ImageUtil.getInstence().bmpToByteArray(ImageUtil.getInstence().getBitmapFormResources(context, R.drawable.ic_back), true);
//
//        //构造一个req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());//transaction字段用于唯一标识一个请求
//        req.scene = scene;
//        req.message = msg;
//
//        //调用api接口发送数据到微信
//        iWxapi.sendReq(req);
    }

    /**
     * 微信音乐类型分享
     */
    public void shareWXMusicMessage() {

    }

    /**
     * 微信视频类型分享
     */
    public void shareWXVideoMessage() {

    }

    /**
     * 微信网页类型分享
     */
    public void shareWXWebpageMessage(Context context, int scene) {
        IWXAPI iWxapi = initWXShare(context);
        //检查是否安装了微信
        if (!iWxapi.isWXAppInstalled()) {
            Toast.makeText(context, "该手机未安装微信", Toast.LENGTH_LONG).show();
            return;
        }
        //初始化一个WXTextObject
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = "https://wwww.baidu.com";
        //用WXTextObject 对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(wxWebpageObject);

        //设置缩略图
        msg.title = "网页分享";
        msg.description = "网页描述";
//        msg.thumbData = ImageUtil.getInstence().bmpToByteArray(ImageUtil.getInstence().getBitmapFormResources(context, R.drawable.ic_back), true);

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());//transaction字段用于唯一标识一个请求
        req.scene = scene;
        req.message = msg;

        //调用api接口发送数据到微信
        iWxapi.sendReq(req);

    }

}
