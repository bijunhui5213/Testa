package com.example.youfeadaway.geeknews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.request.Request;
import com.example.youfeadaway.geeknews.R;
import com.example.youfeadaway.geeknews.adapter.RvShowAdapter;
import com.example.youfeadaway.geeknews.bean.GoldShowBean;
import com.example.youfeadaway.geeknews.utils.Constants;
import com.example.youfeadaway.geeknews.widget.SimpleTouchHelperCallBack;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rv;
    private RvShowAdapter adapter;
    private ArrayList<GoldShowBean> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        list = (ArrayList<GoldShowBean>) intent.getSerializableExtra(Constants.DATA);
//        Log.e("-----", "initView: list列表数据"+list.get(0).title );
        rv = (RecyclerView) findViewById(R.id.rv);

        adapter = new RvShowAdapter(list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("特别页面展示");
        toolbar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack =
                new SimpleTouchHelperCallBack(adapter);
        simpleTouchHelperCallBack.setmSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);
        helper.attachToRecyclerView(rv);

    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, list);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}
