package com.example.administrator.recyclerviewcheckbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.recyclerviewcheckbox.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data:2017/5/12
 * author:高亚男(Administrator)
 * function:设置设配数据的适配器
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    //数据源
    private List<String> mList;
    private Context mContext;
    //设置复选框是否显示和隐藏，默认为false
    private boolean isCheckBox=false;
    //存储复选框选中状态的集合
    Map<Integer,Boolean> map=new HashMap<>();
    //接口回调设置的监听事件
    private RecyclerViewOnItemClickListener mOnItemClickListener;
    public MyAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
        //初始集合的数据
        initMap();

    }

    private void initMap() {
        for (int i = 0; i < mList.size(); i++) {
            map.put(i, false);
        }
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener!=null){
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClickListener(view, (Integer) view.getTag());
        }

    }
//长按点事件
    @Override
    public boolean onLongClick(View view) {
        //不管清空不清空都先刷新
        initMap();
        return mOnItemClickListener != null && mOnItemClickListener.onItemLongClickListener(view, (Integer) view.getTag());

    }
    //设置点击事件
    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    //设置是否显示CheckBox
    public void setShowBox() {
        //取反
        isCheckBox = !isCheckBox;
    }

    //点击item选中CheckBox
    public void setSelectItem(int position) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        notifyItemChanged(position);
    }

    //返回集合给MainActivity
    public Map<Integer, Boolean> getMap() {
        return map;
    }
    /**
     *
     * 自定义一个ViewHOlder用来设定控件的初始化
     */
    public class ViewHolder extends  RecyclerView.ViewHolder{
        private final TextView mTv;
        private final CheckBox mCb;

        public ViewHolder(View itemView) {
            super(itemView);
             mTv =  (TextView) itemView.findViewById(R.id.tv);
             mCb = (CheckBox) itemView.findViewById(R.id.cb);

        }

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到布局
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
       //实例化ViewHolder方便调用
        ViewHolder vh = new ViewHolder(inflate);
        //设置监听事件
        inflate.setOnClickListener(this);
        inflate.setOnLongClickListener(this);
        return vh;

    }

    /**
     * 绑定视图的管理者
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        /**
         * 将所需的item的控件添加到RecyclerView里面
         */
        holder.mTv.setText(mList.get(position));
        //判断复选框是否未显示或是隐藏
        if (isCheckBox){
            holder.mCb.setVisibility(View.VISIBLE);
        }else{

            holder.mCb.setVisibility(View.INVISIBLE);
        }
        //设置复选框的动画
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.list_anim);
        if (isCheckBox){
            holder.mCb.startAnimation(animation);
        }
        //将情况存入到tag绑定起来
        holder.itemView.setTag(position);
        //为checkBox设置监听事件
        holder.mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                //用map集合将复选框的状态存起来
                map.put(position,isCheckBox);
            }
        });
        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }

        holder.mCb.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
//设置接口
    public interface RecyclerViewOnItemClickListener{
//点击事件
    void onItemClickListener(View view, int position);
    //长按点击事件
    //长按事件
    boolean onItemLongClickListener(View view, int position);
    }
}
