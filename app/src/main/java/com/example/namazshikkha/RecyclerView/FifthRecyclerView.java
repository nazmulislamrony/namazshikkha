package com.example.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namazshikkha.Model.FifthStore;
import com.example.namazshikkha.R;

import java.util.List;

public class FifthRecyclerView extends RecyclerView.Adapter<FifthRecyclerView.MyFifthViewHolder> {

    public static FifthClickListener fifthClickListener;

    private Context context;
    private List<FifthStore> fifthStores;

    public FifthRecyclerView(Context context, List<FifthStore> fifthStores) {
        this.context = context;
        this.fifthStores = fifthStores;
    }


    @NonNull
    @Override
    public FifthRecyclerView.MyFifthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyFifthViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull FifthRecyclerView.MyFifthViewHolder holder, int position) {
        holder.listTitle.setText(fifthStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return fifthStores.size();
    }

    public class MyFifthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyFifthViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fifthClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface FifthClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(FifthClickListener fifthClickListener){
        FifthRecyclerView.fifthClickListener=fifthClickListener;
    }
}
