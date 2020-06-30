package example.common_base.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;


import com.example.common_base.R;

import example.common_base.util.WindowUtil;

public class FullScreenTextDialog extends Dialog {
    Context context ;
    String text;
    private TextView textView;
    private View view;

    /**
     * 全屏对话框，用来显示图片
     *
     * @param context
     */
    public FullScreenTextDialog(@NonNull Context context, String text) {
        super(context, R.style.FullScreenDialogStyle);
        this.context = context;
        this.text = text;
        WindowUtil.getInstence().setDialogFullScreen(getWindow());
        initUI();
    }

    private void initUI() {
        initData();
        initListener();
    }

    private void initListener() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initData() {
        view = View.inflate(context, R.layout.dialog_fullscreen_text, null);
        textView = view.findViewById(R.id.tv);
        textView.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(view);
    }
}
