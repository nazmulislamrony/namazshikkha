package com.voltagelab.namazshikkhaapps.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Model.TwelveStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class TwelveRecyclerView extends RecyclerView.Adapter<TwelveRecyclerView.MyTwelveViewHolder> {

    public static TwelveClickListener twelveClickListener;

    private Context context;
    private List<TwelveStore> twelveStores;

    public TwelveRecyclerView(Context context, List<TwelveStore> twelveStores) {
        this.context = context;
        this.twelveStores = twelveStores;
    }


    @NonNull
    @Override
    public TwelveRecyclerView.MyTwelveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyTwelveViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull TwelveRecyclerView.MyTwelveViewHolder holder, int position) {
        holder.listTitle.setText(twelveStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return twelveStores.size();
    }

    public class MyTwelveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyTwelveViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            twelveClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface TwelveClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(TwelveClickListener twelveClickListener){
        TwelveRecyclerView.twelveClickListener=twelveClickListener;
    }
}
