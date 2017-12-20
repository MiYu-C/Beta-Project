package com.zws.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class xinbeiwangActivity extends AppCompatActivity {

    private String new_reminder;
    private EditText reminderTitle;
    private EditText reminderText;
    private String diaryDate;
    private String diaryTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinbeiwang);

        ImageButton xinbeiwang = (ImageButton) findViewById(R.id.xinbeiwang);
        xinbeiwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加新备忘到数据库
                //获取当前时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date(System.currentTimeMillis());
                diaryDate = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                Date time = new Date(System.currentTimeMillis());
                diaryTime = formatter.format(time);

                reminderTitle = (EditText) findViewById(R.id.reminderTitle);
                reminderText = (EditText) findViewById(R.id.reminderText);
                new Thread() {
                    public void run() {
                        new_reminder = "phoneNumber=" + LoginActivity.user.getPhoneNumber() + "&reminderTitle=" + reminderTitle.getText().toString() + "&reminderText=" + reminderText.getText().toString() + "&reminderDate=" + diaryDate + "_" + diaryTime;
                        ;
                        String response = HttpUtil.doPostRequest(NetUtil.PATH_REMINDER_NEW, new_reminder);
                        Message message = Message.obtain();
                        message.obj = response;
                        handler.sendMessage(message);
                    }
                }.start();
            }
        });

    }
    public void abc(View v){
//        // 添加新备忘到数据库
//        //获取当前时间
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date(System.currentTimeMillis());
//        diaryDate = formatter.format(date);
//        formatter = new SimpleDateFormat("HH:mm:ss");
//        Date time = new Date(System.currentTimeMillis());
//        diaryTime = formatter.format(time);
//
//        reminderTitle = (EditText) findViewById(R.id.reminderTitle);
//        reminderText = (EditText) findViewById(R.id.reminderText);
//        new Thread() {
//            public void run() {
//                new_reminder = "phoneNumber=" + LoginActivity.user.getPhoneNumber() + "&reminderTitle=" + reminderTitle.getText().toString() + "&reminderText=" + reminderText.getText().toString() + "&reminderDate=" + diaryDate + "_" + diaryTime;
//                ;
//                String response = HttpUtil.doPostRequest(NetUtil.PATH_REMINDER_NEW, new_reminder);
//                Message message = Message.obtain();
//                message.obj = response;
//                handler.sendMessage(message);
//            }
//        }.start();
    }
    public void xinbeiwangBT(View v)
    {
        //Intent intent = new Intent(ShezhiActivity.this, MainActivity.class);
        //startActivity(intent);
        xinbeiwangActivity.this.finish();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String resultTemp = (String) message.obj;
            Intent intent = new Intent();
            intent.setClass(xinbeiwangActivity.this,BeiwangActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };
}
