package com.zws.team_project;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by 45度炸 on 2017/11/13.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class gengduo_frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gengduo_frag, container, false);
    }
/*
    public void fanhui(View v)
    {
        Intent intent = new Intent(gengduo_frag.this, ZujiActivity.class);
        startActivity(intent);

    }
*/
}