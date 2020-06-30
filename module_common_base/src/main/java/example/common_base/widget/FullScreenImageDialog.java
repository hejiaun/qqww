package example.common_base.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.common_base.R;

import java.util.ArrayList;

import example.common_base.util.WindowUtil;

public class FullScreenImageDialog extends Dialog {
    Context context;
    TextView tv;
    ViewPager vp;
    private View view;
    ArrayList<String> imgUrls;
    private PagerAdapter pagerAdapter;
    private ArrayList<PhotoView> photoViews;

    /**
     * 全屏对话框，用来显示图片
     *
     * @param context
     * @param imgUrl
     */
    public FullScreenImageDialog(@NonNull Context context, ArrayList<String> imgUrl) {
        super(context, R.style.FullScreenDialogStyle);
        this.context = context;
        this.imgUrls = imgUrl;
        WindowUtil.getInstence().setDialogFullScreen(getWindow());
        initUI();
    }

    private void initUI() {
        initData();
        initConfig();
    }

    private void initConfig() {
        vp.setAdapter(pagerAdapter);
    }

    private void initData() {
        view = View.inflate(context, R.layout.dialog_fullscreen_image, null);
//        tv = (TextView) view.findViewById(R.id.tv);
        vp = (ViewPager) view.findViewById(R.id.vp);
        photoViews = new ArrayList<>();
        for (int i = 0; i < imgUrls.size(); i++) {
            PhotoView photoView = new PhotoView(context);
            photoView.enable();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            Glide.with(context)
                    .load(imgUrls.get(i))
                    .into(photoView);
            photoViews.add(photoView);
        }
        //viewPager适配器
        pagerAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                ((ViewPager) container).removeView(photoViews.get(position));
            }

            @Override
            public int getCount() {
                return imgUrls.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ((ViewPager) container).addView(photoViews.get(position));
                return photoViews.get(position);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(view);
    }
}
