package example.common_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.common_base.R;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by Administrator on 2018/6/4.
 */

public class SelectImageView extends RelativeLayout implements View.OnClickListener {

    private ImageView iv_select;
    private RelativeLayout rl;
    private Context mContext;
    private AttributeSet mAttrs;
    private RelativeLayout relativeLayout;
    private RoundedImageView iv;

    public SelectImageView(Context context) {
        this(context, null);
    }

    public SelectImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.view_select_image, this);
        relativeLayout = view.findViewById(R.id.rl);
        iv_select = view.findViewById(R.id.iv_select);
        iv = (RoundedImageView) view.findViewById(R.id.iv);
        iv.setCornerRadius(50);
        rl = view.findViewById(R.id.rl);
        mContext = context;
        mAttrs = attrs;
        setValue();
    }

    /**
     * 设置控件值
     */
    public void setValue() {
        //获取属性值
        TypedArray typedArray = mContext.obtainStyledAttributes(mAttrs, R.styleable.SelectImageView);
        float imageSize = typedArray.getDimension(R.styleable.SelectImageView_imageSize, 20);
        int imageSrc = typedArray.getResourceId(R.styleable.SelectImageView_imageSrc, R.drawable.example);
        float corner = typedArray.getFloat(R.styleable.SelectImageView_imageCorner, 1.0f);
        //设置图片宽高
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = (int) imageSize;
        layoutParams.height = (int) imageSize;
        relativeLayout.setLayoutParams(layoutParams);
        //设置图片
        iv.setImageResource(imageSrc);
        //设置点击事件
        rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (iv_select.getVisibility() == View.GONE) {
            iv_select.setVisibility(View.VISIBLE);
        } else {
            iv_select.setVisibility(View.GONE);
        }
    }

    public void setImage(int src) {
        iv.setImageResource(src);
    }

    public void setRadius(float radius) {
        iv.setCornerRadius(radius);
    }

    public void setImageSize(int size) {
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.height = size;
        layoutParams.width = size;
        relativeLayout.setLayoutParams(layoutParams);
    }
}
