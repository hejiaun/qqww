package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common_base.R;

import example.common_base.entity.StudyCourseEntity;
import example.common_base.util.WindowUtil;

public class StudyCourseView extends LinearLayout {
    TextView tvCourseType;
    TextView tvTeacher;
    TextView tvLevel;
    TextView tvTime;
    TextView tvStatus;
    TextView tvMusicCoin;
    LinearLayout ll;
    private Context context;
    private View view;
    private StudyCourseEntity courseEntity;

    public void initView() {
        ll = view.findViewById(R.id.ll);
        tvMusicCoin = view.findViewById(R.id.tvMusicCoin);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvTime = view.findViewById(R.id.tv_time);
        tvLevel = view.findViewById(R.id.tvLevel);
        tvTeacher = view.findViewById(R.id.tv_teacher);
        tvCourseType = view.findViewById(R.id.tv_courseType);
    }

    public StudyCourseView(Context context) {
        this(context, null);
    }

    public StudyCourseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StudyCourseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.item_study_course, this);
        initView();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(WindowUtil.getInstence().getWindowWidth(context) / 2 - 1, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
    }

    /**
     * 设置课程类别
     *
     * @param courseType
     */
    public void setCourseType(String courseType) {
        tvCourseType.setText(courseType);
    }

    /**
     * 设置老师
     *
     * @param teacher
     */
    public void setTeacher(String teacher) {
        tvTeacher.setText(teacher);
    }

    /**
     * 设置时间
     *
     * @param time
     */
    public void setTime(String time) {
        tvTime.setText("时间:" + time);
    }

    public void setLevel(String level) {
        tvLevel.setText("等级:" + level);
    }

    public void setStatus(String status) {
        tvStatus.setText(status);
    }

    public void setMusicCoin(int coin) {
        tvMusicCoin.setText(coin + "");
    }

    public void setContent(StudyCourseEntity studyCourseEntity) {
        courseEntity = studyCourseEntity;
        setCourseType(studyCourseEntity.getCourseType());
        setLevel(studyCourseEntity.getLevel());
        setTime(studyCourseEntity.getDate());
        setOnClickListener();
    }

    /**
     * 设置点击
     */
    public void setOnClickListener() {
        ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (courseEntity != null) {
                    Toast.makeText(context, courseEntity.getLevel(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
