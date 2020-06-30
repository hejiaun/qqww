package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.example.common_base.R;

import example.common_base.util.DensityUtils;

/**
 * Author: HeJiaJun
 * Date:
 * Description;人工申诉的选择头像的列表
 */
public class ArtificialHeadList extends LinearLayout {
    Context context;

    public ArtificialHeadList(Context context) {
        this(context, null);
    }

    public ArtificialHeadList(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArtificialHeadList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initLayout();
    }

    private void initLayout() {
        setHeadList(5);
    }

    public void setHeadList(int num) {
        setOrientation(LinearLayout.VERTICAL);
        while (num >= 4) {
            num = num - 4;
            addView(getHeadRow(4));
        }
        addView(getHeadRow(num));
    }

    /**
     * 添加一行头像
     *
     * @param visibleCount 显示的头像个数
     */
    private LinearLayout getHeadRow(int visibleCount) {
        LinearLayout linearLayout = new LinearLayout(context);
        MarginLayoutParams layoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, DensityUtils.dp2px(context, 12), 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        for (int i = 0; i < visibleCount; i++) {
            linearLayout.addView(getHead(8, 50, R.drawable.example));
        }
        for (int i = 0; i < 4 - visibleCount; i++) {
            linearLayout.addView(getNullLayout());
        }
        return linearLayout;
    }

    /**
     * 获取一个头像
     *
     * @return
     */
    public LinearLayout getHead(int radius, int size, int image) {
        LinearLayout linearLayout = new LinearLayout(context);
        //设置为居中
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        //设置宽高、权重比
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        linearLayout.setLayoutParams(layoutParams);
        SelectImageView selectImageView = new SelectImageView(context);
        selectImageView.setRadius(DensityUtils.dp2px(context, radius));
        selectImageView.setImage(image);
        selectImageView.setImageSize(DensityUtils.dp2px(context, size));
        linearLayout.addView(selectImageView);
        return linearLayout;
    }

    /**
     * 获取不可见的填充布局
     *
     * @return
     */
    public LinearLayout getNullLayout() {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }
}
