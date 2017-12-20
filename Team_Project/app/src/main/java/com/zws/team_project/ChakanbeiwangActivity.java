package com.zws.team_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChakanbeiwangActivity extends AppCompatActivity {
    private String deleteBeiwang;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakanbeiwang);
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChakanbeiwangActivity.this.finish();
            }
        });
        TextView showReminderTitle = (TextView)findViewById(R.id.showReminderTitle);
        TextView showReminderDate = (TextView) findViewById(R.id.showReminderDate);
        TextView showReminderText = (TextView) findViewById(R.id.showReminderText);
        showReminderDate.setText(BeiwangActivity.onereminder.getReminderDate());
        showReminderTitle.setText(BeiwangActivity.onereminder.getReminderTitle());
        showReminderText.setText(BeiwangActivity.onereminder.getReminderText());
    }

    public void shanchu(View view)
    {
        // 添加如何从数据库删除界面
        //Toast.makeText(this,"clickme",Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setMessage("是否删除？");
        alertdialogbuilder.setPositiveButton("确定", click1);
        alertdialogbuilder.setNegativeButton("取消", click2);
        AlertDialog alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();
    }

    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface arg0,int arg1)
        {
            //android.os.Process.killProcess(android.os.Process.myPid());
            // ****************************debug
            deleteBeiwang = "systemID=" + BeiwangActivity.onereminder.getSystemID();
            new Thread() {
                public void run() {
                    String response = HttpUtil.doPostRequest(NetUtil.PATH_REMINDER_DELETE, deleteBeiwang);
                    Message message = Message.obtain();
                    message.obj = response;
                    handler.sendMessage(message);
                }
            }.start();
            Intent intent = new Intent();
            intent.setClass(ChakanbeiwangActivity.this,BeiwangActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    };
    private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener()
    {@Override

    public void onClick(DialogInterface arg0,int arg1)
    {
        //arg0.cancel();
    }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            result = (String) message.obj;
            System.out.println(result);
            result = result.substring(1,result.length()-1).replaceAll("\\\\","");
        }
    };
}
