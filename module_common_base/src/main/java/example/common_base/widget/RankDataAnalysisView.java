package example.common_base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.common_base.R;

import io.reactivex.annotations.Nullable;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:用户玩的游戏数据显示
 */
public class RankDataAnalysisView extends LinearLayout implements View.OnClickListener {

    private View view;
    private ImageView ivCurrentRankLevel;
    private ImageView ivHighestRankLevel;
    private ImageView ivImprint;
    private TextView tvHighestRankLevel;
    private TextView tvCurrentRankLevel;
    private TextView tvImprint;
    private TextView tvEntertainment;
    private TextView tvRank;
    private TextView tvAll;
    private TextView tvListenCount;
    private TextView tvFightCount;
    private TextView tvRaterCount;
    private TextView tvSingCount;
    private TextView tvPraiseRate;

    public RankDataAnalysisView(Context context) {
        this(context, null);
    }

    public RankDataAnalysisView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public RankDataAnalysisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.view_rank_data_analysis, this);
        initView();
    }

    private void initView() {
        ivCurrentRankLevel = view.findViewById(R.id.ivCurrentRankLevel);
        ivHighestRankLevel = view.findViewById(R.id.ivHighestRankLevel);
        ivImprint = view.findViewById(R.id.ivImprint);
        tvCurrentRankLevel = view.findViewById(R.id.tvCurrentRankLevel);
        tvHighestRankLevel = view.findViewById(R.id.tvHighestRankLevel);
        tvImprint = view.findViewById(R.id.tvImprint);

        tvAll = view.findViewById(R.id.tvAll);
        tvRank = view.findViewById(R.id.tvRank);
        tvEntertainment = view.findViewById(R.id.tvEntertainment);
        tvAll.setOnClickListener(this);
        tvRank.setOnClickListener(this);
        tvEntertainment.setOnClickListener(this);

        tvListenCount = view.findViewById(R.id.tvListenCount);
        tvFightCount = view.findViewById(R.id.tvFightCount);
        tvRaterCount = view.findViewById(R.id.tvRaterCount);
        tvSingCount = view.findViewById(R.id.tvSingCount);
        tvPraiseRate = view.findViewById(R.id.tvPraiseRate);
    }

    /**
     * 将所有tab字体颜色设置为灰色
     */
    private void resetAllTabColor() {
        tvAll.setTextColor(getContext().getResources().getColor(R.color.fontGray));
        tvRank.setTextColor(getContext().getResources().getColor(R.color.fontGray));
        tvEntertainment.setTextColor(getContext().getResources().getColor(R.color.fontGray));
    }

    /**
     * 设置当前段位
     */
    public void setCurrentRankLevel() {

    }

    /**
     * 设置最高段位
     */
    public void setHighestRankLevel() {

    }

    /**
     * 设置印记
     */
    public void setImprint() {
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        resetAllTabColor();
        if (viewId == R.id.tvEntertainment) {//点击娱乐
            tvEntertainment.setTextColor(getContext().getResources().getColor(R.color.black));

        } else if (viewId == R.id.tvAll) {//点击全部
            tvAll.setTextColor(getContext().getResources().getColor(R.color.black));

        }else if (viewId == R.id.tvAll) {//点击排位
            tvRank.setTextColor(getContext().getResources().getColor(R.color.black));

        }
    }
}
