package com.example.module_me.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.module_me.R;
import com.example.module_me.dialog.StateBottomDialog;
import com.example.module_me.presenter.EditInformationActivityPresenter;
import com.example.module_me.view_interface.IEditInformationActivityView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;
import example.common_base.widget.FunctionItemView;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人信息的Activity
 */
public class EditInformationActivity extends BaseActivity<EditInformationActivityPresenter> implements
        IEditInformationActivityView {
    FunctionItemView fivBirthday;
    FunctionItemView fivHeightAndWeight;
    FunctionItemView fivCharater;
    FunctionItemView fivNickname;
    FunctionItemView fivAddress;
    FunctionItemView fivFood;
    FunctionItemView fivMovie;
    FunctionItemView fivOthers;
    FunctionItemView fivSex;
    TextView tvTitle;
    private String sex;
    private ImageView ivBack;
    private StateBottomDialog stateBottomDialog;
    private FunctionItemView fivState;
    private FunctionItemView fivStateDes;

    @Override
    public void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        fivSex = findViewById(R.id.fiv_sex);
        fivOthers = findViewById(R.id.fiv_others);
        fivMovie = findViewById(R.id.fiv_movie);
        fivFood = findViewById(R.id.fiv_food);
        fivAddress = findViewById(R.id.fiv_address);
        fivNickname = findViewById(R.id.fiv_nickname);
        fivCharater = findViewById(R.id.fiv_charater);
        fivHeightAndWeight = findViewById(R.id.fiv_heightAndWeight);
        fivBirthday = findViewById(R.id.fiv_birthday);
        ivBack = findViewById(R.id.iv_back);
        fivState = findViewById(R.id.fivState);
        fivStateDes = findViewById(R.id.fivStateDes);

        ivBack.setOnClickListener(this);
        fivNickname.setOnClickListener(this);
        fivHeightAndWeight.setOnClickListener(this);
        fivBirthday.setOnClickListener(this);
        fivCharater.setOnClickListener(this);
        fivOthers.setOnClickListener(this);
        fivMovie.setOnClickListener(this);
        fivFood.setOnClickListener(this);
        fivAddress.setOnClickListener(this);
        fivSex.setOnClickListener(this);
        fivState.setOnClickListener(this);
        fivStateDes.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        super.initConfig();
        StatusBarUtil.setLightMode(this);
        tvTitle.setText("个人资料");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit_information;
    }

    @Override
    public EditInformationActivityPresenter createPresenter() {
        return new EditInformationActivityPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || data.getStringExtra("result") == null) {
            return;
        }
        switch (resultCode) {
            case EditNicknameActivity.RESULTCODE://昵称编辑界面回传参数
                fivNickname.setOnlyRightText(data.getStringExtra("result"));
                break;
            case EditFoodActivity.RESULTCODE://个人喜爱食物界面的回传参数
                fivFood.setOnlyRightText(data.getStringExtra("result"));
                break;
            case EditMovieActivity.RESULTCODE://个人喜爱电影界面的回传参数
                fivMovie.setOnlyRightText(data.getStringExtra("result"));
                break;
            case EditCharacterActivity.RESULTCODE://个人性格界面的回传参数
                fivCharater.setOnlyRightText(data.getStringExtra("result"));
                break;
            case EditHeightAndWeightActivity.RESULTCODE://个人身高和体重界面的回传参数
                fivHeightAndWeight.setOnlyRightText(data.getStringExtra("result"));
                break;
        }
    }

    /**
     * 显示性别选择对话框
     */
    private void showChoseSex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        WheelPicker picker = new WheelPicker(this);
        picker.setCyclic(false);
        picker.setSelectedItemTextColor(Color.RED);
        picker.setIndicator(true);
        picker.setIndicatorColor(Color.BLACK);
        picker.setItemSpace(30);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("男");
        strings.add("女");
        picker.setData(strings);
        picker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                sex = (String) picker.getData().get(position);
                fivSex.setOnlyRightText(sex);
            }
        });
        builder.setView(picker);
        AlertDialog dialog = builder.create();
        dialog.setButton(Dialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (sex != null && sex.equals("男")) {
                    // TODO: 2018/12/26 提交新的性别信息
                    getPresenter().commitSex(100000001L, 1);
                } else if (sex != null && sex.equals("女")) {
                    getPresenter().commitSex(100000001L, 2);
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);

        } else if (viewId == R.id.fiv_others) {
            startActivity(new Intent(this, EditOthersActivity.class));

        } else if (R.id.fiv_movie == viewId) {
            startActivityForResult(new Intent(this, EditMovieActivity.class), 0);

        } else if (viewId == R.id.fiv_address) {
            startActivity(new Intent(this, EditAddressActivity.class));

        } else if (viewId == R.id.fiv_nickname) {
            startActivityForResult(new Intent(this, EditNicknameActivity.class), 0);

        } else if (viewId == R.id.fiv_charater) {
            startActivityForResult(new Intent(this, EditCharacterActivity.class), 0);

        } else if (viewId == R.id.fiv_birthday) {
            startActivityForResult(new Intent(this, EditBrithdayActivity.class), 0);

        } else if (viewId == R.id.fiv_food) {
            startActivityForResult(new Intent(this, EditFoodActivity.class), 0);

        } else if (viewId == R.id.fiv_heightAndWeight) {
            startActivityForResult(new Intent(this, EditHeightAndWeightActivity.class), 0);

        } else if (viewId == R.id.fiv_sex) {
            showChoseSex();
        } else if (viewId == R.id.fivState) {
            showStateDialog();
        } else if (viewId == R.id.fivStateDes) {
            startActivity(new Intent(this, StateDesActivity.class));
        }
    }

    /**
     * 显示状态对话框
     */
    private void showStateDialog() {
        if (stateBottomDialog == null) {
            stateBottomDialog = new StateBottomDialog(this);
            stateBottomDialog.setStateBottomDialogClickListener(new StateBottomDialog.StateBottomDialogClickListener() {
                @Override
                public void click1() {

                }

                @Override
                public void click2() {

                }

                @Override
                public void click3() {

                }

                @Override
                public void clickCancel() {

                }
            });
        }
        stateBottomDialog.show();
    }
}
