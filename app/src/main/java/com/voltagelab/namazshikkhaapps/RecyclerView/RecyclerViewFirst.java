package com.voltagelab.namazshikkhaapps.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.voltagelab.namazshikkhaapps.Model.ContentStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class RecyclerViewFirst extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewFirst.MyViewHolder> {

    public static ClickListener clickListener;


    private Context context;
    private List<ContentStore> contentStores;

    public RecyclerViewFirst(Context context, List<ContentStore> contentStores) {
        this.context = context;
        this.contentStores = contentStores;
    }

    @NonNull
    @Override
    public RecyclerViewFirst.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.list_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFirst.MyViewHolder holder, int position) {
        holder.listTitle.setText(contentStores.get(position).getDataName());
    }

    @Override
    public int getItemCount() {
        return contentStores.size();
    }


     class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView listTitle;

         public MyViewHolder(@NonNull View itemView) {
             super(itemView);

             listTitle=itemView.findViewById(R.id.textListing);

             itemView.setOnClickListener(this);

         }

         @Override
         public void onClick(View v) {
             clickListener.OnItemClickListener(getAdapterPosition(),v);
         }
     }

     public interface ClickListener{
        void OnItemClickListener(int position, View v);
     }

     public void setOnItemClickListener(ClickListener clickListener){
        RecyclerViewFirst.clickListener=clickListener;
     }
}
