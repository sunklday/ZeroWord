package sunkl.jiai.com.zeroword.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import sunkl.jiai.com.zeroword.R;

/**
 * 启动动画界面
 */
public class StartAnimationActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 1000; // 延迟六秒    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_animation);
        getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR); //全屏
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(StartAnimationActivity.this,
                        MainActivity.class);
                StartAnimationActivity.this.startActivity(mainIntent);
                StartAnimationActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
