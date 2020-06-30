package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.common_base.R;


public class VerticalProgressBar extends LinearLayout {

    TextView tvPopularity;
    ProgressBar pb;
    ImageView iv;
    private Context context;
    private View view;

    public void initView(){
        pb = view.findViewById(R.id.pb);
        iv = view.findViewById(R.id.iv);
        tvPopularity = view.findViewById(R.id.tv_popularity);

    }

    public VerticalProgressBar(Context context) {
        this(context, null);
    }

    public VerticalProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.vertical_progressbar, this);
        initView();
    }

    /**
     * 设置底部图标id
     *
     * @param id
     */
    public void setBottomImageView(int id) {
        iv.setImageResource(id);
    }

    /**
     * 设置当前进度
     */
    public void setProgress() {

    }

    /**
     * 设置最大进度
     */
    public void setMaxProgress() {


    }

    /**
     * 设置进度文本
     *
     * @param progressText
     */
    public void setProgressText(String progressText) {
        tvPopularity.setText(progressText);
    }

    /**
     * 设置进度条最小宽度
     *
     * @param minWidth
     */
    public void setProgressBarMinWidth(int minWidth) {
        pb.setMinimumWidth(minWidth);
    }
}
