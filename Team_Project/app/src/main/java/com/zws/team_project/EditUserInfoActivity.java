package com.zws.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zws.team_project.com.orimol.classtemp.User;

public class EditUserInfoActivity extends AppCompatActivity {

    private String phone,result;
    private User user;
    EditText username,email,sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        phone = bundle.getString("phoneNumber");
        user=UserInfoActivity.getUser();
        setTextView();
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
                user  =  gson.fromJson(result, User.class);//这里有问题，需要你们自行研究！

            } catch (Exception e){

            }
            Intent intent = new Intent();
            intent.setClass(EditUserInfoActivity.this,UserInfoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };

    public void edit(View view) {
        String user_name,user_email,user_sex;
        user_name=username.getText().toString();
        user_email=email.getText().toString();
        user_sex=sex.getText().toString();

        final String edit = "userName=" + user_name +"&phoneNumber=" + phone +"&email="+user_email+"&sex="+user_sex;
        new Thread() {
            public void run() {
                String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_EDIT, edit);
                Message message = Message.obtain();
                message.obj = response;
                handler.sendMessage(message);
            }
        }.start();
    }

    public void setTextView(){
        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        sex=(EditText)findViewById(R.id.sex);
        username.setText(user.getUserName());
        email.setText(user.getEmail());
        sex.setText(user.getSex());
    }
    public void back(View view){
        EditUserInfoActivity.this.finish();
    }
}
