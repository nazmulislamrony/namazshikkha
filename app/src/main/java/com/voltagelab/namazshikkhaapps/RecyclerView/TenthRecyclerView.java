package com.voltagelab.namazshikkhaapps.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Model.TenthStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class TenthRecyclerView extends RecyclerView.Adapter<TenthRecyclerView.MyTenthViewHolder> {

    public static TenthClickListener tenthClickListener;

    private Context context;
    private List<TenthStore> tenthStores;

    public TenthRecyclerView(Context context, List<TenthStore> tenthStores) {
        this.context = context;
        this.tenthStores = tenthStores;
    }


    @NonNull
    @Override
    public TenthRecyclerView.MyTenthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyTenthViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull TenthRecyclerView.MyTenthViewHolder holder, int position) {
        holder.listTitle.setText(tenthStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return tenthStores.size();
    }

    public class MyTenthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyTenthViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            tenthClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface TenthClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(TenthClickListener tenthClickListener){
        TenthRecyclerView.tenthClickListener=tenthClickListener;
    }
}
