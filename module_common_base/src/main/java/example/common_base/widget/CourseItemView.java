package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;
import com.makeramen.roundedimageview.RoundedImageView;


public class CourseItemView extends LinearLayout {

    RoundedImageView ivHead;
    TextView tvName;
    TextView tvCourseNumber;
    private View view;


    public CourseItemView(Context context) {
        this(context, null);
    }

    public CourseItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CourseItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.view_item_course, this);
        initView();
    }

    private void initView() {
        tvCourseNumber = view.findViewById(R.id.tv_courseNum);
        ivHead = view.findViewById(R.id.ivHead);
        tvName = view.findViewById(R.id.tv_name);
    }


    /**
     * 设置ItemView内容
     *
     * @param name         用户名称
     * @param courseNumber 课程总数和剩余数量
     * @param imgHead      用户头像
     */
    public void setView(String name, String courseNumber, int imgHead) {
        tvCourseNumber.setText(courseNumber);
        tvName.setText(name);
        ivHead.setBackground(getResources().getDrawable(imgHead));
    }
}
