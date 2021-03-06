package com.example.administrator.recyclerviewcheckbox;


import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.recyclerviewcheckbox.adapter.ListAdapter;
import com.example.administrator.recyclerviewcheckbox.adapter.MyAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mList;
    private RecyclerView mRecycfle;
    private ListAdapter mAdapter;
    private List<String> list;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
        findViewById(R.id.btn).setOnClickListener(this);
        initData();
        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new ListAdapter(list, this);
        recyclerView.setAdapter(adapter);
        //添加分割线
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        adapter.setRecyclerViewOnItemClickListener(new ListAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                //设置选中的项
                adapter.setSelectItem(position);
            }

            @Override
            public boolean onItemLongClickListener(View view, int position) {
                adapter.setShowBox();
                //设置选中的项
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.recycle:
                //获取你选中的item
                Map<Integer, Boolean> map = adapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    if (map.get(i)) {
                        Log.d("TAG", "你选了第：" + i + "项");
                    }

                break;

        }
            case R.id.btn:
                Toast.makeText(this, "已选择，请选择其他的", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //全选
            case R.id.all:
                Map<Integer, Boolean> map = adapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    map.put(i, true);
                    adapter.notifyDataSetChanged();
                }
                break;
            //全不选
            case R.id.no_all:
                Map<Integer, Boolean> m = adapter.getMap();
                for (int i = 0; i < m.size(); i++) {
                    m.put(i, false);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 为列表添加测试数据
     */
    private void initData() {
        File directory = Environment.getExternalStorageDirectory();
        File[] files = directory.listFiles();
        list = new ArrayList<>();
        for (File file : files) {
            list.add(file.getName());
        }
    }
}
