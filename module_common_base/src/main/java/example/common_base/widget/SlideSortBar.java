package example.common_base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.common_base.R;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class SlideSortBar extends View {
    //----------------------字母数组-----------------------//
    private String words[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    //----------------------字母画笔-----------------------//
    private Paint wordsPaint;
    //----------------------字母背景画笔-----------------------//
    private Paint wordBgPaint;
    //----------------------字母宽度-----------------------//
    private int itemWidth;
    //----------------------字母高度-----------------------//
    private int itemHeight;
    //----------------------手指按下的字母索引-----------------------//
    private int touchIndex = 0;
    //----------------------手指按下的字母改变接口-----------------------//
    private OnWordChangeListener onWordChangeListener = null;


    public SlideSortBar(Context context) {
        this(context, null);
    }

    public SlideSortBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSortBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        wordBgPaint = new Paint();
        wordBgPaint.setColor(Color.GRAY);
        wordsPaint = new Paint();
        wordsPaint.setColor(getResources().getColor(R.color.deep_gray_translucent));
        wordsPaint.setAntiAlias(true);
        wordBgPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            int index = (int) (event.getY() / itemHeight);
            if (touchIndex != index) {
                touchIndex = index;
                if (touchIndex >= 0 && touchIndex <= words.length - 1) {
                    if (onWordChangeListener != null) {
                        setBackground(getResources().getDrawable(R.color.translucent_half_black));
                        onWordChangeListener.wordChange(words[touchIndex]);
                    }
//                    invalidate();
                }
            }
        } else if (action == MotionEvent.ACTION_UP) {
            if (onWordChangeListener != null) {
                onWordChangeListener.wordUpChang();
                setBackground(getResources().getDrawable(R.color.transparent));
            }
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemHeight = (MeasureSpec.getSize(heightMeasureSpec) / 27);
        itemWidth = itemHeight;
        getLayoutParams().width = itemWidth;
        wordsPaint.setTextSize(itemWidth - 12);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
//            if (touchIndex == i) {
////                canvas.drawCircle(itemWidth / 2, itemHeight / 2 + i * itemHeight, itemWidth / 2, wordBgPaint);
////                canvas.drawRect(0, 0, itemWidth, itemHeight*i, wordBgPaint);
//
//                wordsPaint.setColor(Color.WHITE);
//            } else {
//                wordsPaint.setColor(Color.BLACK);
//            }
            //获取文字的宽高
            Rect rect = new Rect();
            wordsPaint.getTextBounds(words[i], 0, 1, rect);
            int wordWidth = rect.width();
            int wordHeight = rect.height();
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemHeight / 2 + wordHeight / 2 + i * itemHeight;
            canvas.drawText(words[i], wordX, wordY, wordsPaint);
        }
    }

    public void setWordChangeListener(OnWordChangeListener onWordChangeListener) {
        this.onWordChangeListener = onWordChangeListener;

    }

    /**
     * 字母切换监听接口
     */
    public interface OnWordChangeListener {
        void wordChange(String s);

        void wordUpChang();
    }

}
