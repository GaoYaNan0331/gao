package com.baway.zhoukaobgaoyanan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * data:2017/4/10
 * author:高亚男(Administrator)
 * function:
 */
public class MyListAdapter extends BaseAdapter{
    private NewBean newbean;
    private Context context;

    public MyListAdapter(NewBean  newbean, Context context) {
        this. newbean =  newbean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return  newbean.getResult().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return  newbean.getResult().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            v=new ViewHolder();
            v.iv= (ImageView) convertView.findViewById(R.id.image);
            v.tv1= (TextView) convertView.findViewById(R.id.te1);
            v.tv2= (TextView) convertView.findViewById(R.id.te2);
            convertView.setTag(v);
        }else{
            v= (ViewHolder) convertView.getTag();
        }
        v.tv1.setText(newbean.getResult().getData().get(position).getTitle());
        v.tv2.setText(newbean.getResult().getData().get(position).getAuthor_name());
       com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(newbean.getResult().getData().get(position).getThumbnail_pic_s(),v.iv, ImageLoader.getoptions(R.mipmap.ic_launcher));
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1,tv2;
    }
}

