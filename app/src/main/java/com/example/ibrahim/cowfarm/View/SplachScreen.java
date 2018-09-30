package com.example.ibrahim.cowfarm.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ibrahim.cowfarm.R;

public class SplachScreen extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);


         /* New Handler to start the RegisterScreen
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the RegisterScreen. */
                Intent mainIntent = new Intent(SplachScreen.this,RegisterScreen.class);
                SplachScreen.this.startActivity(mainIntent);
                SplachScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    } // function onCreate

} // class of SplachScreen
