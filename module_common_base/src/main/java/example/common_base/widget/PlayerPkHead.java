package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class PlayerPkHead extends LinearLayout implements View.OnClickListener{

    RoundedImageView ivPlayerLeft;
    TextView tv;
    RoundedImageView ivPlayerRight;
    private Context context;
    private View view;

    private void initView(){
        ivPlayerRight = view.findViewById(R.id.iv_player_right);
        tv = view.findViewById(R.id.tvVote);
        ivPlayerLeft = view.findViewById(R.id.iv_player_left);
        ivPlayerLeft.setOnClickListener(this);
        ivPlayerRight.setOnClickListener(this);
    }

    public PlayerPkHead(Context context) {
        this(context, null);
    }

    public PlayerPkHead(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerPkHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.include_pk_player_head, this);
        initView();
    }


    /**
     * 设置头像大小，
     *
     * @param size
     * @param corner 圆角
     */
    public void setIvSize(int size, int corner) {
        ivPlayerLeft.setCornerRadius(corner);
        ivPlayerRight.setCornerRadius(corner);
        ViewGroup.LayoutParams layoutParamsLeft = ivPlayerLeft.getLayoutParams();
        layoutParamsLeft.width = size;
        layoutParamsLeft.height = size;
    }

    public void setTv(String text) {
        tv.setText(text);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_player_left) {

        } else if (viewId == R.id.iv_player_right) {

        }
    }
}
