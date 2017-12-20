package com.zws.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XinguihuaActivity extends AppCompatActivity {

    private EditText newPlanningText;
    private EditText newPlanningDate;
    private String new_plan;
    private String diaryDate;
    private static int flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinguihua);
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XinguihuaActivity.this.finish();
            }
        });
        newPlanningDate = (EditText) findViewById(R.id.planningDate);
        newPlanningText = (EditText) findViewById(R.id.planningText);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            }
        });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(System.currentTimeMillis());
        diaryDate = formatter.format(date);
        ImageButton wanchengguihua = (ImageButton) findViewById(R.id.wanchengguihua);
        wanchengguihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        System.out.println("****" + flag);
                        new_plan = "phoneNumber=" + LoginActivity.user.getPhoneNumber() + "&isShortPlaning=" + flag + "&planningDate=" + newPlanningDate.getText().toString() +
                                "&planningText=" + newPlanningText.getText().toString() + "&SystemDate=" + diaryDate;
                        flag = 1;
                        String response = HttpUtil.doPostRequest(NetUtil.PATH_PLAN_NEW, new_plan);
                        Message message = Message.obtain();
                        message.obj = response;
                        handler.sendMessage(message);
                    }
                }.start();
            }
        });

    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String resultTemp = (String) message.obj;
            Intent intent = new Intent();
            intent.setClass(XinguihuaActivity.this,SuoyouGuihuaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };
    public void xinguihuaBT(View v)
    {
        //Intent intent = new Intent(ShezhiActivity.this, MainActivity.class);
        //startActivity(intent);
        XinguihuaActivity.this.finish();
    }
}
