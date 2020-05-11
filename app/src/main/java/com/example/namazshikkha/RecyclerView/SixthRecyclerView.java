package com.example.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namazshikkha.Model.SixthStore;
import com.example.namazshikkha.R;

import java.util.List;

public class SixthRecyclerView extends RecyclerView.Adapter<SixthRecyclerView.MySixthViewHolder> {

    public static SixthClickListener sixthClickListener;

    private Context context;
    private List<SixthStore> sixthStores;

    public SixthRecyclerView(Context context, List<SixthStore> sixthStores) {
        this.context = context;
        this.sixthStores = sixthStores;
    }


    @NonNull
    @Override
    public SixthRecyclerView.MySixthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MySixthViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull SixthRecyclerView.MySixthViewHolder holder, int position) {
        holder.listTitle.setText(sixthStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return sixthStores.size();
    }

    public class MySixthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MySixthViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sixthClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface SixthClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(SixthClickListener sixthClickListener){
        SixthRecyclerView.sixthClickListener=sixthClickListener;
    }
}
