package com.potato.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.potato.data.OnlineListItem;
import com.potato.potatoplayer.R;

import java.util.ArrayList;

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.OnlineViewHolder>{
    private ArrayList<OnlineListItem> mData;

    public OnlineAdapter(ArrayList<OnlineListItem> items){
        this.mData = items;
    }

    @Override
    public OnlineAdapter.OnlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("OnlineAdapter","return layout.item");
        return new OnlineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(OnlineViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class OnlineViewHolder extends RecyclerView.ViewHolder{

        TextView title;

        public OnlineViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_name_song);
        }
    }
}
