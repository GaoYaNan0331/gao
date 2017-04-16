package com.baway.gaoyanan0415a;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.baway.gaoyanan0415a.Adpter.MyCBaseAdpter;
import com.baway.gaoyanan0415a.Beans.Beans;
import com.baway.gaoyanan0415a.Utils.UrlUtils;
import com.baway.gaoyanan0415a.myfragment.MyFragment;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView chbutton;
    private List<Beans.DataBean> clist;
    private List<MyFragment> fragments;
    private List<String> urls;
    private ListView clv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        chbutton = (ImageView) findViewById(R.id.cehua);
        clist = new ArrayList<Beans.DataBean>();
        urls = new ArrayList<String>();
        jxcdata();
        urls.add(UrlUtils.irl2);
        urls.add(UrlUtils.url3);
        urls.add(UrlUtils.url4);
        fragments = new ArrayList<>();
        for (int i =0;i<urls.size();i++){
             fragments.add(new MyFragment(urls.get(i)));
        }
    }

    public void jxcdata(){
        Handler mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    String messges = (String) msg.obj;
                    Gson gson = new Gson();
                    Beans beans = gson.fromJson(messges, Beans.class);
                    List<Beans.DataBean> data = beans.getData();
                    for (int i = 0; i < data.size(); i++) {
                        Beans.DataBean bd = new Beans.DataBean();
                        String name = data.get(i).getName();
                        bd.setName(name);
                        clist.add(bd);
                    }
                }
                clv.setAdapter(new MyCBaseAdpter(MainActivity.this, clist));
            }

        };
        new HttpConnUtils(UrlUtils.url,mhandler).start();

    }

    private void initView() {
        final SlidingMenu slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        int width = getPing();
        slidingMenu.setBehindOffset(width/4);
        slidingMenu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.cehua_activity);
        chbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenu.toggle();
            }
        });
        clv = (ListView) slidingMenu.findViewById(R.id.clv);


        clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                FragmentTransaction replace = transaction.replace(R.id.frame, fragments.get(i));
                transaction.commit();
            }
        });
    }
    //侧滑设置方法
    public int getPing(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
}
