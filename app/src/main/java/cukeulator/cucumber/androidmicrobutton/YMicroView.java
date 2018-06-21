package cukeulator.cucumber.androidmicrobutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Elorri Etchemendi on 21/06/2018.
 */

public class YMicroView extends FrameLayout implements YRecognizer.Callback {

    public static final int MICRO_NORMAL = 0;
    public static final int MICRO_STARTING = 1;
    public static final int MICRO_RECORDING = 2;
    private final Context context;
    private final YRecognizer recognizer;
    private View micro;
    private View micro_starting;
    private View micro_recording;
    private CountDownTimer tickTimer;


    public YMicroView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        recognizer = new YRecognizer(this);

        inflate(context, R.layout.microview, this);

        micro = findViewById(R.id.btn_micro);
        micro_starting = findViewById(R.id.btn_micro_starting);
        micro_recording = findViewById(R.id.btn_micro_recording);

        setState(MICRO_NORMAL);

        micro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.start();
            }
        });

        micro_starting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.stop();
            }
        });

        micro_recording.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.stop();
            }
        });
    }

    private void setState(int state) {
        switch (state) {
            case MICRO_STARTING: {
                micro.setVisibility(GONE);
                micro_starting.setVisibility(VISIBLE);
                micro_recording.setVisibility(GONE);
                break;
            }
            case MICRO_RECORDING: {
                micro.setVisibility(GONE);
                micro_starting.setVisibility(GONE);
                micro_recording.setVisibility(VISIBLE);
                animateListening();
                break;
            }
            case MICRO_NORMAL: {
                micro.setVisibility(VISIBLE);
                micro_starting.setVisibility(GONE);
                micro_recording.setVisibility(GONE);
                break;
            }
        }
    }

    public void animateListening() {

        final ViewGroup container = micro_recording.findViewById(R.id.circle_container);
        int num = (int) (Math.random() * 10);
        for (int i = 0; i < container.getChildCount(); i++) {
            ImageView child = (ImageView) container.getChildAt(i);
            addPaddingArround(20, child.getDrawable());
            if (i == num) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }

//        ImageView gray_filled_circle_1 = container.findViewById(R.id.gray_filled_circle_1);
//        gray_filled_circle_1.setVisibility(VISIBLE);
//        ImageView gray_filled_circle_2 = container.findViewById(R.id.gray_filled_circle_2);
//        ImageView gray_filled_circle_3 = container.findViewById(R.id.gray_filled_circle_3);
//        ImageView gray_filled_circle_4 = container.findViewById(R.id.gray_filled_circle_4);
//        ImageView gray_filled_circle_5 = container.findViewById(R.id.gray_filled_circle_5);
//        ImageView gray_filled_circle_6 = container.findViewById(R.id.gray_filled_circle_6);
//        ImageView gray_filled_circle_7 = container.findViewById(R.id.gray_filled_circle_7);
//        ImageView gray_filled_circle_8 = container.findViewById(R.id.gray_filled_circle_8);
//        ImageView gray_filled_circle_9 = container.findViewById(R.id.gray_filled_circle_9);
        if(tickTimer!=null){
            tickTimer.cancel();
        }

        tickTimer = new CountDownTimer(200, 200) {

            public void onTick(long millisUntilFinished) {
                //
            }

            public void onFinish() {
                animateListening();
            }
        }.start();

//
//        for (int num = 0; num < 10; num++) {
//            //int num = (int) (Math.random() * 10);
//            for (int i = 0; i < container.getChildCount(); i++) {
//                View child = container.getChildAt(i);
//                if (i == num) {
//                    child.setVisibility(VISIBLE);
//                }
//                child.setVisibility(GONE);
//            }
//        }


//		//Supprime le cercle actuel et en rajoute un d'un taille 1dp plus petit
//		for (int i = 100; i >= 80; i--) {
//			container.removeAllViews();
//			View circleView = inflate(context, R.layout.gray_filled_circle, null);
//			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dip(i), dip(i));
//			circleView.setLayoutParams(params);
//			container.addView(circleView);
//			sleep(1000);
//		}
//		for (int i = 80; i <= 100; i++) {
//			container.removeAllViews();
//			View circleView = inflate(context, R.layout.gray_filled_circle, null);
//			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dip(i), dip(i));
//			circleView.setLayoutParams(params);
//			container.addView(circleView);
//			sleep(1000);
//		}
//		AnimatedVectorDrawable animatedVectorDrawable= (AnimatedVectorDrawable) gray_filled_circle.getDrawable();
//
//		ViewPropertyAnimator animator = gray_filled_circle.animate();
//		animator.scaleY(0);
//		animator.scaleX(0);
//		animator.setDuration(3000);
//		animator.start();

//		gray_filled_circle.setScaleX(1f);
//		gray_filled_circle.setScaleY(1f);
//		ObjectAnimator animX = ObjectAnimator.ofFloat(gray_filled_circle, "scaleX", 0.0f);
//		ObjectAnimator animY = ObjectAnimator.ofFloat(gray_filled_circle, "scaleY", 0.0f);
//		AnimatorSet animSetXY = new AnimatorSet();
//		animSetXY.playTogether(animX, animY);
//		animSetXY.setDuration(3000);
//		animSetXY.start();
    }

    public int dip(int size) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) (size * dm.density);
    }


    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStarting() {
        setState(MICRO_STARTING);
    }

    @Override
    public void onListening() {
        setState(MICRO_RECORDING);
    }

    @Override
    public void onStopped() {
        setState(MICRO_NORMAL);
    }

    public Drawable addPaddingArround(int padding, Drawable drawable) {
        LayerDrawable withPadding=new LayerDrawable(new Drawable[]{drawable});
        withPadding.setLayerInset(0, padding, padding, padding, padding);
        return withPadding;
    }
}
