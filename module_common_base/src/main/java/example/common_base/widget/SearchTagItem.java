package example.common_base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common_base.R;


public class SearchTagItem extends RelativeLayout implements View.OnClickListener{
    Context context;
    TextView tvTag;
    TextView tvMore;
    private View view;
    private OnClickMore clickMore;

    public void initView() {
        tvMore = view.findViewById(R.id.tv_more);
        tvTag = view.findViewById(R.id.tv_tag);
        tvMore.setOnClickListener(this);
    }

    public SearchTagItem(Context context) {
        this(context, null);
    }

    public SearchTagItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchTagItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.view_search_tag_item, this);
        initView();
    }



    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tv_more) {
            clickMore.cliciMore();
        }
    }

    /**
     * 点击更多
     */
    interface OnClickMore {
        void cliciMore();
    }
}
