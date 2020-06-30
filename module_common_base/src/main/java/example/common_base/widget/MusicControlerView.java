package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common_base.R;


public class MusicControlerView extends LinearLayout implements View.OnClickListener {
    ImageView ivMode;
    ImageView ivPre;
    ImageView ivPlay;
    ImageView ivNext;
    LinearLayout llRight;
    private Context contex;
    private View view;
    private OnMusicControlerListener onMusicControlerListener;
    private TextView btnMixer;
    private TextView btnPracticeSong;
    private TextView btnStudyMode;

    public MusicControlerView(Context context) {
        this(context, null);
    }

    public MusicControlerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicControlerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.contex = context;
        view = View.inflate(context, R.layout.include_play_bottom_controler, this);
        initView();
    }

    private void initView() {
        llRight = view.findViewById(R.id.ll_right);
        ivNext = view.findViewById(R.id.iv_next);
        ivPlay = view.findViewById(R.id.iv_play);
        ivPre = view.findViewById(R.id.iv_pre);
        ivMode = view.findViewById(R.id.iv_mode);
        ivMode.setOnClickListener(this);
        ivPre.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
    }

    /**
     * 设置点击监听器
     *
     * @param onMusicControlerListener
     */
    public void setOnMusicControlerListener(OnMusicControlerListener onMusicControlerListener) {
        this.onMusicControlerListener = onMusicControlerListener;
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ivMore) {
            onMusicControlerListener.clickMode();

        } else if (viewId == R.id.iv_pre) {
            onMusicControlerListener.clickPre();

        }else if (viewId == R.id.iv_play) {
            onMusicControlerListener.clickPlay();

        }else if (viewId == R.id.iv_next) {
            onMusicControlerListener.clickNext();
        }
    }

    /**
     * 设置为歌曲列表按钮样式
     */
    public void setSongListRight() {
        ImageView ivSongList = new ImageView(contex);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivSongList.setImageResource(R.drawable.gequxiangqing_btn_gedan);
        ivSongList.setClickable(true);
        ivSongList.setLayoutParams(layoutParams);
        llRight.addView(ivSongList);
    }

    public void setMarginBottom(TextView textView) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 6);
        textView.setLayoutParams(layoutParams);
    }

    /**
     * 设置为独唱样式
     */
    public void setSingleSingRight() {
        btnMixer = getTextButton("  调音台  ", 10);
        setMarginBottom(btnMixer);
        btnPracticeSong = getTextButton("  练声曲  ", 10);
        setMarginBottom(btnPracticeSong);
        btnStudyMode = getTextButton("学歌模式", 10);
        llRight.addView(btnMixer);
        btnMixer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llRight.addView(btnPracticeSong);
        btnPracticeSong.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llRight.addView(btnStudyMode);
        btnStudyMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 设置工具按钮为点击状态
     */
    public void setClickStatusTool(TextView textView) {


    }

    /**
     * 设置工具按钮为未点击状态
     */
    public void setUnClickStatusTool(TextView textView) {

    }

    public TextView getTextButton(String text, int textSize) {
        TextView textView = new TextView(contex);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setClickable(true);
        textView.setBackgroundResource(R.drawable.shape_deepgray_round8dp);
        textView.setTextSize(textSize);
        textView.setText(text);
        textView.setLayoutParams(layoutParams);
        return textView;
    }


    /**
     * 点击监听器
     */
    public interface OnMusicControlerListener {
        /**
         * 点击播放模式
         */
        void clickMode();

        /**
         * 点击播放上一首
         */
        void clickPre();

        /**
         * 点击播放下一首
         */
        void clickNext();

        /**
         * 点击播放
         */
        void clickPlay();
    }

    /**
     * 点击三种工具
     */
    public interface OnClickToolListener {
        /**
         * 点击调音台
         */
        void clickMixer();

        /**
         * 点击练声曲
         */
        void clickPracticeSong();

        /**
         * 点击学习模式
         */
        void clickStudyMode();
    }

    /**
     * 设置可见性
     *
     * @param mode 模式按钮
     * @param next 下一首歌曲按钮
     * @param pre  上一首歌曲按钮
     */
    public void setVisibility(int mode, int next, int pre) {
        ivMode.setVisibility(mode);
        ivNext.setVisibility(next);
        ivPre.setVisibility(pre);
    }


}
