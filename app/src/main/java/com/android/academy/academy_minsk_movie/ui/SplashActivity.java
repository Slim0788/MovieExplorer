package com.android.academy.academy_minsk_movie.ui;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.android.academy.academy_minsk_movie.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000;

    private final Handler handler = new Handler();
    private final Launcher launcher = new Launcher();

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(launcher, SPLASH_DELAY);
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(launcher);
        super.onStop();
    }

    private void launch() {
        if (!isFinishing()) {
            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.splash_fade_in, R.anim.splash_fade_out);
            finish();
        }
    }

    private class Launcher implements Runnable {
        @Override
        public void run() {
            launch();
        }
    }
}
