package com.baway.weektwo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.weektwo.R;
import com.baway.weektwo.bean.GsonBean;

import java.util.List;

/**
 * data:2017/4/7
 * author:高亚男(Administrator)
 * function:
 */
public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<GsonBean.ContentsBean> contents;

    public MyAdapter(Context context, List<GsonBean.ContentsBean> contents) {
        this.context = context;
        this.contents = contents;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int i) {
        return contents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            view=View.inflate(context, R.layout.list_item,null);
            holder=new ViewHolder();
            holder.t1= (TextView) view.findViewById(R.id.text1);
            holder.t2= (TextView) view.findViewById(R.id.text2);
            view.setTag(holder);

        }else{
           holder= (ViewHolder) view.getTag();

        }
        holder.t1.setText(contents.get(i).getName());
        holder.t2.setText(contents.get(i).getSort());
        return view;
    }
 class ViewHolder{
     TextView t1,t2;
 }
}
