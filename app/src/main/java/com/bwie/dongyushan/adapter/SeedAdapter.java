package com.bwie.dongyushan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.dongyushan.R;
import com.bwie.dongyushan.bean.UserBean;

import java.util.List;

public class SeedAdapter extends RecyclerView.Adapter<SeedAdapter.MyViewHolder2>{
    private List<UserBean.DataBean.ListBean> list;
    private Context context;

    public SeedAdapter(List<UserBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seed_item, parent, false);
        MyViewHolder2 myViewHolder2=new MyViewHolder2(view);
        return myViewHolder2;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder2 holder, int position) {

        holder.cb_03.setChecked(list.get(position).isseedchecked());

        holder.shangpin_price_text.setText(list.get(position).getPrice()+"");
        holder.shangpin_name_text.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String[] split=images.split("\\|");
        String a=split[0].toString();
        Log.i("aaa",a);
        Glide.with(context).load(split[0]).into(holder.shangpin_image);

        //点击加号
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(holder.num.getText().toString());
                num1+=2;
                holder.num.setText(num1+"");
            }
        });
        //点击减号
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(holder.num.getText().toString());
                if (num1<=1){
                }else {
                    num1-=1;
                    holder.num.setText(num1+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder{
        private CheckBox cb_03;
        private TextView shangpin_name_text,shangpin_price_text,num;
        private ImageView shangpin_image,jia,jian;

        public MyViewHolder2(View itemView) {
            super(itemView);
            shangpin_name_text = itemView.findViewById(R.id.shangpin_name_text);
            shangpin_price_text = itemView.findViewById(R.id.shangpin_price_text);
            shangpin_image = itemView.findViewById(R.id.shangpin_image);
            jia=itemView.findViewById(R.id.jia);
            jian=itemView.findViewById(R.id.jian);
            num=itemView.findViewById(R.id.num);
            cb_03 = itemView.findViewById(R.id.cb_03);
        }
    }


}
