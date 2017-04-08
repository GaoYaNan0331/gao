package com.baway.weektwo.msysntask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.baway.weektwo.Url.urlStr;
import com.baway.weektwo.adapter.MyAdapter;
import com.baway.weektwo.bean.GsonBean;
import com.baway.weektwo.http.HttpConnections;
import com.google.gson.Gson;

import java.util.List;

/**
 * data:2017/4/7
 * author:高亚男(Administrator)
 * function:
 */
public class MyAsycTask extends AsyncTask<String,Integer,String>{
    private Context context;
    private ListView lv;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };
    public MyAsycTask(Context context, ListView lv) {
        this.context = context;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s!=null){
            Gson gson=new Gson();
            GsonBean gsonBean = gson.fromJson(s, GsonBean.class);
            final List<GsonBean.ContentsBean> contents = gsonBean.getContents();
            MyAdapter adapter=new MyAdapter(context,contents);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context,contents.get(i).getId()+"",Toast.LENGTH_SHORT).show();
                }
            });

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
             GsonBean.ContentsBean contentsBean = contents.get(i);
                 contents.remove(contentsBean);
                 MyAdapter adapter=new MyAdapter(context,contents);
                 lv.setAdapter(adapter);

        return true;
    }
});
        }




    }

    @Override
    protected String doInBackground(String... strings) {

        String s = HttpConnections.doGet(urlStr.url);

        return s;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
