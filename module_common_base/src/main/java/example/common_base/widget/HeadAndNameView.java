package example.common_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;
import com.makeramen.roundedimageview.RoundedImageView;

import example.common_base.util.DensityUtils;

/**
 * 带名称的圆形头像
 */
public class HeadAndNameView extends LinearLayout {
    Context context;
    RoundedImageView ivRound;
    TextView tvName;
    private View view;
    private TypedArray typedArray;
    private float borderWidth;
    private float headSize;
    private float textSize;
    private float headCorner;
    private int textColor;
    private int borderColor;
    private int imgSrc;
    private String text;

    public void initView() {
        ivRound = view.findViewById(R.id.iv_round);
        tvName = view.findViewById(R.id.tv_name);
    }

    public HeadAndNameView(Context context) {
        this(context, null);
    }

    public HeadAndNameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadAndNameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.view_head_and_name, this);
        initView();
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadAndNameView);
        getValue();
        setValue();
    }


    /**
     * 获取自定义属性值
     */
    public void getValue() {
        borderWidth = DensityUtils.dp2px(context, typedArray.getDimension(R.styleable.HeadAndNameView_headBorderWidth, 0));
        headSize = typedArray.getDimension(R.styleable.HeadAndNameView_headSize, 36);
        textSize = typedArray.getInteger(R.styleable.HeadAndNameView_textSize, 12);
        headCorner = typedArray.getDimension(R.styleable.HeadAndNameView_headCorner, 0);
        textColor = typedArray.getColor(R.styleable.HeadAndNameView_textColor, context.getResources().getColor(R.color.black));
        borderColor = typedArray.getColor(R.styleable.HeadAndNameView_headBorderColor, context.getResources().getColor(R.color.black));
        imgSrc = typedArray.getResourceId(R.styleable.HeadAndNameView_headImage, R.drawable.example);
        text = typedArray.getString(R.styleable.HeadAndNameView_text);
    }

    /**
     * 实现自定义属性值
     */
    public void setValue() {
        ivRound.setImageResource(imgSrc);
        ivRound.setBorderWidth(borderWidth);
        ivRound.setBorderColor(borderColor);
        ivRound.setCornerRadius(headCorner);
        tvName.setText(text);
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvName.setTextColor(textColor);
        //设置头像大小
        ivRound.getLayoutParams().height = (int) headSize;
        ivRound.getLayoutParams().width = (int) headSize;

    }

}
