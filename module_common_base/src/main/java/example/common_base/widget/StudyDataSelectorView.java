package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;


/**
 * 学音乐中的日期选择器
 */
public class StudyDataSelectorView extends LinearLayout implements View.OnClickListener {

    private String courseType = "大课";
    private String date = "周一";
    /**
     * 大课下标
     */
    private static final int BIGCOURSE = 1;
    /**
     * 1对1下标
     */
    private static final int ONEBYONE = 2;
    /**
     * 1对多
     */
    private static final int ONEBYMORE = 3;
    TextView tvCourse;
    TextView tvDateLeft;
    TextView tvOneByOne;
    TextView tvDateMid;
    TextView tvOneByMore;
    TextView tvDateRight;
    Triangle triangleLeft;
    Triangle triangleMiddle;
    Triangle triangleRight;
    TextView tvMonday;
    TextView tvTuesday;
    TextView tvWednesday;
    TextView tvThursday;
    TextView tvFriday;
    TextView tvSaturday;
    TextView tvSunday;
    LinearLayout llCourse;
    LinearLayout llOneByOne;
    LinearLayout llOneByMore;

    //    /**
//     * 当前选中筛选课程
//     */
//    private int courseIndex = 1;
//    /**
//     * 当前选中筛选日期
//     */
//    private int dateIndex = 1;
    private View view;
    private Context context;
    private MyOnSelectListener myOnSelectListener;

    public void initView() {
        llOneByMore = view.findViewById(R.id.ll_oneByMore);
        llOneByOne = view.findViewById(R.id.ll_oneByOne);
        llCourse = view.findViewById(R.id.ll_course);
        tvSunday = view.findViewById(R.id.tv_sunday);
        tvSaturday = view.findViewById(R.id.tv_saturday);
        tvFriday = view.findViewById(R.id.tv_friday);
        tvThursday = view.findViewById(R.id.tv_thursday);
        tvWednesday = view.findViewById(R.id.tv_wednesday);
        tvTuesday = view.findViewById(R.id.tv_tuesday);
        tvMonday = view.findViewById(R.id.tv_monday);
        triangleRight = view.findViewById(R.id.triangle_right);
        triangleMiddle = view.findViewById(R.id.triangle_middle);
        triangleLeft = view.findViewById(R.id.triangle_left);
        tvDateRight = view.findViewById(R.id.tv_date_right);
        tvOneByMore = view.findViewById(R.id.tv_oneByMore);
        tvDateMid = view.findViewById(R.id.tv_date_mid);
        tvOneByOne = view.findViewById(R.id.tv_oneByOne);
        tvDateLeft = view.findViewById(R.id.tv_date_left);
        tvCourse = view.findViewById(R.id.tv_course);

        llOneByMore.setOnClickListener(this);
        llOneByOne.setOnClickListener(this);
        llCourse.setOnClickListener(this);
        tvMonday.setOnClickListener(this);
        tvTuesday.setOnClickListener(this);
        tvWednesday.setOnClickListener(this);
        tvThursday.setOnClickListener(this);
        tvFriday.setOnClickListener(this);
        tvSaturday.setOnClickListener(this);
        tvSunday.setOnClickListener(this);
    }

    public StudyDataSelectorView(Context context) {
        this(context, null);
    }

