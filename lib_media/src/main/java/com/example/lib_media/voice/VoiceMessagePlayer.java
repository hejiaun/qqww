package com.example.lib_media.voice;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  语音播放器
 */
public class VoiceMessagePlayer {
    private static VoiceMessagePlayer voiceMessagePlayer = null;
    private MediaPlayer mediaPlayer;
    private boolean isPause = false;
    private boolean isPlaying = false;
    private int playingPostion = -1;

    public static VoiceMessagePlayer getInstenece() {
        if (voiceMessagePlayer == null) {
            synchronized (VoiceMessagePlayer.class) {
                if (voiceMessagePlayer == null) {
                    voiceMessagePlayer = new VoiceMessagePlayer();
                }
            }
        }
        return voiceMessagePlayer;
    }

    /**
     * 播放语音消息
     *
     * @param filePath
     */
    public void playVoiceMessage(int playingPostion, String filePath, VoiceMessagePlayOnCompletionListener completionListener) {
        try {
            this.completionListener = completionListener;
            if (VoiceMessagePlayer.this.playingPostion != playingPostion && VoiceMessagePlayer.this.playingPostion >= 0) {
                this.completionListener.onStop(VoiceMessagePlayer.this.playingPostion);
            }
            VoiceMessagePlayer.this.playingPostion = playingPostion;
            mediaPlayer = new MediaPlayer();
            completionListener.onStart();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    VoiceMessagePlayer.this.completionListener.onComplete();
                    isPlaying = false;
                }
            });
            mediaPlayer.setOnErrorListener(
                    new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            mediaPlayer.reset();
                            isPlaying = false;
                            return true;
                        }
                    });
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            isPlaying = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止语言播放
     */
    public void release() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        isPlaying = false;
    }

    public void pause() {
        if (isPlaying) {
            mediaPlayer.pause();
            isPause = true;
            isPlaying = false;
        }
    }

    public void resume() {
        if (mediaPlayer == null) return;
        if (isPause) {
            isPause = false;
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    private VoiceMessagePlayOnCompletionListener completionListener = null;

    public void setVoiceMessagePlayer(VoiceMessagePlayOnCompletionListener completionListener) {
        this.completionListener = completionListener;
    }

    public interface VoiceMessagePlayOnCompletionListener {
        void onComplete();

        void onStart();

        void onStop(int position);
    }

}
