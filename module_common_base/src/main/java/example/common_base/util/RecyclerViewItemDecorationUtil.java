package example.common_base.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class RecyclerViewItemDecorationUtil {
    private static RecyclerViewItemDecorationUtil util;

    private RecyclerViewItemDecorationUtil() {

    }

    public static RecyclerViewItemDecorationUtil getInstance() {
        if (util == null) {
            synchronized (RecyclerViewItemDecorationUtil.class) {
                if (util == null) {
                    util = new RecyclerViewItemDecorationUtil();
                }
            }
        }
        return util;
    }

    public RecyclerView.ItemDecoration getItemDecoration(final int top, final int right, final int bottom, final int left) {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = bottom;
                outRect.left = left;
                outRect.right = right;
                outRect.top = top;
            }
        };
    }
}
