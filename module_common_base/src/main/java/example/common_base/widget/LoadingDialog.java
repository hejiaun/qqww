package example.common_base.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.example.common_base.R;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class LoadingDialog extends Dialog {


    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        WindowManager windowManager = getWindow().getWindowManager();
        int width = windowManager.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        // 设置窗口背景透明度
        attributes.alpha = 0.3f;
        // 设置窗口宽高为屏幕的三分之一（为了更好地适配，请别直接写死）
        attributes.width = width / 3;
        attributes.height = attributes.width;
        setCancelable(false);
       getWindow().setAttributes(attributes);
    }

}
