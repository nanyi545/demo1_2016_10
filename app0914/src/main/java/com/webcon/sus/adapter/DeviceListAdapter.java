package com.webcon.sus.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.webcon.sus.demo.R;
import com.webcon.sus.entity.BaseDevice;
import com.webcon.sus.utils.SUConstant;

import java.util.List;

public class DeviceListAdapter extends BaseAdapter{

    private final List<BaseDevice> mDevices;
    private LayoutInflater mInflater;

    public DeviceListAdapter(Context context, List<BaseDevice> list){
        this.mDevices = list;
        if(context == null || list == null){
            return;
        }
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return mDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_device_list, null);
            holder.logo = (ImageView)convertView.findViewById(R.id.device_item_logo);
            holder.name = (TextView)convertView.findViewById(R.id.device_item_name);
            holder.check = (ImageView)convertView.findViewById(R.id.device_item_check);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        setState(holder, mDevices.get(position).getType());
        holder.name.setText(mDevices.get(position).getName());

        return convertView;
    }

    private void setState(ViewHolder holder, int type){
        switch(type){
            case SUConstant.DEVICE_TYPE_MONITOR:
                holder.logo.setImageResource(SUConstant.DEVICE_LOGO_MONITOR);
                holder.check.setImageResource(SUConstant.DEVICE_CHECK_WEBC);
//                holder.check.setVisibility(View.VISIBLE);
                break;
//            case SUConstant.DEVICE_TYPE_RADAR:
//                break;
//            case SUConstant.DEVICE_TYPE_ENTRANCE:
//                break;
            default:
                holder.logo.setImageResource(SUConstant.DEVICE_LOGO_SENSOR);
//                holder.check.setVisibility(View.GONE);
                holder.check.setImageDrawable(null);
                break;
        }

    }

    private class ViewHolder{
        ImageView logo;
        TextView name;
        ImageView check;
    }

}
