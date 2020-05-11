package com.example.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namazshikkha.Model.SeventhStore;
import com.example.namazshikkha.R;

import java.util.List;

public class SeventhRecyclerView extends RecyclerView.Adapter<SeventhRecyclerView.MySeventhViewHolder> {

    public static SeventhClickListener seventhClickListener;

    private Context context;
    private List<SeventhStore> seventhStores;

    public SeventhRecyclerView(Context context, List<SeventhStore> seventhStores) {
        this.context = context;
        this.seventhStores = seventhStores;
    }


    @NonNull
    @Override
    public SeventhRecyclerView.MySeventhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MySeventhViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull SeventhRecyclerView.MySeventhViewHolder holder, int position) {
        holder.listTitle.setText(seventhStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return seventhStores.size();
    }

    public class MySeventhViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MySeventhViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            seventhClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface SeventhClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(SeventhClickListener seventhClickListener){
        SeventhRecyclerView.seventhClickListener=seventhClickListener;
    }
}
