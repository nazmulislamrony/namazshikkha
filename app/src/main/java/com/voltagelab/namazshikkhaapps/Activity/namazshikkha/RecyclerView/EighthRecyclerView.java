package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EighthStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class EighthRecyclerView extends RecyclerView.Adapter<EighthRecyclerView.MyEighthViewHolder> {

    public static EighthClickListener eighthClickListener;

    private Context context;
    private List<EighthStore> eighthStores;

    public EighthRecyclerView(Context context, List<EighthStore> eighthStores) {
        this.context = context;
        this.eighthStores = eighthStores;
    }


    @NonNull
    @Override
    public EighthRecyclerView.MyEighthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyEighthViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull EighthRecyclerView.MyEighthViewHolder holder, int position) {
        holder.listTitle.setText(eighthStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return eighthStores.size();
    }

    public class MyEighthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyEighthViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            eighthClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface EighthClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(EighthClickListener eighthClickListener){
        EighthRecyclerView.eighthClickListener=eighthClickListener;
    }
}
