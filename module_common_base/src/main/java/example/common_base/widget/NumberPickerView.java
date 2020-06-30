package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class NumberPickerView extends LinearLayout implements View.OnClickListener {
    Context context;
    private View view;
    private TextView addView = null;
    private TextView reduceView = null;
    private TextView numberView = null;
    private int currentNumber;
    private boolean isPositiveNumber = false;

    public NumberPickerView(Context context) {
        this(context, null);
    }

    public NumberPickerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public void setTextSize(int textSize) {
        addView.setTextSize(textSize);
        reduceView.setTextSize(textSize);
        numberView.setTextSize(textSize);
    }

    public void setTextColor(int textColor) {
        addView.setTextColor(textColor);
        reduceView.setTextColor(textColor);
        numberView.setTextColor(textColor);
    }

    /**
     * 增加数量
     */
    public void addNumber() {
        currentNumber = currentNumber + 1;
        numberView.setText(currentNumber + "");
    }

    /**
     * 设置是数量是否只能为正数
     *
     * @param isPositiveNumber
     */
    public void isPositiveNumber(boolean isPositiveNumber) {
        this.isPositiveNumber = isPositiveNumber;
    }

    /**
     * 减少数量
     */
    public void reduceNumber() {
        currentNumber = currentNumber - 1;
        numberView.setText(currentNumber + "");
    }

    public void initView() {
        view = View.inflate(context, R.layout.view_numberpicker, this);
        addView = view.findViewById(R.id.tvAdd);
        reduceView = view.findViewById(R.id.tvReduce);
        numberView = view.findViewById(R.id.tvNumber);
        addView.setOnClickListener(this);
        reduceView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tvAdd) {
            addNumber();
        } else if (viewId == R.id.tvReduce) {
            if (currentNumber > 0) {
                reduceNumber();
            }
        }
    }

    private OnNumberChangerListener onNumberChangerListener = null;

    public interface OnNumberChangerListener {
        void add();

        void reduce();
    }

    public void setOnNumberChangerListener(OnNumberChangerListener onNumberChangerListener) {
        this.onNumberChangerListener = onNumberChangerListener;
    }

}
