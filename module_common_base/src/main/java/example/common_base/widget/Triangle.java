package example.common_base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import example.common_base.util.DensityUtils;

public class Triangle extends View {

    private Context context;

    public Triangle(Context context) {
        this(context, null);
    }

    public Triangle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Triangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Path path = new Path();
        path.moveTo(0, DensityUtils.dp2px(context, 12));
        path.lineTo(DensityUtils.dp2px(context, 10), 0);
        path.lineTo(DensityUtils.dp2px(context, 20), DensityUtils.dp2px(context, 12));
        path.lineTo(0, DensityUtils.dp2px(context, 12));
        canvas.drawPath(path, paint);
    }
}
