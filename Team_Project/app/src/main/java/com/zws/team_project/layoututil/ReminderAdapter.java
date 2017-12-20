package com.zws.team_project.layoututil;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zws.team_project.R;
import com.zws.team_project.com.orimol.classtemp.Reminder;

import java.util.List;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

public class ReminderAdapter extends BaseAdapter {
    // Gson转化后的List集合
    private List<Reminder> reminders;
    // 固定写法，用于接收activity传递的值
    private Context context;

    public ReminderAdapter(Context context,List<Reminder> reminders){
        this.context = context;
        this.reminders = reminders;
    }
    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int position) {
        return reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = LayoutInflater.from(context).inflate(R.layout.beiwang_item, null);
        TextView beiwangTitle = (TextView) myView.findViewById(R.id.beiwangTitle);
        TextView beiwangDate = (TextView) myView.findViewById(R.id.beiwangDate);
        beiwangTitle.setText(reminders.get(position).getReminderTitle());
        beiwangDate.setText(reminders.get(position).getReminderDate());
        return myView;
    }
}
