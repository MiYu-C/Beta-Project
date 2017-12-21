package com.zws.team_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zws.team_project.com.orimol.classtemp.User;

public class UserInfoActivity extends AppCompatActivity {

    private static User user_info;
    private String phone,result;
    TextView user_name,phone_number,email,sex;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        phone="123";
        find_user(phone);
    }

    private void find_user(String phone) {
        final String find_user = "phoneNumber=" + phone;
        new Thread() {
            public void run() {
                String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_FIND, find_user);
                Message message = Message.obtain();
                message.obj = response;
                handler.sendMessage(message);
            }
        }.start();
    }



    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            result = (String) message.obj;
            System.out.println(result);
            result = result.substring(1,result.length()-1).replaceAll("\\\\","");
            System.out.println(result.length());
            try{
                Gson gson = new Gson();
                user  =  gson.fromJson(result, User.class);//这里有问题，需要你们自行研究！！
                user_info=user;
                setTextView();
            }catch (Exception e){

            }
        }
    };

    public void setTextView(){
        user_name=(TextView)findViewById(R.id.user_name);
        phone_number=(TextView)findViewById(R.id.phone_number);
        email=(TextView)findViewById(R.id.email);
        sex=(TextView)findViewById(R.id.sex);
        user_name.setText(user.getUserName());
        phone_number.setText(user.getPhoneNumber());
        email.setText(user.getEmail());
        sex.setText(user.getSex());
    }
    public void back(View v){
        UserInfoActivity.this.finish();
    }
    public void edit(View v){
        Intent intent = new Intent(UserInfoActivity.this, EditUserInfoActivity.class);
        //用Bundle携带数据
        Bundle bundle=new Bundle();
        //传递数据
        bundle.putString("phoneNumber", user.getPhoneNumber());
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public static User getUser(){
        return user_info;
    }
    public void exit(View view){
        SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit(); editor.clear(); editor.commit();
        Intent intent = new Intent();
        intent.setClass(UserInfoActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //SysApplication.getInstance().exit();
        MainActivity mainActivity = new MainActivity();
        mainActivity.MainActivity.finish();
        UserInfoActivity.this.finish();
    }
}
