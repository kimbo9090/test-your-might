package com.tomas9080gmail.test_your_might;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class acercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

}
