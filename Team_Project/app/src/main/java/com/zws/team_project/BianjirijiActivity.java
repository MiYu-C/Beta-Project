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

public class BianjirijiActivity extends AppCompatActivity {
    private static int flag;

    private boolean visibility;


    private String bianji_diary;
    private EditText alterDiaryTitle;
    private EditText alterDiaryEmotion;
    private EditText alterDiaryText;
    private String diaryDate;
    private String diaryTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianjiriji);
        alterDiaryTitle = (EditText) findViewById(R.id.alterDiaryTitle);
        alterDiaryTitle.setText(MainActivity.onediary.getDiaryTitle());
        alterDiaryEmotion = (EditText) findViewById(R.id.alterDiaryEmotion);
        alterDiaryEmotion.setText(MainActivity.onediary.getEmotion());
        alterDiaryText = (EditText) findViewById(R.id.alterDiaryText);
        alterDiaryText.setText(MainActivity.onediary.getDiaryText());
        CheckBox isSecret = (CheckBox) findViewById(R.id.isSecret);
        isSecret.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag = 0;

                    visibility=false;
                } else {
                    flag = 1;
                    visibility=true;

                } else {
                    flag = 1;
                }
            }
        });
        ImageButton wanchengbianji = (ImageButton) findViewById(R.id.wanchengbianji);
        wanchengbianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 编写 更改内容确定并上传数据库
                //获取当前时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date(System.currentTimeMillis());
                diaryDate = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                Date time = new Date(System.currentTimeMillis());
                diaryTime = formatter.format(time);

                MainActivity.onediary.setDiaryTitle(alterDiaryTitle.getText().toString());
                MainActivity.onediary.setDiaryText(alterDiaryText.getText().toString());
                MainActivity.onediary.setEmotion(alterDiaryEmotion.getText().toString());
                MainActivity.onediary.setVisibility(visibility);

                new Thread() {
                    public void run() {
                        System.out.println("****" + flag);
                        bianji_diary = "phoneNumber=" + LoginActivity.user.getPhoneNumber() + "&diaryTitle=" + alterDiaryTitle.getText().toString() + "&diaryText=" + alterDiaryText.getText().toString() + "&diaryDate=" + diaryDate +
                              "_" + diaryTime + "&emotion=" + alterDiaryEmotion.getText().toString() + "&visibility=" + flag + "&systemID=" + MainActivity.onediary.getSystemID();
                        flag = 1;
                        String response = HttpUtil.doPostRequest(NetUtil.PATH_DIARY_EDIT, bianji_diary);
                        Message message = Message.obtain();
                        message.obj = response;
                        handler.sendMessage(message);
                    }
                }.start();
            }
        });
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BianjirijiActivity.this.finish();
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String resultTemp = (String) message.obj;
            Intent intent = new Intent();
            intent.setClass(BianjirijiActivity.this,ChakanrijiActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            /*MainActivity.update=1;
            ChakanrijiActivity chakanActivity = new ChakanrijiActivity();
            //chakanActivity.ChakanrijiActivity.finish();
            BianjirijiActivity.this.finish();*/

            intent.setClass(BianjirijiActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };
}
