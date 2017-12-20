package com.zws.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zws.team_project.com.orimol.classtemp.Planning;
import com.zws.team_project.layoututil.PlanningAdapter;

import java.util.List;

public class SuoyouGuihuaActivity extends AppCompatActivity {

    private List<Planning> planning;
    //public Planning oneplanning;
    private String result;
    private String get_planning;
    private TextView isFinish;
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
                planning = g.fromJson(result, new TypeToken<List<Planning>>(){}.getType());
            }catch (Exception e){
                System.out.println("error");
            }
            set2();
        }
    };
    public void set2(){
        ListView planningListView = (ListView) findViewById(R.id.planningListView);
        planningListView.setAdapter(new PlanningAdapter(SuoyouGuihuaActivity.this, planning));
        // 规划的ListView
        planningListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suoyou_guihua);
        get_planning = "phoneNumber="+ LoginActivity.user.getPhoneNumber();
        new Thread() {
            public void run() {
                String response = HttpUtil.doPostRequest(NetUtil.PATH_PLAN_GETALL, get_planning);
                Message message = Message.obtain();
                message.obj = response;
                handler.sendMessage(message);
            }
        }.start();

        // *******************************//
//        ImageButton dacha = (ImageButton) findViewById(R.id.dacha);
//        ImageButton dagou = (ImageButton) findViewById(R.id.dagou);
//        isFinish = (TextView) findViewById(R.id.isFinish);
//        dacha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isFinish.setText("已删除");
//                // 执行删除语句块
//            }
//        });
//        dagou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isFinish.setText("已完成");
//                // 执行
//            }
//        });
//        ImageButton newPlanning = (ImageButton) findViewById(R.id.newPlanning);
//        newPlanning.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SuoyouGuihuaActivity.this,XinguihuaActivity.class);
//                startActivity(intent);
//            }
//        });
        //ImageButton fanhui1 = (ImageButton) findViewById(R.id.fanhui1);
        //fanhui1.setOnClickListener(new View.OnClickListener() {

    }
    public void guihuaBT(View v)
    {
        //Intent intent = new Intent(ShezhiActivity.this, MainActivity.class);
        //startActivity(intent);
        SuoyouGuihuaActivity.this.finish();
    }
    public void xinguihuaBT(View v)
    {
        Intent intent = new Intent(SuoyouGuihuaActivity.this, XinguihuaActivity.class);
        startActivity(intent);
    }
    public void back(View view){
        SuoyouGuihuaActivity.this.finish();
    }
}
