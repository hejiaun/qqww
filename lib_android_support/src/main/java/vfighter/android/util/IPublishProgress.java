package vfighter.android.util;

/**
 * 发布进度内容
 * 
 * @author konlg
 * @param <PROGRESS>
 */
public interface IPublishProgress<PROGRESS> {

    /**
     * 发布进度消息
     * 
     * @param progress
     */
    void progress(PROGRESS... progress);

}