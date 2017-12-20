package com.zws.team_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zws.team_project.com.orimol.classtemp.User;


public class LoginActivity extends AppCompatActivity {

    private String result=null;
    private EditText usernameET,pwdET;
    private String username,pwd;
    private boolean isLogin = false;			//传递给fragment的参数，判断是否登录
    private SharedPreferences preferences;
    private ProgressDialog dialog;
    public static User user;
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

                Toast.makeText(LoginActivity.this,
                        "登录用户："+user.getPhoneNumber(),
                        // 设置该Toast提示信息的持续时间,
                        Toast.LENGTH_SHORT).show();
                /** by chj **/
                SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("user_phone", user.getPhoneNumber());
                editor.commit();
                /** **/
                //新建一个显式意图，第一个参数为当前Activity类对象，第二个参数为你要打开的Activity类
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                //用Bundle携带数据
                Bundle bundle=new Bundle();
                //传递数据
                bundle.putString("phoneNumber", username);
                intent.putExtras(bundle);
                startActivity(intent);
                //LoginActivity.this.setVisible(false);
            } catch (Exception e){
                Toast.makeText(LoginActivity.this,
                        "用户名/密码错误",
                        // 设置该Toast提示信息的持续时间,
                        Toast.LENGTH_SHORT).show();
            }

            System.out.println("登录用户："+user);//放在SharedPreferences下面，在退出登录的时候删除SharedPreferences下的登录用户
            System.out.println(result);
            //System.out.println(user.getPhoneNumber());
            /*if(user==null){
                Toast.makeText(LoginActivity.this,
                        "用户名/密码错误",
                        // 设置该Toast提示信息的持续时间,
                        Toast.LENGTH_SHORT).show();

            }*/
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById();
    }
    public void findViewById(){
        usernameET = (EditText) this.findViewById(R.id.username);
        pwdET = (EditText) this.findViewById(R.id.password);


    }
    public void onLogin(View v)
    {
        switch (v.getId()) {
            case R.id.Login://登录

                username = usernameET.getText().toString();
                pwd = pwdET.getText().toString();


                final String user = "userName="+username+"&userPassword="+pwd;/*@RequestParam(value = "userName") String userName, @RequestParam(value = "userPassword") String userPassword,*/

                if(username!= null && !username.equals("") && pwd!=null && !pwd.equals("")){
                    /*这里需要留意的是httpPostUtils请求在Android里面不能放在主线程里面，必须新建一个子线程，然后通过Hanlder把子线程的值传过来更新UI（因为子线程不能直接更改UI）*/
                    new Thread() {
                        public void run() {
                            String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_LOGIN, user);
                            Message message = Message.obtain();
                            message.obj = response;
                            handler.sendMessage(message);
                        }
                    }.start();
                }
                else{
                    System.out.println("用户名密码不能为空");
                    Toast.makeText(LoginActivity.this,
                            "用户名密码不能为空",
                            // 设置该Toast提8示信息的持续时间,
                            Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }
    public void backmain(View v){
        /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);*/
        LoginActivity.this.finish();
    }
    public void newUser(View v){
        Intent intent = new  Intent(LoginActivity.this,NewUserActivity.class);
        startActivity(intent);
    }
    public void forgetPassword(View v){
//        Intent intent = new  Intent(LoginActivity.this,FpswdActivity.class);
//        startActivity(intent);
    }
}
