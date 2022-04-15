package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EleventhStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class EleventRecyclerView extends RecyclerView.Adapter<EleventRecyclerView.EleventhViewHolder> {

    public static EleventhClickListener eleventhClickListener;

    private List<EleventhStore> eleventhStores;
    private Context context;


    public EleventRecyclerView(List<EleventhStore> eleventhStores, Context context) {
        this.eleventhStores = eleventhStores;
        this.context = context;
    }

    @NonNull
    @Override
    public EleventhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.list_view,parent,false);
        return new EleventhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EleventhViewHolder holder, int position) {
        holder.listTitle.setText(eleventhStores.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return eleventhStores.size();
    }

    public class EleventhViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public EleventhViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            eleventhClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface EleventhClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(EleventhClickListener eleventhClickListener){
        EleventRecyclerView.eleventhClickListener=eleventhClickListener;
    }

}
