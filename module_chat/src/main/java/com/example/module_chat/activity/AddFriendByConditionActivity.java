package com.example.module_chat.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.example.module_chat.R;
import com.example.module_chat.presenter.AddFriendByConditionPresenter;
import com.example.module_chat.view_interface.IAddFriendByConditionView;
import com.example.module_chat.window.AageSelectionDialog;
import com.example.module_chat.window.ConstellationSelectionDialog;
import com.example.module_chat.window.LocationSelectionDialog;
import com.example.module_chat.window.RankLevelSelectionDialog;

import example.common_base.base.BaseActivity;
import example.common_base.util.ActivityUtil;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description： 按条件添加朋友
 */
public class AddFriendByConditionActivity extends BaseActivity<AddFriendByConditionPresenter> implements IAddFriendByConditionView {

    private SuperTextView stvConstellation;
    private SuperTextView stvLocation;
    private SuperTextView stvAge;
    private SuperTextView stvRankLevel;
    private SuperTextView stvTitleBar;
    private TextView tvAny;
    private TextView tvLady;
    private TextView tvMan;
    private AageSelectionDialog ageSelectionDialog;
    private RankLevelSelectionDialog rankLevelSelectionDialog;
    private ConstellationSelectionDialog constellationSelectionDialog;
    private LocationSelectionDialog locationSelectionDialog;

    /**
     * 加载控件
     */
    @Override
    public void initView() {
        super.initView();
        stvTitleBar = findViewById(R.id.stvTitleBar);
        stvRankLevel = findViewById(R.id.stvRankLevel);
        stvAge = findViewById(R.id.stvAge);
        stvLocation = findViewById(R.id.stvLocation);
        stvConstellation = findViewById(R.id.stvConstellation);

        tvMan = findViewById(R.id.tvMan);
        tvLady = findViewById(R.id.tvLady);
        tvAny = findViewById(R.id.tvAny);

        tvAny.setOnClickListener(this);
        tvMan.setOnClickListener(this);
        tvLady.setOnClickListener(this);
        stvAge.setOnClickListener(this);
        stvConstellation.setOnClickListener(this);
        stvLocation.setOnClickListener(this);
        stvRankLevel.setOnClickListener(this);
    }

    /**
     * 基础配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        stvTitleBar.setLeftImageViewClickListener(new MyLeftIconClickListener());
        stvTitleBar.setRightTvClickListener(new MyRightTextViewClickListener());

        stvAge.setRightImageViewClickListener(new MyRightIconClickListener(MyRightIconClickListener.AGE));
        stvRankLevel.setRightImageViewClickListener(new MyRightIconClickListener(MyRightIconClickListener.RANK_LEVEL));
        stvLocation.setRightImageViewClickListener(new MyRightIconClickListener(MyRightIconClickListener.LOCATION));
        stvConstellation.setRightImageViewClickListener(new MyRightIconClickListener(MyRightIconClickListener.CONSTELLATION));
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_add_friend_by_condition;
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Override
    public AddFriendByConditionPresenter createPresenter() {
        return new AddFriendByConditionPresenter(this);
    }

    /**
     * 点击事件的监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tvAny) {
            selectSex(R.id.tvAny);
        } else if (id == R.id.tvMan) {
            selectSex(R.id.tvMan);
        } else if (id == R.id.tvLady) {
            selectSex(R.id.tvLady);
        } else if (id == R.id.stvAge) {
            showAgeSelectDialog();
        } else if (id == R.id.stvRankLevel) {
            showRankLevelDialog();
        } else if (id == R.id.stvConstellation) {
            showConstellationDialog();
        } else if (id == R.id.stvLocation) {
            showLocationDialog();
        }
    }

    private void selectSex(int id) {
        if (id == R.id.tvAny) {
            tvAny.setBackgroundResource(R.drawable.shape_red_round4dp);
            tvLady.setBackgroundResource(R.drawable.shape_white_border);
            tvMan.setBackgroundResource(R.drawable.shape_white_border);
        } else if (id == R.id.tvLady) {
            tvLady.setBackgroundResource(R.drawable.shape_red_round4dp);
            tvMan.setBackgroundResource(R.drawable.shape_white_border);
            tvAny.setBackgroundResource(R.drawable.shape_white_border);
        } else if (id == R.id.tvMan) {
            tvLady.setBackgroundResource(R.drawable.shape_white_border);
            tvAny.setBackgroundResource(R.drawable.shape_white_border);
            tvMan.setBackgroundResource(R.drawable.shape_red_round4dp);
        }
    }

    /**
     * 显示年龄选择对话框
     */
    private void showAgeSelectDialog() {
        if (ageSelectionDialog == null) {
            ageSelectionDialog = new AageSelectionDialog(this);
            ageSelectionDialog.setClickListener(new AageSelectionDialog.ClickSuerListener() {
                @Override
                public void clickSure(String result) {
                    stvAge.setRightString(result);
                }
            });
        }
        ageSelectionDialog.show();
    }

