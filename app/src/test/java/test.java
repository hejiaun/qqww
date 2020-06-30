import org.junit.Test;

import example.common_base.net.controller.comment.MusicCircleCommentController;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Date:2019/1/21 14:18
 * Description:
 */
public class test {
    @Test
    public void getCommentByMusicCircleIdT() {
        long l = new MusicCircleCommentController().addComment(1, "323", 1);
        System.out.print(l + "=======================");
    }
}
