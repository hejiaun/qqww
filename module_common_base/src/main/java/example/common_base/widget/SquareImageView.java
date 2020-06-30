package example.common_base.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class SquareImageView extends android.support.v7.widget.AppCompatImageView {


    public SquareImageView(Context context) {
        this(context, null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        getLayoutParams().width = w;
        getLayoutParams().height = w;
    }
}
