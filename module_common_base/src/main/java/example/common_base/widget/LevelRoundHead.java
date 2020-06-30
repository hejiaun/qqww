package example.common_base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common_base.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class LevelRoundHead extends RelativeLayout {

    RoundedImageView ivHead;
    TextView tvLevel;
    RelativeLayout rlHead;
    private View view;

    public LevelRoundHead(Context context) {
        this(context, null);
    }

    public LevelRoundHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LevelRoundHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.view_level_round_head, this);
        initView();
    }

    private void initView() {
        ivHead = view.findViewById(R.id.ivHead);
        tvLevel = view.findViewById(R.id.tv_level);
        rlHead = view.findViewById(R.id.rl_head);
    }

    /**
     * 设置图片
     */
    public void setImage(int image) {
        ivHead.setImageResource(image);
    }

    /**
     * 设置图片大小
     *
     * @param size
     */
    public void setImageSize(int size) {
        ViewGroup.LayoutParams layoutParams = ivHead.getLayoutParams();
        layoutParams.height = size;
        layoutParams.width = size;
        ivHead.setLayoutParams(layoutParams);
    }

    /**
     * 设置头像圆角半径大小
     *
     * @param radius 半径
     */
    public void setImageRadius(float radius) {
        ivHead.setCornerRadius(radius);
    }

    /**
     * 设置等级
     *
     * @param level
     */
    public void setTvLevel(int level) {
        if (level > 99) {
            tvLevel.setText(99 + "+");
        } else {
            tvLevel.setText(level + "");
        }
    }
}
