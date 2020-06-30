package example.common_base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.common_base.R;

import example.common_base.util.FormatUtil;

public class SongProgressView extends LinearLayout {
    TextView tvCurrentProgress;
    SeekBar sbSongProgress;
    TextView tvDuration;
    private Context context;
    private View view;

    public void initView(){
        tvDuration = view.findViewById(R.id.tv_duration);
        sbSongProgress = view.findViewById(R.id.sb_song_progress);
        tvCurrentProgress = view.findViewById(R.id.tv_current_progress);
    }

    public SongProgressView(Context context) {
        this(context, null);
    }

    public SongProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SongProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.view_song_progress, this);
        this.context = context;
        initView();
    }

    /**
     * 设置歌曲时长
     *
     * @param second 时长(秒)
     */
    public void setDuration(long second) {
        sbSongProgress.setMax((int) second);
        tvDuration.setText(FormatUtil.getInstence().second2MS(second));
    }

    /**
     * 设置进度条监听类
     *
     * @param seekBarChangeListener
     */
    public void setProgressChangeListener(SeekBar.OnSeekBarChangeListener seekBarChangeListener) {
        sbSongProgress.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    /**
     * 设置单前歌曲进度
     *
     * @param second 当前进度(秒)
     */
    public void setCurrentProgressTime(long second) {
        tvCurrentProgress.setText(FormatUtil.getInstence().second2MS(second));
    }

    /**
     * 设置进度条进度
     */
    public void setCurrentProgress(int progress) {
        sbSongProgress.setProgress(progress);
    }
}
