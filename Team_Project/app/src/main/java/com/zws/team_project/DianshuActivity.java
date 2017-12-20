package com.zws.team_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DianshuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianshu);
    }
    public void wodedianshuBT(View v)
    {
        //Intent intent = new Intent(ShezhiActivity.this, MainActivity.class);
        //startActivity(intent);
        DianshuActivity.this.finish();
    }
}
