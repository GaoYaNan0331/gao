package com.baway.weektwo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.baway.weektwo.msysntask.MyAsycTask;

public class MainActivity extends AppCompatActivity {
private Handler handler=new Handler(){


};
    private ListView mLv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.lv);
        MyAsycTask myAsycTask=new MyAsycTask(MainActivity.this,mLv);
        myAsycTask.execute();
    }
}
