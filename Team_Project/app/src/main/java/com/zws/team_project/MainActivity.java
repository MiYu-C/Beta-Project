package com.zws.team_project;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zws.team_project.com.orimol.classtemp.Diary;
import com.zws.team_project.layoututil.DiaryAdapter;

import java.util.List;

/**
 * Created by bruce on 2016/11/1.
 * HomeActivity 主界面
 */

public class MainActivity extends AppCompatActivity {
    public static Activity MainActivity;
    public static int update=0;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private List<Diary> diary;
    public static Diary onediary;
    private String get_diary;
    private String result;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String resultTemp = (String) message.obj;
            result = resultTemp.substring(1,resultTemp.length()-1).replaceAll("\\\\","");
            Gson g = new Gson();
            diary = g.fromJson(result, new TypeToken<List<Diary>>(){}.getType());
            ListView diaryListView = (ListView) findViewById(R.id.diaryListView);
            diaryListView.setAdapter(new DiaryAdapter(MainActivity.this, diary));
            // 日记的ListView点击事件监听
            diaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // 写在这里
                    onediary = diary.get(position);
                    Intent intent = new Intent(MainActivity.this,ChakanrijiActivity.class);
                    startActivity(intent);
                }
            });
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity = this;
        /** **/

        /******************************************************************/

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_nav_view);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_RiLi:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_RiJi:
                                viewPager.setCurrentItem(1);
                                new Thread() {
                                    public void run() {
                                        get_diary = "phoneNumber=" + LoginActivity.user.getPhoneNumber();
                                        String response = HttpUtil.doPostRequest(NetUtil.PATH_DIARY_GETALL, get_diary);
                                        Message message = Message.obtain();
                                        message.obj = response;
                                        handler.sendMessage(message);
                                    }
                                }.start();

                                break;
                            case R.id.navigation_RiPaiGN:
                                viewPager.setCurrentItem(2);


                                break;
                            case R.id.navigation_GengDuo:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
  //      viewPager.setOnTouchListener(new View.OnTouchListener() {
    //       @Override
      //     public boolean onTouch(View v, MotionEvent event) {
        //        return true;
    //       }
    //   });//

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new rili_frag());
        adapter.addFragment(new riji_frag());
        adapter.addFragment(new ripai_frag());
        adapter.addFragment(new gengduo_frag());
        viewPager.setAdapter(adapter);
    }

    public void shezhiBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, ShezhiActivity.class);
        startActivity(intent);
    }
    public void guihuaBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, SuoyouGuihuaActivity.class);
        startActivity(intent);
    }
    public void zujiBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, ZujiActivity.class);
        startActivity(intent);
    }
    public void beiwangBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, BeiwangActivity.class);
        startActivity(intent);
    }
    public void tashanzhishiBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, TashanZSActivity.class);
        startActivity(intent);
    }
    public void xinrijiBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, XinrijiActivity.class);
        startActivity(intent);
    }
    public void weiguicishuBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, WeiguiActivity.class);
        startActivity(intent);
    }
    public void wodedianshuBT(View v)
    {
        Intent intent = new Intent(MainActivity.this, DianshuActivity.class);
        startActivity(intent);
    }
    public void userinfo(View v){
        Intent intent = new Intent(MainActivity.this,UserInfoActivity.class);
        startActivity(intent);
    }


}
