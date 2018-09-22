package com.bwie.dongyushan.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.dongyushan.R;
import com.bwie.dongyushan.bean.UserBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<UserBean.DataBean> list;
    private Context context;

    public MyAdapter(List<UserBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.cb_02.setChecked(list.get(position).isMychecked());

        holder.shangdian_name_text.setText(list.get(position).getSellerName());
        holder.recyclerView_seed.setLayoutManager(new LinearLayoutManager(context));
        SeedAdapter seedAdapter = new SeedAdapter(list.get(position).getList(), context);
        holder.recyclerView_seed.setAdapter(seedAdapter);
    }


    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cb_02;
        private TextView shangdian_name_text;
        private RecyclerView recyclerView_seed;

        public MyViewHolder(View itemView) {
            super(itemView);
            shangdian_name_text=itemView.findViewById(R.id.shangdian_name_text);
            recyclerView_seed=itemView.findViewById(R.id.recyclerView_seed);
            cb_02 = itemView.findViewById(R.id.cb_02);
        }
    }
}
