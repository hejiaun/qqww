package example.common_base.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common_base.R;
import com.makeramen.roundedimageview.RoundedImageView;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;
import example.common_base.util.ActivityUtil;
import example.common_base.util.ConstantValuesUtil;
import example.common_base.util.ImageUtil;
import example.common_base.widget.ZxingUtil;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  名片Activity
 */
public class VisitingCardActivity extends BaseActivity   {
    RoundedImageView ivHead;
    TextView tvName;
    ImageView ivQRCode;
    private Bitmap bitmap;
    private ImageView ivBack;
    private TextView tvSave;

    @Override
    public void initView() {
        super.initView();
        ivHead = findViewById(R.id.ivHead);
        tvName = findViewById(R.id.tvName);
        ivQRCode = findViewById(R.id.ivQRCode);
        ivBack = findViewById(R.id.ivBack);
        tvSave = findViewById(R.id.tvSave);

        tvSave.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }


    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.activity_visiting_card;
    }

    /**
     * 创建presenter
     *
     * @return presenter
     */
    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    /**
     * 基本配置
     */
    @Override
    public void initConfig() {
        super.initConfig();
        bitmap = ZxingUtil.getInstence().createQRCodeWihtLogo("userId", R.drawable.example, this);
        ivQRCode.setImageBitmap(bitmap);
    }


    /**
     * 点击事件的监听
     *
     * @param view 被点击的控件
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.ivBack) {//点击返回
            ActivityUtil.getInstance().finishActivity(this);
        } else if (viewId == R.id.tvSave) {//点击保存图片
            if (bitmap != null) {
                ImageUtil.getInstence().saveImageToLocal(bitmap);
                Toast.makeText(this, "保存成功至" + ConstantValuesUtil.IMAGE_SAVE_PATH, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
