package com.cj.videoanalysis.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cj.videoanalysis.R;

/**
 * 作者：陈骏
 * 创建时间：2018/1/11. 9:47
 * QQ：200622550
 * 博客：https://www.jianshu.com/u/c5ada9939f6d
 * 个人网站：www.coocj.top
 * 作用：小影解析
 */

public class XiaoYinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoyin);
        this.setTitle("小影解析");
    }
}