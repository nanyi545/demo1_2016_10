package com.webcon.sus.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webcon.sus.demo.R;
import com.webcon.sus.entity.StationNode;
import com.webcon.sus.utils.CommonUtils;
import com.webcon.sus.utils.OnRVItemClickListener;
import com.webcon.sus.utils.SUConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 站场列表Adapter，用于RecyclerView
 * @author m
 */
public class RVStationListAdapter extends RecyclerView.Adapter<RVStationListAdapter.RVHolder>{
    private static final String TAG = "RVStationListAdapter";

    private List<StationNode> mStations;

    public RVStationListAdapter(List<StationNode> mStations){
        if(mStations == null){
            this.mStations = new ArrayList<>();
        }else{
            this.mStations = mStations;
        }
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_station_list, parent, false);
        LinearLayout container = (LinearLayout)layout.findViewById(R.id.linear_card_container);

        // 创建holder
        RVHolder holder = new RVHolder(container);
        // 添加组件
//        holder.rpView = (com.andexert.library.RippleView)layout.findViewById(R.id.rippleview_container);  ripple view does not work here
        holder.cardview = (CardView)layout.findViewById(R.id.card_station);
        holder.state = (ImageView)layout.findViewById(R.id.station_item_state);
        holder.name = (TextView)layout.findViewById(R.id.station_item_name);
        holder.msg = (TextView)layout.findViewById(R.id.station_item_msg);
        holder.online = (TextView)layout.findViewById(R.id.station_item_online);
        holder.defence = (TextView)layout.findViewById(R.id.station_item_defence);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RVHolder holder, int position) {
        // 将数据与界面进行绑定的操作
        if(mOnItemClickListener != null){
            holder.cardview.setOnClickListener(new View.OnClickListener() {  //set the listener on the linear layout
//            holder.rpView.setOnClickListener(new View.OnClickListener() {   //set the listener on the ripple view
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });

            holder.cardview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(v, pos);
                    return false;
                }
            });
        }
        int alarms = mStations.get(position).getNewAlarm();
        holder.name.setText(mStations.get(position).getName());
        assignData(holder, mStations.get(position).isOnline(),
                mStations.get(position).getState(), alarms);
    }

    @Override
    public int getItemCount() {
        return mStations.size();
    }

    /**
     * 设置数据
     */
    private void assignData(RVHolder holder, boolean isOnline, int stateFlag, int alarms){
        Log.i(TAG, "alarms:" + alarms);
        if(isOnline){
            holder.name.setTextColor(SUConstant.COLOR_STATE_ONLINE);
            holder.msg.setVisibility(View.VISIBLE);
            holder.online.setText(CommonUtils.ORIGINAL_ONLINE);
            holder.online.setTextColor(SUConstant.COLOR_ALARM_PURPLE);
            holder.defence.setVisibility(View.VISIBLE);
            switch(stateFlag){
                case SUConstant.FLAG_STATION_CLOSED:
                    holder.state.setImageResource(SUConstant.STATION_STATE_ALARM_1);
                    if(alarms > 0){
                        holder.msg.setTextColor(SUConstant.COLOR_ALARM_ORANGE_FADE);
                        holder.msg.setText(CommonUtils.placeHolderSwitch(alarms));
                    }else{
                        holder.msg.setTextColor(SUConstant.COLOR_ALARM_GREEN_FADE);
                        holder.msg.setText(CommonUtils.ORIGINAL_NO_ALARM);
                    }
                    holder.defence.setText(CommonUtils.ORIGINAL_DEFENCE_CLOSED);
                    holder.defence.setTextColor(SUConstant.COLOR_ALARM_PURPLE_FADE);
                    break;
                case SUConstant.FLAG_STATION_OPENED:
                    holder.state.setImageResource(SUConstant.STATION_STATE_NORMAL);
                    holder.msg.setTextColor(SUConstant.COLOR_ALARM_GREEN);
                    holder.msg.setText(CommonUtils.ORIGINAL_NO_ALARM);
                    holder.defence.setText(CommonUtils.ORIGINAL_DEFENCE_OPENED);
                    holder.defence.setTextColor(SUConstant.COLOR_ALARM_PURPLE);
                    break;
                case SUConstant.FLAG_STATION_ALARM:
                    holder.state.setImageResource(SUConstant.STATION_STATE_ALARM_2);
                    holder.msg.setTextColor(SUConstant.COLOR_ALARM_ORANGE);
                    holder.msg.setText(CommonUtils.placeHolderSwitch(alarms));
                    holder.defence.setText(CommonUtils.ORIGINAL_DEFENCE_OPENED);
                    holder.defence.setTextColor(SUConstant.COLOR_ALARM_PURPLE);
                    break;
                default:
                    holder.state.setImageResource(SUConstant.STATION_STATE_CLOSED);
                    holder.msg.setTextColor(SUConstant.COLOR_ALARM_GREY_FADE);
                    holder.msg.setText("");
                    holder.defence.setText("");
                    break;
            }
        }else{
            holder.name.setTextColor(SUConstant.COLOR_STATE_OFFLINE);
            holder.state.setImageResource(SUConstant.STATION_STATE_OFFLINE);
            holder.msg.setVisibility(View.GONE);
            holder.online.setText(CommonUtils.ORIGINAL_OFFLINE);
            holder.online.setTextColor(SUConstant.COLOR_ALARM_RED);
            holder.defence.setVisibility(View.GONE);
        }

    }

    /* -------------------------------------------------- */
    // 自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class RVHolder extends RecyclerView.ViewHolder{
        LinearLayout container;
        CardView cardview;
        ImageView state;
        TextView name;
        TextView msg;
        TextView online;
        TextView defence;
//        com.andexert.library.RippleView rpView;

        public RVHolder(LinearLayout v) {
            super(v);
            container = v;
        }
    }

    // ---接口 处理点击事件 ------
    private OnRVItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRVItemClickListener listener) {
        this.mOnItemClickListener = listener;

    }
}
