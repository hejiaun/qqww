package example.common_base.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lib_media.util.FileUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: HeJiaJun
 * Date:
 * Description:图片工具类
 */
public class ImageUtil {
    private static ImageUtil imageUtils = null;

    /**
     * 打开相册请求码
     */
    private final int REQUEST_CODE = 10101;

    private ImageUtil() {
    }

    public static ImageUtil getInstence() {
        if (imageUtils == null) {
            synchronized (ImageUtil.class) {
                if (imageUtils == null) {
                    imageUtils = new ImageUtil();
                }
            }
        }
        return imageUtils;
    }

    /**
     * 获取打开相册的请求码
     *
     * @return
     */
    public int getREQUEST_CODE() {
        return REQUEST_CODE;
    }

    /**
     * 设置imageView宽高
     *
     * @param height imageView的高
     * @param width  imageView的宽
     * @param iv     imageView的id
     */
    public void setImageViewSize(int height, int width, ImageView iv) {
        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        iv.setLayoutParams(layoutParams);
    }

    /**
     * 保存bitmap到本地jpg格式
     */
    public void saveImageToLocal(Bitmap bitmap) {
        //文件夹
        File dir = new File(ConstantValuesUtil.IMAGE_SAVE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            File imageFile = new File(dir, FileUtil.getInstence().getGenerateFileName() + ".jpg");
            fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Bitmap getBitmapFormResources(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public Drawable getDrawableFromResources(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    public Drawable getDrawbleFormBitmap(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public Bitmap getBitmapFormDrawable(Context context, Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE
                        ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //设置绘画的边界，此处表示完整绘制
        drawable.draw(canvas);
        return bitmap;
    }

    public byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                // F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }

    /**
     * 加载图片到ImageView
     *
     * @param activity
     * @param imageView
     */
    public void loadImageToView(Activity activity, ImageView imageView, String path) {
        Glide
                .with(activity)
                .load(path)
                .into(imageView);
    }

    /**
     * 打开相机拍照
     *
     * @param activity
     */
    public void takePhoto(Activity activity) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.CAMERA);
    }

    /**
     * 从相册选择一张图片
     *
     * @param activity
     */
    public void getSingleImageFromAlbum(Activity activity) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(false)
                .imageSpanCount(3)
                .enableCrop(false)
                .isGif(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}
