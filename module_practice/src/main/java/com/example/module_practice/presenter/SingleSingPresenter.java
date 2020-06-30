package com.example.module_practice.presenter;

import android.widget.SeekBar;

import com.example.module_practice.view_interface.ISingleSingView;

import java.io.InputStream;

import example.common_base.base.BasePresenter;
import example.common_base.widget.SongProgressView;
import me.wcy.lrcview.LrcView;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  独唱Activity的Presenter
 */
public class SingleSingPresenter extends BasePresenter<ISingleSingView> {


    /**
     * 构造方法，初始化View层
     *
     * @param iSingleSingView View层接口
     */
    public SingleSingPresenter(ISingleSingView iSingleSingView) {
        super(iSingleSingView);
    }



    /**
     * 获取歌词控件点击监听器
     *
     * @return
     */
    public LrcView.OnPlayClickListener getOnPlayClickListener() {
        return new LrcView.OnPlayClickListener() {
            @Override
            public boolean onPlayClick(long time) {
                getView().getLrcView().updateTime(time);
                time = time / 1000;
                SongProgressView songProgressView = getView().getSongProgressView();
                songProgressView.setCurrentProgressTime(time);
                songProgressView.setCurrentProgress((int) time);
                return false;
            }
        };
    }

    /**
     * 获取进度条监听类
     *
     * @return
     */
    public SeekBar.OnSeekBarChangeListener getSeekBarChangeListener() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                //设置歌词控件当前进度
                getView().getSongProgressView().setCurrentProgressTime(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                getView().getLrcView().updateTime((seekBar.getProgress() * 1000));
            }
        };
    }

}
