package com.example.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namazshikkha.Model.SecondStore;
import com.example.namazshikkha.R;

import java.util.List;

public class SecondRecyclerView extends RecyclerView.Adapter<SecondRecyclerView.MySecondViewHolder> {

    public static SecondClickListener secondClickListener;

    private Context context;
    private List<SecondStore> secondStores;

    public SecondRecyclerView(Context context, List<SecondStore> secondStores) {
        this.context = context;
        this.secondStores = secondStores;
    }


    @NonNull
    @Override
    public SecondRecyclerView.MySecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MySecondViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondRecyclerView.MySecondViewHolder holder, int position) {
        holder.listTitle.setText(secondStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return secondStores.size();
    }

    public class MySecondViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MySecondViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            secondClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface SecondClickListener{
        void onItemClickListener (int position, View v);
    }

    public void setOnItemClickListener(SecondClickListener secondClickListener){
        SecondRecyclerView.secondClickListener=secondClickListener;
    }
}
