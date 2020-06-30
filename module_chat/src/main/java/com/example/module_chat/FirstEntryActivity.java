package com.example.module_chat;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.module_chat.fragment.ChatFragment;

import example.common_base.base.BaseActivity;
import example.common_base.base.BasePresenter;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FirstEntryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ChatFragment meFragment = new ChatFragment();
        fragmentTransaction.add(R.id.fl, meFragment);
        fragmentTransaction.commit();
    }

    @Override
    public int initLayout() {
        return R.layout.activity_module_fristentry;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
