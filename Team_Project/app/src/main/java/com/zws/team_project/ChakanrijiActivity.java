package com.zws.team_project;

import android.app.Activity;
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

public class ChakanrijiActivity extends AppCompatActivity {
    public static Activity ChakanrijiActivity;
    private String deleteDiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakanriji);
        ChakanrijiActivity=this;
        // 显示当前选中日记的具体内容
        TextView showDiaryTitle = (TextView) findViewById(R.id.showDiaryTitle);
        TextView showDiaryContent = (TextView) findViewById(R.id.showDiaryContent);
        TextView showDiaryDate = (TextView) findViewById(R.id.showDiaryDate);
        TextView showDiaryEmotion = (TextView) findViewById(R.id.showDiaryEmotion);
        TextView secret = (TextView) findViewById(R.id.secret);
        showDiaryTitle.setText(MainActivity.onediary.getDiaryTitle());
        showDiaryDate.setText(MainActivity.onediary.getDiaryDate());
        showDiaryContent.setText(MainActivity.onediary.getDiaryText());
        showDiaryEmotion.setText(MainActivity.onediary.getEmotion());
        if (MainActivity.onediary.isVisibility()) {
            secret.setText("公开");
        } else {
            secret.setText("私密");
        }
        ImageButton bianjiriji = (ImageButton) findViewById(R.id.bianjiriji);
        bianjiriji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChakanrijiActivity.this,BianjirijiActivity.class);
                startActivity(intent);
            }
        });

        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChakanrijiActivity.this.finish();
            }
        });
    }

    public void shanchuriji(View view)
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
            // ***************************debug
            deleteDiary = "systemID=" + MainActivity.onediary.getSystemID();
            new Thread() {
                public void run() {
                    String response = HttpUtil.doPostRequest(NetUtil.PATH_DIARY_DELETE, deleteDiary);
                    Message message = Message.obtain();
                    message.obj = response;
                    handler.sendMessage(message);
                }
            }.start();
            Intent intent = new Intent();
            intent.setClass(ChakanrijiActivity.this,MainActivity.class);
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
            String result = (String) message.obj;
        }
    };
}
