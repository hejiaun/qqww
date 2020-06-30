package example.common_base.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeReader;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Hashtable;

import example.common_base.activity.ScanActivity;

/**
 * 二维码扫描工具类
 */
public class ZxingUtil {
    public static ZxingUtil zxingUtil = null;

    /**
     * 私有构造方法
     */
    private ZxingUtil() {

    }

    /**
     * 获取ZxingUtil单例
     *
     * @return
     */
    public static ZxingUtil getInstence() {
        if (zxingUtil == null) {
            synchronized (ZxingUtil.class) {
                if (zxingUtil == null) {
                    zxingUtil = new ZxingUtil();
                }
            }
        }
        return zxingUtil;
    }

    /**
     * 创建二维码
     *
     * @param str
     * @return
     */
    public Bitmap createQRCode(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            result = multiFormatWriter.encode(new String(str.getBytes("UTF-8"), "ISO-8859-1"),
                    BarcodeFormat.QR_CODE, 1000, 1000);
            result = updateBit(result, 0);
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 创建带Logo的二维码
     *
     * @return
     */
    public Bitmap createQRCodeWihtLogo(String src, int logoBitmap, Context context) {
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), logoBitmap, new BitmapFactory.Options());
        Bitmap qrCode = createQRCode(src);
        return addLogo(qrCode, logo);
    }


    /**
     * 解析二维码图片
     *
     * @return
     */
    public String analysisQRImage(Bitmap bitmap) {
        String result = null;
        try {
            if (bitmap != null) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int[] data = new int[width * height];
                bitmap.getPixels(data, 0, width, 0, 0, width, height);
                RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(width, height, data);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
                QRCodeReader reader = new QRCodeReader();
                Hashtable<DecodeHintType, String> hints = new Hashtable<>();
                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");//设置二维码的编码
                Result decode = reader.decode(binaryBitmap, hints);
                String text = decode.getText();
                result = text;
            }
        } catch (Exception e) {
        }
        return result;
    }


    /**
     * 跳转到扫描界面
     *
     * @param activity
     */
    public void startScanActivity(Activity activity) {
        new IntentIntegrator(activity)
                .setPrompt("")
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .initiateScan();
    }


    /**
     * 在二维码中间添加Logo图案
     */
    private Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }
        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 6 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

    /**
     * 设置二维码边距
     *
     * @param matrix
     * @param margin
     * @return
     */
    private BitMatrix updateBit(BitMatrix matrix, int margin) {
        int tempM = margin * 2;
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = margin; i < resWidth - margin; i++) {
            for (int j = margin; j < resHeight - margin; j++) {
                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }


    /**
     * drawable 转换成bitmap
     *
     * @param drawable
     * @return
     */
    public Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        return bitmap;
    }

}


