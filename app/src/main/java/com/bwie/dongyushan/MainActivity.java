package com.bwie.dongyushan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.dongyushan.adapter.MyAdapter;
import com.bwie.dongyushan.bean.UserBean;
import com.bwie.dongyushan.liu.LiuShi;
import com.bwie.dongyushan.presenter.MyPresenter;
import com.bwie.dongyushan.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyView{


    private RecyclerView recyclerView;
    private MyPresenter myPresenter;
    private List<UserBean.DataBean> data;
    private List<UserBean> list01;
    private EditText sousuo;
    private Button sousuo_btn;
    private LiuShi liuShi;
    private List<String> list=new ArrayList<>();
    private TextView qingkong;
    private CheckBox cb_01;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);
        myPresenter = new MyPresenter(this);
        liuShi = findViewById(R.id.liushi);
        sousuo=findViewById(R.id.sousuo);
        sousuo_btn=findViewById(R.id.sousuo_btn);
        qingkong = findViewById(R.id.qingkong);
        cb_01 = findViewById(R.id.cb_01);
        cb_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb_01.isChecked()){

                    for (int i = 0; i <data.size() ; i++) {

                        data.get(i).setMychecked(true);

                        for (int j = 0; j <data.get(i).getList().size() ; j++) {

                            data.get(i).getList().get(j).setSeedchecked(true);

                        }

                    }

                }else {

                    for (int i = 0; i <data.size() ; i++) {

                        data.get(i).setMychecked(false);

                        for (int j = 0; j <data.get(i).getList().size() ; j++) {

                            data.get(i).getList().get(j).setSeedchecked(false);

                        }

                    }



                }

                adapter.notifyDataSetChanged();



            }
        });
        qingkong = findViewById(R.id.qingkong);

        qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                liuShi.removeAllViews();
            }
        });

        sousuo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st= sousuo.getText().toString();
                list.add(st);
                liuShi.removeAllViews();
                for (int i=0;i<list.size();i++){

                    TextView tv=new TextView(MainActivity.this);
                    tv.setText(list.get(i));
                    liuShi.addView(tv);
                }
                liuShi.setPadding(5,5,5,5);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        myPresenter.show();
    }



    /*cb_01.setOnClickListener(new View.OnClickListener() {

        @Override

        public void onClick(View v) {

            if(cb_01.isChecked()){

                for (int i = 0; i <news.getData().size() ; i++) {

                    news.getData().get(i).setOutchecked(true);

                    for (int j = 0; j <news.getData().get(i).getList().size() ; j++) {

                        news.getData().get(i).getList().get(j).setInnerchecked(true);

                    }

                }

            }else {

                for (int i = 0; i <news.getData().size() ; i++) {

                    news.getData().get(i).setOutchecked(false);

                    for (int j = 0; j <news.getData().get(i).getList().size() ; j++) {

                        news.getData().get(i).getList().get(j).setInnerchecked(false);

                    }

                }



            }

            adapter.notifyDataSetChanged();

        }

    });*/

    //成功
    @Override
    public void success(final UserBean userBean) {
        runOnUiThread(new Runnable() {



            @Override
            public void run() {
                data = userBean.getData();
              //  Toast.makeText(MainActivity.this,data.size()+"",Toast.LENGTH_SHORT).show();
                adapter = new MyAdapter(data, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    //失败
    @Override
    public void failure(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
