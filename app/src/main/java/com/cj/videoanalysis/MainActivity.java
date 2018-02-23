package com.cj.videoanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cj.videoanalysis.ui.DouYinActivity;
import com.cj.videoanalysis.ui.HuoShanActivity;
import com.cj.videoanalysis.ui.KuaiShouActivity;
import com.cj.videoanalysis.ui.MeiPaiActivity;
import com.cj.videoanalysis.ui.MiaoPaiActivity;
import com.cj.videoanalysis.ui.MoMoActivity;
import com.cj.videoanalysis.ui.MuseActivity;
import com.cj.videoanalysis.ui.TouTiaoActivity;
import com.cj.videoanalysis.ui.XiaoYinActivity;
import com.cj.videoanalysis.ui.YingKeActivity;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("视频解析列表");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item, getItemDatas()) {
            @Override
            protected void convert(final BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item_text, item);

                helper.setOnClickListener(R.id.cv_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = null;
                        switch (helper.getAdapterPosition()) {
                            case 0:
                                intent = new Intent(MainActivity.this, DouYinActivity.class);
                                break;
                            case 1:
                                intent = new Intent(MainActivity.this, MuseActivity.class);
                                break;
                            case 2:
                                intent = new Intent(MainActivity.this, TouTiaoActivity.class);
                                break;
                            case 3:
                                intent = new Intent(MainActivity.this, MiaoPaiActivity.class);
                                break;
                            case 4:
                                intent = new Intent(MainActivity.this, HuoShanActivity.class);
                                break;
                            case 5:
                                intent = new Intent(MainActivity.this, KuaiShouActivity.class);
                                break;
                            case 6:
                                intent = new Intent(MainActivity.this, YingKeActivity.class);
                                break;
                            case 7:
                                intent = new Intent(MainActivity.this, MoMoActivity.class);
                                break;
                            case 8:
                                intent = new Intent(MainActivity.this, MeiPaiActivity.class);
                                break;
                            case 9:
                                intent = new Intent(MainActivity.this, XiaoYinActivity.class);
                                break;
                        }
                        startActivity(intent);
                    }
                });
                mAdapter.isFirstOnly(false);
                mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            }
        });
    }

    /**
     * 初始化数据
     * @return
     */
    public static List<String> getItemDatas() {
        List<String> mList = new ArrayList<>();
        mList.add("抖音");
        mList.add("muse");
        mList.add("头条、西瓜、内涵、阳光宽频");
        mList.add("微博、秒拍、小咖秀");
        mList.add("火山");
        mList.add("快手");
        mList.add("映客");
        mList.add("陌陌");
        mList.add("美拍");
        mList.add("小影");
        return mList;
    }
}
