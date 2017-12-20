package com.zws.team_project.layoututil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zws.team_project.R;
import com.zws.team_project.com.orimol.classtemp.Diary;

import java.util.List;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

public class DiaryAdapter extends BaseAdapter{
    private List<Diary> diary;
    // 固定写法，用于接收activity传递的值
    private Context context;

    public DiaryAdapter(Context context,List<Diary> diary){
        this.context = context;
        this.diary = diary;
    }
    @Override
    public int getCount() {
        return diary.size();
    }

    @Override
    public Object getItem(int position) {
        return diary.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = LayoutInflater.from(context).inflate(R.layout.diary_item, null);
        TextView diaryTitle = (TextView) myView.findViewById(R.id.diaryTitle);
        TextView diaryDate = (TextView) myView.findViewById(R.id.diaryDate);
        TextView diaryEmotion = (TextView) myView.findViewById(R.id.diaryEmotion);
        diaryTitle.setText(diary.get(position).getDiaryTitle());
        diaryDate.setText(diary.get(position).getDiaryDate());
        diaryEmotion.setText(diary.get(position).getEmotion());
        return myView;
    }
}
