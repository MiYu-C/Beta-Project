package com.zws.team_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.zws.team_project.R;
import com.zws.team_project.com.orimol.classtemp.User;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        login();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);
            result = (String) message.obj;
            result = result.substring(1,result.length()-1).replaceAll("\\\\","");
            System.out.println(result);
            try{
                Gson gson = new Gson();
                user  =  gson.fromJson(result, User.class);//这里有问题，需要你们自行研究！！
                LoginActivity.user=user;
                Intent intent =new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }catch (Exception e){

            }
        }
    };
    public User user;
    private String result=null;
    private SharedPreferences sp;
    private String phone_number;
    private void login(){
        sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        phone_number = sp.getString("user_phone",null);
        if(phone_number!=null){
            final String find_user = "phoneNumber=" + phone_number;
            new Thread() {
                public void run() {
                    String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_FIND, find_user);
                    Message message = Message.obtain();
                    message.obj = response;
                    handler.sendMessage(message);
                }
            }.start();
        }
        else{
            Intent intent =new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }
    }
}
