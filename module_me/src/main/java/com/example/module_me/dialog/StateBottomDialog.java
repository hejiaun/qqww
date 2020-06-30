package com.example.module_me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.allen.library.SuperTextView;
import com.example.module_me.R;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 状态选择底部对话框
 */
public class StateBottomDialog extends BottomSheetDialog implements View.OnClickListener {

    private final View view;
    private SuperTextView stvCancel;
    private SuperTextView stv3;
    private SuperTextView stv2;
    private SuperTextView stv1;

    public StateBottomDialog(@NonNull Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_state, null);
        setContentView(view);
        initView();
    }

    private void initView() {
        stv1 = view.findViewById(R.id.stv1);
        stv2 = view.findViewById(R.id.stv2);
        stv3 = view.findViewById(R.id.stv3);
        stvCancel = view.findViewById(R.id.stvCancel);

        stv1.setOnClickListener(this);
        stv2.setOnClickListener(this);
        stv3.setOnClickListener(this);
        stvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.stv1) {
            if (stateBottomDialogClickListener != null) {
                stateBottomDialogClickListener.click1();
            }
        } else if (id == R.id.stv2) {
            if (stateBottomDialogClickListener != null) {
                stateBottomDialogClickListener.click2();
            }
        } else if (id == R.id.stv3) {
            if (stateBottomDialogClickListener != null) {
                stateBottomDialogClickListener.click3();
            }
        } else if (id == R.id.stvCancel) {
            if (stateBottomDialogClickListener != null) {
                stateBottomDialogClickListener.clickCancel();
            }
        }
        dismiss();
    }

    private StateBottomDialogClickListener stateBottomDialogClickListener;

    public void setStateBottomDialogClickListener(StateBottomDialogClickListener stateBottomDialogClickListener) {
        this.stateBottomDialogClickListener = stateBottomDialogClickListener;
    }

    public interface StateBottomDialogClickListener {
        void click1();

        void click2();

        void click3();

        void clickCancel();

    }
}
