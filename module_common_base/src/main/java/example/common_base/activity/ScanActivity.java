package example.common_base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common_base.R;
import com.jaeger.library.StatusBarUtil;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ImageUtil;
import example.common_base.util.WindowUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  扫一扫Activity
 */
public class ScanActivity extends BaseActivity {
    DecoratedBarcodeView mDbv;
    private CaptureManager captureManager;
    private TextView tvVisitingCard;
    private ImageView ivBack;
    private TextView tvPhoto;


    @Override
    public void initView() {
        super.initView();
        mDbv = findViewById(R.id.dbv);
        tvPhoto = findViewById(R.id.tv_photo);
        ivBack = findViewById(R.id.iv_back);
        tvVisitingCard = findViewById(R.id.tvVisitingCard);
        tvPhoto.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvVisitingCard.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public int initLayout() {
        return R.layout.activity_scan;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initConfig() {
        super.initConfig();
        captureManager = new CaptureManager(this, mDbv);
        WindowUtil.getInstence().setTransparentStatusBar(this);
        StatusBarUtil.setDarkMode(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDbv.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    /**
     * Activity切换的回调
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //图片选择器回调
        if (requestCode == ImageUtil.getInstence().getREQUEST_CODE() && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra("result");
            for (int i = 0; i < result.size(); i++) {

            }
        }
    }

    /**
     * 点击事件监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tv_photo) {//点击相册
            showAlbum();
        } else if (viewId == R.id.tvVisitingCard) {
            startActivity(new Intent(this, VisitingCardActivity.class));
        }
    }

    /**
     * 打开相册
     */
    public void showAlbum() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(false)
                .imageSpanCount(3)
                .enableCrop(false)
                .isGif(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}
