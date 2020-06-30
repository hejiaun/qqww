package com.example.lib_media.music;

import android.media.MediaPlayer;

import java.io.IOException;
import java.util.Stack;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Date:2019/1/16 15:50
 * Description:音乐播放管理器
 */
public class MusicPlayerManager implements MediaPlayer.OnPreparedListener {
    private static MusicPlayerManager musicPlayerManager;
    private static MediaPlayer mediaPlayer;
    /**
     * 单曲循环播放模式
     */
    public final static int MODE_SINGLE_LOOP = 0xfff111;

    /**
     * 列表顺序循环模式
     */
    public final static int MODE_LIST_LOOP = 0xfff112;

    /**
     * 随机播放模式
     */
    public final static int MODE_RANDOM = 0xfff113;

    /**
     * 顺序播放模式
     */
    public final static int MODE_SEQUENTIAL = 0xfff114;

    /**
     * 歌曲列表栈
     */
    private Stack songListStack = new Stack();

    private MusicPlayerManager() {
    }

    public static MusicPlayerManager getInstence() {
        if (musicPlayerManager == null) {
            synchronized (MusicPlayerManager.class) {
                if (musicPlayerManager == null) {
                    musicPlayerManager = new MusicPlayerManager();
                    mediaPlayer = new MediaPlayer();
                }
            }
        }
        return musicPlayerManager;
    }

    /**
     * 播放上一首
     */
    public void playNext() {

    }

    /**
     * 播放下一首
     */
    public void playPre() {

    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /**
     * 重新播放
     */
    public void rePlay() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /**
     * 添加歌曲到播放列表
     */
    public void addSong2List() {

    }

    /**
     * 清空播放列表
     */
    public void clearList() {

    }

    /**
     * 获取歌曲列表
     *
     * @return
     */
    public Stack getSongList() {
        return songListStack;
    }

    /**
     * 从歌曲列表移除歌曲
     */
    public void removeSongFromList() {

    }

    /**
     * 是否正在播放
     */
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    /**
     * 使用网络链接播放歌曲
     *
     * @param url 网络链接
     */
    public void playNetMusic(String url) throws IOException {
        if (isPlaying()) {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setOnPreparedListener(this);
        }
    }

    /**
     * 播放本地歌曲
     *
     * @param path 本地路径
     */
    public void playLocalMusic(String path) throws IOException {
        if (isPlaying()) {
            mediaPlayer.setDataSource(path);
            mediaPlayer.setOnPreparedListener(this);
        }
    }

    /**
     * 获取播放进度
     *
     * @return
     */
    public int getProgress() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            return mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
        }
        return 0;
    }

    /**
     * 获取播放时长
     */
    public int getDuration() {
        if (mediaPlayer != null) {
            mediaPlayer.getDuration();
        }
        return 0;
    }

    public void setProgressBySecond(int second) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(second * 1000);
        }
    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(progress * getDuration() * 1000);
        }
    }

    /**
     * 准备完成
     *
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        if (!mp.isPlaying()) {
            mp.start();
        }
    }
}
