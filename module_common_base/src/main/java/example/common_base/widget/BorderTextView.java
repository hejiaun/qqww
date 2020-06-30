package example.common_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.common_base.R;

import example.common_base.util.DensityUtils;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class BorderTextView extends android.support.v7.widget.AppCompatTextView {

    private TextView borderText = null;///用于描边的TextView
    private int borderColor;
    private int soildColor;
    private float borderWidth;
    private Canvas canvas;

    public BorderTextView(Context context) {
        this(context, null);
        borderText = new TextView(context);
        init();
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        borderText = new TextView(context, attrs);
        init();
    }

    public BorderTextView(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        borderText = new TextView(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BorderTextViewStyle);
        //获取自定义属性
        borderColor = typedArray.getColor(R.styleable.BorderTextViewStyle_borderTextColor, getResources().getColor(R.color.red_select));
        soildColor = typedArray.getColor(R.styleable.BorderTextViewStyle_soildTextColor, getResources().getColor(R.color.white));
        borderWidth = typedArray.getDimension(R.styleable.BorderTextViewStyle_borderWidth, DensityUtils.dp2px(context, 2));
        init();
        typedArray.recycle();
    }

    public void init() {
        TextPaint tp1 = borderText.getPaint();
        tp1.setStrokeWidth(borderWidth);                                  //设置描边宽度
        tp1.setStyle(Paint.Style.STROKE);                             //对文字只描边
        borderText.setTextColor(borderColor);  //设置描边颜色
        borderText.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        borderText.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence tt = borderText.getText();
        //两个TextView上的文字必须一致
        if (tt == null || !tt.equals(this.getText())) {
            borderText.setText(getText());
            this.postInvalidate();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        borderText.measure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        borderText.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        borderText.draw(canvas);
        this.canvas = canvas;
        super.onDraw(canvas);
    }

    /**
     * 设置文字描边的颜色
     *
     * @param color
     */
    public void setBorderColor(int color) {
        borderText.setTextColor(color);
        borderColor = color;
    }
}
