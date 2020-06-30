package example.common_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.common_base.R;


public class FunctionItemView extends RelativeLayout {

    ImageView ivLeft;
    TextView tvTitle;
    Switch sw;
    ImageView ivRight;
    TextView tvRight;
    private Context context;
    private View view;
    private int leftIconResource;
    private String leftString;
    private int rightIconResource;
    private String rightString;

    public FunctionItemView(Context context) {
        this(context, null);
    }

    public FunctionItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunctionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.view_function_item, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FunctionItemView);
        leftIconResource = typedArray.getResourceId(R.styleable.FunctionItemView_fivLeftIcon, R.drawable.ic_delete_photo);
        leftString = typedArray.getString(R.styleable.FunctionItemView_fivLeftString);
        rightIconResource = typedArray.getResourceId(R.styleable.FunctionItemView_fivRightIcon, R.drawable.ic_delete_photo);
        rightString = typedArray.getString(R.styleable.FunctionItemView_fivRightString);
        initView();
        initConfig();
    }

    private void initConfig() {
        if (leftIconResource != R.drawable.ic_delete_photo) {
            ivLeft.setImageResource(leftIconResource);
        }
        if (rightIconResource != R.drawable.ic_delete_photo) {
            ivRight.setImageResource(rightIconResource);
        }
        if (!TextUtils.isEmpty(rightString)) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(rightString);
        }
        tvTitle.setText(leftString);
    }

    private void initView() {
        tvRight = view.findViewById(R.id.tvRightText);
        ivRight = view.findViewById(R.id.iv_right);
        sw = view.findViewById(R.id.sw);
        tvTitle = view.findViewById(R.id.tv_title);
        ivLeft = view.findViewById(R.id.iv_title);

    }

    /**
     * 设置标题图标
     *
     * @param imageTitle
     */
    public void setImageTitle(@Nullable int imageTitle) {
        ivLeft.setImageResource(imageTitle);
    }

    /**
     * 设置标题图片可见性
     *
     * @param visibilisy
     */
    public void setImageTitleVisibilisy(int visibilisy) {
        ivLeft.setVisibility(visibilisy);
    }

    /**
     * 设置标题文本
     *
     * @param textTitle
     */
    public void setTextTitle(String textTitle) {
        tvTitle.setText(textTitle);
    }

    /**
     * 设置右边未向右图片
     */
    public void setRightImage() {
        ivRight.setImageResource(R.drawable.common_btn_zhankai);
        ivRight.setVisibility(View.VISIBLE);
        sw.setVisibility(View.GONE);
    }

    /**
     * 设置右边未开关
     */
    public void setRightSwitch() {
        ivRight.setVisibility(View.GONE);
        sw.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右边为空白
     */
    public void setRightNull() {
        ivRight.setVisibility(View.GONE);
        sw.setVisibility(View.GONE);
    }

    /**
     * 设置开关打开
     */
    public void setSwitchCheck(boolean isCheck) {
        sw.setChecked(isCheck);
    }

    /**
     * 开关是否打开
     *
     * @return
     */
    public boolean isCheck() {
        return sw.isChecked();
    }

    /**
     * 设置右边为文本样式
     *
     * @param text
     */
    public void setRightText(String text) {
        sw.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(text);
    }

    /**
     * 设置添加点击条目开关跟随打开关闭
     */
    public void setClickItemCheck() {
        if (isCheck()) {
            setSwitchCheck(false);
        } else {
            setSwitchCheck(true);
        }
    }


    /**
     * 设置指向右边样式
     */
    public void setRightWithTextTitleStyle(String title) {
        setTextTitle(title);
        setRightImage();
        setImageTitleVisibilisy(View.GONE);
    }


    /**
     * 设置开关样式
     */
    public void setTextTitleWithSwitchStyle(String title) {
        setTextTitle(title);
        setImageTitleVisibilisy(View.GONE);
        setRightSwitch();
    }


    /**
     * @param title      文字标题
     * @param imageTitle 图片标题
     */
    public void setRightWihtTextTitleImageTitleStyle(String title, int imageTitle) {
        setTextTitle(title);
        setRightImage();
        setImageTitle(imageTitle);
    }

    /**
     * 修改右边为文本样式
     *
     * @param text
     * @param textRight
     */
    public void setTextTitleTextRightStyle(String text, String textRight) {
        setTextTitle(text);
        setImageTitleVisibilisy(View.GONE);
        setRightText(textRight);
    }

    /**
     * 修改为文字标题，右边为文本样式和向右图片
     *
     * @param text
     * @param textRight
     */
    public void setTextTitleTextRightToRightStyle(String text, String textRight) {
        setTextTitle(text);
        setImageTitleVisibilisy(View.GONE);
        setRightText(textRight);
        setRightImage();
    }

    /**
     * 设置文本、图片标题，右边为空
     */
    public void setTextTitleImageTitleRightNullSttle(String title, int image) {
        setTextTitle(title);
        setImageTitle(image);
        setRightNull();
    }

    /**
     * 设置文字标题，右边自定义图片
     */
    public void setTextTitleRightCustomImage(String title, int rightImage) {
        setTextTitle(title);
        ivRight.setImageResource(rightImage);
        setImageTitleVisibilisy(View.GONE);
        ivRight.setVisibility(View.VISIBLE);
        sw.setVisibility(View.GONE);
    }

    /**
     * 只设置右边文字
     *
     * @param text
     */
    public void setOnlyRightText(String text) {
        tvRight.setVisibility(VISIBLE);
        tvRight.setText(text);
    }

}
