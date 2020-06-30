package com.example.share_lib.wxapi;

import android.content.Intent;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class ShareUtil {

    private void share(String title, String content) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, title);//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, content);//添加分享内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "分享");
//        startActivity(share_intent);
    }
}
