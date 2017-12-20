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


public class XinrijiActivity extends AppCompatActivity {

    private static int flag;
    private String diaryDate;
    private String diaryTime;
    private String new_diary;
    private EditText writeDiaryTitle;
    private EditText writeDiaryText;
    private EditText writeEmotion;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String resultTemp = (String) message.obj;
            Intent intent = new Intent();
            intent.setClass(XinrijiActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinriji);
        CheckBox isSecret = (CheckBox) findViewById(R.id.isSecret);
        isSecret.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            }
        });
        // 获取当前时间
        writeDiaryText = (EditText) findViewById(R.id.writeDiaryText);
        writeDiaryTitle = (EditText) findViewById(R.id.writeDiaryTitle);
        writeEmotion = (EditText) findViewById(R.id.writeDiaryEmotion);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(System.currentTimeMillis());
        // 当前的日期
        diaryDate = formatter.format(date);
        formatter = new SimpleDateFormat("HH:mm:ss");
        Date time = new Date(System.currentTimeMillis());
        //　当前的时间
        diaryTime = formatter.format(time);

        ImageButton wanchengxinriji = (ImageButton) findViewById(R.id.finishNewDiary);
        wanchengxinriji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新日记传送数据库
                new Thread() {
                    public void run() {
                        new_diary = "phoneNumber=" + LoginActivity.user.getPhoneNumber() + "&diaryTitle=" + writeDiaryTitle.getText().toString() + "&diaryText=" + writeDiaryText.getText().toString() + "&diaryDate=" + diaryDate + "&emotion=" + writeEmotion.getText().toString() + "&visibility=" + flag;
                        flag = 1;
                        String response = HttpUtil.doPostRequest(NetUtil.PATH_DIARY_NEW, new_diary);
                        Message message = Message.obtain();
                        message.obj = response;
                        handler.sendMessage(message);
                    }
                }.start();
            }
        });
    }
    public void xinrijiBT(View v)
    {
        //Intent intent = new Intent(ShezhiActivity.this, MainActivity.class);
        //startActivity(intent);
        XinrijiActivity.this.finish();
    }
}