    /**
     * 显示段位选择对话框
     */
    private void showRankLevelDialog() {
        if (rankLevelSelectionDialog == null) {
            rankLevelSelectionDialog = new RankLevelSelectionDialog(this);
            rankLevelSelectionDialog.setClickListener(new RankLevelSelectionDialog.ClickSuerListener() {
                @Override
                public void clickSure(String result) {
                    stvRankLevel.setRightString(result);
                }
            });
        }
        rankLevelSelectionDialog.show();
    }

    /**
     * 显示星座选择对话框
     */
    private void showConstellationDialog() {
        if (constellationSelectionDialog == null) {
            constellationSelectionDialog = new ConstellationSelectionDialog(this);
            constellationSelectionDialog.setClickListener(new ConstellationSelectionDialog.ClickSuerListener() {
                @Override
                public void clickSure(String result) {
                    stvConstellation.setRightString(result);
                }
            });
        }
        constellationSelectionDialog.show();
    }

    /**
     * 显示地址选择对话框
     */
    private void showLocationDialog() {
        if (locationSelectionDialog == null) {
            locationSelectionDialog = new LocationSelectionDialog(this);
            locationSelectionDialog.setClickSuerListener(new LocationSelectionDialog.ClickSuerListener() {
                @Override
                public void clickSure(String result) {
                    stvLocation.setRightString(result);
                }
            });
        }
        locationSelectionDialog.show();
    }

    private class MyRightIconClickListener implements SuperTextView.OnRightImageViewClickListener {
        public final static int AGE = 1;
        public final static int RANK_LEVEL = 2;
        public final static int LOCATION = 3;
        public final static int CONSTELLATION = 4;
        private int flag;

        public MyRightIconClickListener(int flag) {
            this.flag = flag;
        }

        @Override
        public void onClickListener(ImageView imageView) {
            if (flag == AGE) {
                Toast.makeText(AddFriendByConditionActivity.this, "age", Toast.LENGTH_SHORT).show();
            } else if (flag == RANK_LEVEL) {
                Toast.makeText(AddFriendByConditionActivity.this, "rank", Toast.LENGTH_SHORT).show();
            } else if (LOCATION == flag) {
                Toast.makeText(AddFriendByConditionActivity.this, "location", Toast.LENGTH_SHORT).show();
            } else if (flag == CONSTELLATION) {
                Toast.makeText(AddFriendByConditionActivity.this, "constellation", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class MyLeftIconClickListener implements SuperTextView.OnLeftImageViewClickListener {
        @Override
        public void onClickListener(ImageView imageView) {
            ActivityUtil.getInstance().finishActivity(AddFriendByConditionActivity.class);
        }
    }

    private class MyRightTextViewClickListener implements SuperTextView.OnRightTvClickListener {
        @Override
        public void onClickListener() {
            startActivity(new Intent(AddFriendByConditionActivity.this, AddFriendByConditionSearchResult.class));
        }
    }

}
