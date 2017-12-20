package com.zws.team_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zws.team_project.com.orimol.classtemp.Reminder;
import com.zws.team_project.layoututil.ReminderAdapter;

import java.util.List;


public class BeiwangActivity extends AppCompatActivity {

    private String result;
    public static Reminder onereminder;
    private List<Reminder> reminder;
    private String get_reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiwang);
        // handle里面返回所有的备忘录
        get_reminder = "phoneNumber="+ LoginActivity.user.getPhoneNumber();
        new Thread() {
            public void run() {
                String response = HttpUtil.doPostRequest(NetUtil.PATH_REMINDER_GETALL, get_reminder);
                Message message = Message.obtain();
                message.obj = response;
                handler.sendMessage(message);
            }
        }.start();
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BeiwangActivity.this.finish();
            }
        });

    }
    public void beiwangBT(View v)
    {
        BeiwangActivity.this.finish();
    }
    public void xinbeiwangBT(View v)
    {
        Intent intent = new Intent(BeiwangActivity.this, xinbeiwangActivity.class);
        startActivity(intent);
    }


    public void showdialog(View view)
    {
       // final EditText et1 = new EditText(this);
        new AlertDialog.Builder(this).setTitle("添加")
                .setIcon(android.R.drawable.ic_dialog_info)
               // .setView(et1)

                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        /*
                        String input1 = et1.getText().toString();

                        if (input1.equals("")) {
                            Toast.makeText(getApplicationContext(), "内容不能为空！" + input1, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Intent intent = new Intent();
                            intent.putExtra("content", input1);
                            //intent.setClass(BeiwangActivity.this, ShezhiActivity.class);
                            startActivity(intent);
                        }*/
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);
            String resultTemp = (String) message.obj;
            System.out.println("list"+resultTemp);
            //result = resultTemp.substring(1,result.length()-1).replaceAll("\\\\","");
            Gson g = new Gson();
            //String str = "[{\"phoneNumber\":\"123\",\"reminderDate\":\"000\",\"reminderText\":\"aasd\",\"reminderTitle\":\"sd\",\"systemID\":1},{\"phoneNumber\":\"123\",\"reminderDate\":\"2017/11/12_02:22:01\",\"reminderText\":\"买买买\",\"reminderTitle\":\"双十一\",\"systemID\":2}]";
            // 从服务器上获取json数据，利用gson转化为String类型的数据,需要把str改成result来获取服务器的String信息
            try{
                result = resultTemp.substring(1,resultTemp.length()-1).replaceAll("\\\\","");
                reminder = g.fromJson(result, new TypeToken<List<Reminder>>(){}.getType());
            }catch (Exception e){
                System.out.println("error");
            }
            set();
        }
    };

    public void set(){
        ListView reminderListView = (ListView) findViewById(R.id.reminderListView);
        reminderListView.setAdapter(new ReminderAdapter(BeiwangActivity.this, reminder));
        // 备忘的的ListView监听
        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 监听内容
                onereminder = reminder.get(position);
                Intent intent = new Intent(BeiwangActivity.this,ChakanbeiwangActivity.class);
                startActivity(intent);
            }
        });
    }
}
