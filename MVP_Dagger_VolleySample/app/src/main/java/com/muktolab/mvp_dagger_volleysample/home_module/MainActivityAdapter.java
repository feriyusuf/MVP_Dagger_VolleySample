package com.muktolab.mvp_dagger_volleysample.home_module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muktolab.mvp_dagger_volleysample.R;
import com.muktolab.mvp_dagger_volleysample.entities.DayWiseDataEntity;

import java.util.List;

/**
 * Created by skarim on 1/19/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {

    DayWiseDataEntity entity;
    List<DayWiseDataEntity> lisRoot;
    Context context;

    public MainActivityAdapter(Context context, List<DayWiseDataEntity> _listRoot) {
        this.context = context;
        lisRoot = _listRoot;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daywise_data_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        entity = lisRoot.get(position);

        holder.tvCode.setText("Code : " + entity.code);
        holder.tvDay.setText("Day : " + entity.day);
        holder.tvHigh.setText("High : " + entity.high);
        holder.tvLow.setText("Low : " + entity.low);
        holder.tvText.setText("Text : " + entity.text);
        holder.tvDate.setText("Date : " + entity.date);
    }

    @Override
    public int getItemCount() {
        return lisRoot.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode, tvText, tvLow, tvHigh, tvDay, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvCode = (TextView) view.findViewById(R.id.tvCode);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvDay = (TextView) view.findViewById(R.id.tvDay);
            tvHigh = (TextView) view.findViewById(R.id.tvHigh);
            tvLow = (TextView) view.findViewById(R.id.tvLow);
            tvText = (TextView) view.findViewById(R.id.tvText);

        }
    }
}
