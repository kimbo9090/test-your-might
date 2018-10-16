package com.tomas9080gmail.test_your_might;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class logoActivity extends AppCompatActivity {
    private String TAG = "logoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myLog.d(TAG,"Inicializando onCreate");
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent it = new Intent(logoActivity.this,resumen.class);
                startActivity(it);
                finish();
            }
        },5000);





    }

    @Override
    protected void onStart() {
        myLog.d(TAG,"Inicializando onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        myLog.d(TAG,"Inicializando onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        myLog.d(TAG,"Inicializando onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        myLog.d(TAG,"Inicializando onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        myLog.d(TAG,"Inicializando onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        myLog.d(TAG,"Inicializando onDestroy");
        super.onDestroy();
    }
}
