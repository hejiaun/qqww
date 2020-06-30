package example.common_base.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;


/**
 * 录音对话框
 */
public class RecordDialog extends Dialog {

    /**
     * 发送
     */
    public static final int RECORDING = 1;
    /**
     * 取消
     */
    public static final int CANCEL = 2;
    private View view;
    private ImageView ivCancel;
    private ImageView ivVoiceLevel;
    private LinearLayout llRecording;
    private final TextView tvState;
    private String recordding = "正在录音";
    private String cancel = "取消发送";

    public RecordDialog(@NonNull Context context) {
        super(context, R.style.FullScreenDialogStyle);
        view = View.inflate(context, R.layout.dialog_record, null);
        setContentView(view);
        ivCancel = (ImageView) view.findViewById(R.id.ivCancel);
        ivVoiceLevel = (ImageView) view.findViewById(R.id.ivVoiceLevel);
        llRecording = (LinearLayout) view.findViewById(R.id.llRecording);
        tvState = (TextView) findViewById(R.id.tvState);
        setCanceledOnTouchOutside(false);
    }


    /**
     *
     */
    public void changeState(int state) {
        switch (state) {
            case RECORDING://发送
                llRecording.setVisibility(View.VISIBLE);
                ivCancel.setVisibility(View.GONE);
                tvState.setText(recordding);
                break;
            case CANCEL://取消发送
                llRecording.setVisibility(View.GONE);
                ivCancel.setVisibility(View.VISIBLE);
                tvState.setText(cancel);
                break;
        }
    }

    /**
     * 设置声音等级
     */
    public void setVoiceLevel(int level) {
        switch (level) {
            case 1:
                ivVoiceLevel.setImageResource(R.drawable.v1);
                break;
            case 2:
                ivVoiceLevel.setImageResource(R.drawable.v2);
                break;
            case 3:
                ivVoiceLevel.setImageResource(R.drawable.v3);
                break;
            case 4:
                ivVoiceLevel.setImageResource(R.drawable.v4);
                break;
            case 5:
                ivVoiceLevel.setImageResource(R.drawable.v5);
                break;
            case 6:
                ivVoiceLevel.setImageResource(R.drawable.v6);
                break;
            case 7:
                ivVoiceLevel.setImageResource(R.drawable.v7);
                break;
        }

    }

    public void setRecordingText(String recordding) {
        this.recordding = recordding;
    }

    public void setCancelText(String cancel) {
        this.cancel = cancel;
    }
}
