package cukeulator.cucumber.androidmicrobutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private View closeMicro;
    private View openMicro;
    private View btn_micro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView shape_drawable = findViewById(R.id.shape_drawable);

        Drawable drawable=generateDrawable();
        shape_drawable.setImageDrawable(drawable);



//
//		btn_micro = findViewById(R.id.btn_micro);
//		btn_micro.setVisibility(View.VISIBLE);
//
//		btn_micro.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//				btn_micro.setVisibility(View.GONE);
//				View btn_micro_recording = findViewById(R.id.btn_micro_recording);
//
//				final View gray_filled_circle = btn_micro_recording.findViewById(R.id.gray_filled_circle);
//				btn_micro_recording.setVisibility(View.VISIBLE);
//
//
//				Interpolator interpolator = AnimationUtils.loadInterpolator(MainActivity.this, android.R.interpolator.fast_out_slow_in);
//				ObjectAnimator scaleX = ObjectAnimator.ofFloat(gray_filled_circle, View.SCALE_X, (1f));
//				scaleX.setInterpolator(interpolator);
//				scaleX.setDuration(6000L);
//				ObjectAnimator scaleY = ObjectAnimator.ofFloat(gray_filled_circle, View.SCALE_Y, (1f));
//				scaleY.setInterpolator(interpolator);
//				scaleY.setDuration(6000L);
//				scaleX.start();
//				scaleY.start();


//				final int finalRadius = (int)Math.hypot(gray_filled_circle.getWidth()/2, gray_filled_circle.getHeight()/2);
//
//						Animator anim = ViewAnimationUtils.createCircularReveal(gray_filled_circle, (int) gray_filled_circle.getWidth()/2, (int) gray_filled_circle.getHeight()/2, 0, finalRadius);
//						anim.start();
//
//						anim.addListener(new Animator.AnimatorListener() {
//							@Override
//							public void onAnimationStart(Animator animator) {
//
//							}
//
//							@Override
//							public void onAnimationEnd(Animator animator) {
//								Animator anim = ViewAnimationUtils.createCircularReveal(gray_filled_circle, (int) gray_filled_circle.getWidth()/2, (int) gray_filled_circle.getHeight()/2, 0, 0);
//								anim.start();
//							}
//
//							@Override
//							public void onAnimationCancel(Animator animator) {
//
//							}
//
//							@Override
//							public void onAnimationRepeat(Animator animator) {
//
//							}
//						});
//
//
//			}
//		});
//			}
//		});
    }

    private Drawable generateDrawable() {
        return addPaddingArround((int)convertDpToPixel(100), new MyShapeDrawable(new OvalShape(),
                (int)convertDpToPixel
                (100), (int)convertDpToPixel(3)));

    }

    private class MyShapeDrawable extends ShapeDrawable {
        private Paint mFillPaint= new Paint(Paint.ANTI_ALIAS_FLAG);
        private Paint mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public MyShapeDrawable(Shape s, int sizePx, int strokeWidthPx) {
            super(s);
            this.setIntrinsicWidth ((int) convertDpToPixel(sizePx));
            this.setIntrinsicHeight ((int) convertDpToPixel(sizePx));

            mFillPaint.setStyle(Paint.Style.FILL);
            mFillPaint.setColor(Color.TRANSPARENT);

            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setColor(Color.RED);
            mStrokePaint.setStrokeWidth(strokeWidthPx);


        }

        @Override protected void onDraw(Shape s, Canvas c, Paint p) {
            //s.draw(c, p);
           // s.draw(c, mFillPaint);
            s.draw(c, mStrokePaint);
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public  float convertDpToPixel(float dp){
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public  float convertPixelsToDp(float px, Context context){
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public Drawable addPaddingArround(int padding, Drawable drawable) {
        LayerDrawable withPadding=new LayerDrawable(new Drawable[]{drawable});
        withPadding.setLayerInset(0, padding, padding, padding, padding);
        return withPadding;
    }

}