    public StudyDataSelectorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StudyDataSelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        view = View.inflate(context, R.layout.view_study_date_selector, this);
        initView();
    }


    /**
     * 重置所有日期按钮背景
     */
    public void resetAllDate() {
        tvMonday.setBackgroundResource(R.color.white);
        tvTuesday.setBackgroundResource(R.color.white);
        tvThursday.setBackgroundResource(R.color.white);
        tvWednesday.setBackgroundResource(R.color.white);
        tvSaturday.setBackgroundResource(R.color.white);
        tvSunday.setBackgroundResource(R.color.white);
        tvFriday.setBackgroundResource(R.color.white);

        tvMonday.setTextColor(getResources().getColor(R.color.black));
        tvFriday.setTextColor(getResources().getColor(R.color.black));
        tvSunday.setTextColor(getResources().getColor(R.color.black));
        tvSaturday.setTextColor(getResources().getColor(R.color.black));
        tvWednesday.setTextColor(getResources().getColor(R.color.black));
        tvThursday.setTextColor(getResources().getColor(R.color.black));
        tvTuesday.setTextColor(getResources().getColor(R.color.black));


    }

    /**
     * 重置所有按钮背景
     */
    public void resetAllCourse() {
        llCourse.setBackgroundResource(R.color.statusBar);
        llOneByMore.setBackgroundResource(R.color.statusBar);
        llOneByOne.setBackgroundResource(R.color.statusBar);
        tvCourse.setTextColor(getResources().getColor(R.color.white));
        tvOneByMore.setTextColor(getResources().getColor(R.color.white));
        tvOneByOne.setTextColor(getResources().getColor(R.color.white));
        //指示器可见度
        triangleLeft.setVisibility(View.INVISIBLE);
        triangleMiddle.setVisibility(View.INVISIBLE);
        triangleRight.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置日期文本
     */
    public void setDateText(String text) {
        tvDateLeft.setText(text);
        tvDateMid.setText(text);
        tvDateRight.setText(text);
    }


    /**
     * 点击课程按钮
     */
    public void clickCourseType(int i) {
        LinearLayout ll = null;
        TextView tv = null;
        Triangle triangle = null;
        switch (i) {
            default:
            case BIGCOURSE://点击大课
                ll = llCourse;
                tv = tvCourse;
                triangle = triangleLeft;
                courseType = "大课";
                break;
            case ONEBYMORE://点击一对多
                ll = llOneByMore;
                tv = tvOneByMore;
                triangle = triangleRight;
                courseType = "一对多";
                break;
            case ONEBYONE://点击一对一
                ll = llOneByOne;
                tv = tvOneByOne;
                triangle = triangleMiddle;
                courseType = "一对一";
                break;

        }
        resetAllCourse();
        ll.setBackgroundResource(R.drawable.shape_card_round_white);
        tv.setTextColor(getResources().getColor(R.color.statusBar));
        triangle.setVisibility(View.VISIBLE);
    }


    /**
     * 点击日期按钮
     */
    public void clickDate(int i) {
        String text = null;
        TextView tv = null;
        switch (i) {
            case 1:
                tv = tvMonday;
                text = "周一";
                break;
            case 2:
                tv = tvTuesday;
                text = "周二";
                break;
            case 3:
                tv = tvWednesday;
                text = "周三";
                break;
            case 4:
                tv = tvThursday;
                text = "周四";
                break;
            case 5:
                tv = tvFriday;
                text = "周五";
                break;
            case 6:
                tv = tvSaturday;
                text = "周六";
                break;
            case 7:
                tv = tvSunday;
                text = "周日";
                break;
        }
        resetAllDate();
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setBackgroundResource(R.drawable.shape_purpel_round_10dp);
        setDateText(text);
    }


    public String getDate() {
        return date;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setSelectListener(MyOnSelectListener myOnSelectListener) {
        this.myOnSelectListener = myOnSelectListener;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ll_course) {//点击大课
            clickCourseType(BIGCOURSE);
            myOnSelectListener.clickBigCourse();
        } else if (viewId == R.id.ll_oneByMore) {//点击一对多
            clickCourseType(ONEBYMORE);
            myOnSelectListener.clickOneByMore();
        } else if (viewId == R.id.tv_monday) {//点击周一
            clickDate(1);
            date = "周一";
        } else if (viewId == R.id.tv_tuesday) {//点击周二
            clickDate(2);
            date = "周二";
        } else if (viewId == R.id.tv_wednesday) {//点击周三
            clickDate(3);
            date = "周三";
        } else if (viewId == R.id.tv_thursday) {//点击周四
            clickDate(4);
            date = "周四";
        } else if (viewId == R.id.tv_friday) {//点击周五
            clickDate(5);
            date = "周五";
        } else if (viewId == R.id.tv_saturday) {//点击周六
            clickDate(6);
            date = "周六";
        } else if (viewId == R.id.tv_sunday) {//点击周日
            clickDate(7);
            date = "周日";
        }

    }

    /**
     * 点击监听接口
     */
    public interface MyOnSelectListener {
        void clickBigCourse();//点击大课

        void clickOneByOne();//点击一对一

        void clickOneByMore();//点击一对多

        void clickMonday();//点击星期一

        void clickTuesday();//点击星期二

        void clickWednesday();//点击星期三

        void clickThursday();//点击星期四

        void clickFriday();//点击星期五

        void clickSaturday();//点击星期六

        void clickSunday();//点击星期日
    }
}
