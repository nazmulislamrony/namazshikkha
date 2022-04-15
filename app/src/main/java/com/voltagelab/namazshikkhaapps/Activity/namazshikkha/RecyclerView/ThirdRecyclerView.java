package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.ThirdStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class ThirdRecyclerView extends RecyclerView.Adapter<ThirdRecyclerView.MyThirdViewHolder> {

    public static ThirdClickListener thirdClickListener;

    private Context context;
    private List<ThirdStore> thirdStores;

    public ThirdRecyclerView(Context context, List<ThirdStore> thirdStores) {
        this.context = context;
        this.thirdStores = thirdStores;
    }


    @NonNull
    @Override
    public ThirdRecyclerView.MyThirdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyThirdViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdRecyclerView.MyThirdViewHolder holder, int position) {
        holder.listTitle.setText(thirdStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return thirdStores.size();
    }

    public class MyThirdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyThirdViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            thirdClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface ThirdClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(ThirdClickListener thirdClickListener){
        ThirdRecyclerView.thirdClickListener=thirdClickListener;
    }
}
