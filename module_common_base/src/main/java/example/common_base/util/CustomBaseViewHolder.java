package example.common_base.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common_base.R;
import com.lqr.ninegridimageview.LQRNineGridImageView;
import com.lqr.ninegridimageview.LQRNineGridImageViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import example.common_base.entity.ChatMessageEntity;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  自定义列表填充器
 */
public class CustomBaseViewHolder extends BaseViewHolder {
    public CustomBaseViewHolder(View view) {
        super(view);
    }

    /**
     * 通过Glide设置ImageView
     *
     * @param viewId
     * @param url
     */
    public void setImageViewResourceByGlide(int viewId, String url) {
        ImageView imageView = (ImageView) getView(viewId);
        RequestOptions options = new RequestOptions();
        options
                .placeholder(R.drawable.example)
                .centerInside();
        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 设置控件的可见性为View.Gone
     *
     * @param viewId 控件id
     */
    public void setViewGone(int viewId) {
        getView(viewId).setVisibility(View.GONE);
    }

    /**
     * 设置消息发送状态
     *
     * @param id
     * @param status
     */
    public void setMessageState(int id, int status) {
        switch (status) {
            case ChatMessageEntity.MESSAGE_STATUS_SENDING://发送中
                getView(id).setVisibility(View.VISIBLE);
                break;
            case ChatMessageEntity.MESSAGE_STATUS_FAIL://发送失败
                break;
            case ChatMessageEntity.MESSAGE_STATUS_SENT://发送成功
                getView(id).setVisibility(View.INVISIBLE);
                break;
        }

    }

    /**
     * 设置九宫格头像
     *
     * @param id
     * @param res 头像URL集合
     */
    public void setNineGridGroupHead(int id, ArrayList<String> res) {
        LQRNineGridImageView lqrNineGridImageView = (LQRNineGridImageView) getView(id);
        if (res.size() == 1) {//只有一个头像
            lqrNineGridImageView.setGap(0);
        } else {
            lqrNineGridImageView.setGap(DensityUtils.dp2px(lqrNineGridImageView.getContext(), 2));
//            lqrNineGridImageView.setBackgroundColor(lqrNineGridImageView.getResources().getColor(R.color.shallowGray));
        }
        LQRNineGridImageViewAdapter lqrNineGridImageViewAdapter = new LQRNineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String res) {
//                RequestOptions requestOptions = new RequestOptions();
//                requestOptions
//                        .centerInside();
//                Glide
//                        .with(context)
//                        .load(res)
//                        .apply(requestOptions)
//                        .into(imageView);
                Picasso.get()
                        .load(res)
                        .into(imageView);
            }

        };
        //先设置适配器
        lqrNineGridImageView.setAdapter(lqrNineGridImageViewAdapter);
        //再设置填充数据
        lqrNineGridImageView.setImagesData(res);
    }

}
