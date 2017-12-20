package com.zws.team_project.layoututil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zws.team_project.R;
import com.zws.team_project.com.orimol.classtemp.Planning;

import java.util.List;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

public class PlanningAdapter extends BaseAdapter {
    private List<Planning> planning;
    // 固定写法，用于接收activity传递的值
    private Context context;

    public PlanningAdapter(Context context,List<Planning> planning){
        this.context = context;
        this.planning = planning;
    }
    @Override
    public int getCount() {
        return planning.size();
    }

    @Override
    public Object getItem(int position) {
        return planning.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = LayoutInflater.from(context).inflate(R.layout.planning_item, null);
        TextView SystemcDate = (TextView)myView.findViewById(R.id.SystemcDate);
        TextView isShortPlaning = (TextView)myView.findViewById(R.id.isShortPlanning);
        TextView planningDate = (TextView)myView.findViewById(R.id.planningDate);
        TextView planningText = (TextView)myView.findViewById(R.id.planningText);
        TextView isFinish = (TextView)myView.findViewById(R.id.isFinish);
        SystemcDate.setText(planning.get(position).getPlanningDate());
        planningDate.setText(planning.get(position).getPlanningDate());
        planningText.setText(planning.get(position).getPlanningText());
        if (planning.get(position).isShortPlaning() == true) {
            isShortPlaning.setText("长期");
        } else {
            isShortPlaning.setText("短期");
        }

        if (planning.get(position).isFinish() == 0) {
            isFinish.setText("未完成");
        }
        if (planning.get(position).isFinish() == 1) {
            isFinish.setText("已完成");
        }
        if (planning.get(position).isFinish() == 2) {
            isFinish.setText("已删除");
        }
        return myView;

    }
}
