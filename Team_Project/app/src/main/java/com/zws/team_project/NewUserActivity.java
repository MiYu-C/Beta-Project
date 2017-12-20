package com.zws.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zws.team_project.com.orimol.classtemp.User;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        findViewById();
    }
    private String result=null;
    private EditText phoneNumber,passWord,passWord_2;
    private String phone,pwd,pwd_2;//,name="空",email="空",sex="空";
    public User user;

    public void findViewById(){
        phoneNumber = (EditText) this.findViewById(R.id.phoneNumber);
        passWord = (EditText) this.findViewById(R.id.passWord);
        passWord_2 = (EditText) this.findViewById(R.id.passWord_2);


    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);
            result = (String) message.obj;
            result = result.substring(1,result.length()-1).replaceAll("\\\\","");
            System.out.println(result);
            try{
                Gson gson = new Gson();
                user  =  gson.fromJson(result, User.class);//这里有问题，需要你们自行研究！！
                if(user==null){
                    Toast.makeText(NewUserActivity.this,
                            "手机号： "+phone+" 可用",
                            // 设置该Toast提示信息的持续时间,
                            Toast.LENGTH_SHORT).show();
                    //新建一个显式意图，第一个参数为当前Activity类对象，第二个参数为你要打开的Activity类
                    Intent intent =new Intent(NewUserActivity.this,SetUserActivity.class);
                    //用Bundle携带数据
                    Bundle bundle=new Bundle();
                    //传递数据
                    bundle.putString("phoneNumber", phone);
                    bundle.putString("passWord", pwd);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(NewUserActivity.this,
                            "手机号： "+phone+" 已被注册",
                            // 设置该Toast提示信息的持续时间,
                            Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){

                Toast.makeText(NewUserActivity.this,
                        "注册失败",
                        // 设置该Toast提示信息的持续时间,
                        Toast.LENGTH_SHORT).show();
            }
        }

    };


    public void new_user(View view){
        switch (view.getId()) {
            case R.id.new_user:

                phone = phoneNumber.getText().toString();
                System.out.println(phone);
                pwd = passWord.getText().toString();
                pwd_2 = passWord_2.getText().toString();
                /**
                 * 寻找用户
                 */
                final String find_user = "phoneNumber=" + phone;
                if (pwd.equals(pwd_2) && phone != null && !phone.equals("") && pwd != null && !pwd.equals("")){
                    new Thread() {
                        public void run() {
                            String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_FIND, find_user);
                            Message message = Message.obtain();
                            message.obj = response;
                            handler.sendMessage(message);
                        }
                    }.start();
                }

                /*final String new_user = "userName="+name+"&phoneNumber="+phone+"&passWord="+pwd+"&email="+email+"&sex="+sex;
                /**
                 * 注册用户
                 */
                /*if(pwd.equals(pwd_2) && phone!=null && !phone.equals("") && pwd!=null && !pwd.equals("")){
                    new Thread() {
                        public void run() {

                            String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_NEW, new_user);
                            Message message = Message.obtain();
                            message.obj = response;
                            handler.sendMessage(message);
                        }
                    }.start();
                }*/
                else{
                    System.out.println("用户名密码不能为空,且两次密码要相同");
                    Toast.makeText(NewUserActivity.this,
                            "用户名密码不能为空,且两次密码要相同",
                            // 设置该Toast提示信息的持续时间,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    public void back(View view){
        this.finish();
    }
}
