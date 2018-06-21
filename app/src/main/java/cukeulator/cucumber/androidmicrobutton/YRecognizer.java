package cukeulator.cucumber.androidmicrobutton;

import android.os.CountDownTimer;

public class YRecognizer {
    private CountDownTimer startTimer;
    private CountDownTimer stopTimer;

    public interface Callback {

        void onStarting();

        void onListening();

        void onStopped();
    }

    private final Callback mCallback;

    YRecognizer(Callback callback) {
        mCallback = callback;
    }


    public void start() {
        mCallback.onStarting();
        startTimer = new CountDownTimer(3000, 3000) {

            public void onTick(long millisUntilFinished) {
                //
            }

            public void onFinish() {
                mCallback.onListening();
            }
        }.start();
    }

    public void stop() {
        mCallback.onStopped();

        //Annule toutes les taches running
        if (startTimer != null) {
            startTimer.cancel();
        }
        if (stopTimer != null) {
            stopTimer.cancel();
        }


        stopTimer = new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                //
            }

            public void onFinish() {
                mCallback.onStopped();
            }
        }.start();
    }

}
