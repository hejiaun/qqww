package com.example.lib_media.voice;

import android.media.MediaRecorder;

import com.example.lib_media.util.FileUtil;

import java.io.File;
import java.io.IOException;


/**
 * Author: HeJiaJun
 * Date:
 * Description:  录音助手
 */
public class VoiceMessageRecorder {
    private AudioStateListener audioStateListener = null;
    private static VoiceMessageRecorder voiceMessageRecorder = null;
    private MediaRecorder mMediaRecorder;
    private boolean isPrepare = false;
    private String mCurrentFilePath;

    private VoiceMessageRecorder() {

    }

    public static VoiceMessageRecorder getInstence() {
        if (voiceMessageRecorder == null) {
            synchronized (VoiceMessageRecorder.class) {
                if (voiceMessageRecorder == null) {
                    voiceMessageRecorder = new VoiceMessageRecorder();
                }
            }
        }
        return voiceMessageRecorder;
    }

    /**
     * 准备录音
     */
    public void prepareAudio(String fileDir) {
        try {
            isPrepare = false;
            //文件夹
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File recordFile = new File(dir, FileUtil.getInstence().getGenerateFileName() + ".amr");
            mCurrentFilePath = recordFile.getAbsolutePath();
            mMediaRecorder = new MediaRecorder();
            //设置输出文件
            mMediaRecorder.setOutputFile(recordFile.getAbsolutePath());
            //设置MediaRecorder的音频源为麦克风
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置音频格式
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            //设置音频编码
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            isPrepare = true;
            if (audioStateListener != null) {
                audioStateListener.wellPrepared();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取录音等级
     *
     * @return
     */
    public int getVoiceLevel(int maxLevel) {
        if (mMediaRecorder == null) return 1;
        if (isPrepare) {
            return maxLevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1;
        }
        return 1;
    }

    /**
     * 释放当前录音
     */
    public void release() {
        if (mMediaRecorder != null) {
            mMediaRecorder.stop();
            mMediaRecorder.release();
        }
        mMediaRecorder = null;
        mCurrentFilePath = null;
    }

    /**
     * 取消录音
     */
    public void cancel() {
        release();
        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
    }

    /**
     * 录音状态回掉接口
     */
    public interface AudioStateListener {
        /**
         * 准备完毕
         */
        void wellPrepared();
    }

    /**
     * 设置录音状态回掉接口
     */
    public void setAudioStateListener(AudioStateListener audioStateListener) {
        this.audioStateListener = audioStateListener;
    }


    /**
     * 获取文件路径
     *
     * @return
     */
    public String getAudioFilePath() {
        return mCurrentFilePath;
    }
}
