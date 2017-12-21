package com.zws.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

public class SetUserActivity extends AppCompatActivity {

    private String result=null;
    private EditText usernameET,emailET;
    private EditText phoneNumber,passWord,passWord_2;
    private String phone,pwd,name="空",email="空",sex="空";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        phone = bundle.getString("phoneNumber");
        pwd =  bundle.getString("passWord");
        /** **/
        RadioGroup group = (RadioGroup)this.findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1){
                // TODO Auto-generated method stub
                // 获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton)SetUserActivity.this.findViewById(radioButtonId);
                sex=(String)rb.getText();
                System.out.println(sex);
            }
        });
        /** **/
        usernameET = (EditText) this.findViewById(R.id.editText2);
        emailET = (EditText) this.findViewById(R.id.editText3);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);
            result = (String) message.obj;
            result = result.substring(1,result.length()-1).replaceAll("\\\\","");
            System.out.println(result);
            if(result.equals(phone)){
                Toast.makeText(SetUserActivity.this,
                        "注册用户手机号："+phone,
                        // 设置该Toast提示信息的持续时间,
                        Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(SetUserActivity.this,LoginActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(intent);

                //SetUserActivity.this.finish();
            }
            else{
                Toast.makeText(SetUserActivity.this,
                        "注册失败",
                        // 设置该Toast提示信息的持续时间,
                        Toast.LENGTH_SHORT).show();
                /*Intent intent =new Intent(SetUserActivity.this,NewUserActivity.class);
                startActivity(intent);*/
                SetUserActivity.this.finish();
            }

        }


    };
    public void new_user(View view){
        switch (view.getId()){
            case R.id.new_user:
                name = usernameET.getText().toString();
                email = emailET.getText().toString();
                System.out.println(phone+name+email);
                System.out.println(phone);

                final String new_user = "userName="+name+"&phoneNumber="+phone+"&passWord="+pwd+"&email="+email+"&sex="+sex;
                /**
                 * 注册用户
                 */

                new Thread() {
                    public void run() {

                        String response = HttpUtil.doPostRequest(NetUtil.PATH_USER_NEW, new_user);
                        Message message = Message.obtain();
                        message.obj = response;
                        handler.sendMessage(message);
                    }
                }.start();

                break;
            default:
                break;
        }
    }
    public void back(View view){
        this.finish();
    }
}
