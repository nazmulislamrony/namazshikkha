package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.FourthStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class FourthRecyclerView extends RecyclerView.Adapter<FourthRecyclerView.MyFourthViewHolder> {

    public static FourthClickListener fourthClickListener;

    private Context context;
    private List<FourthStore> fourthStores;

    public FourthRecyclerView(Context context, List<FourthStore> fourthStores) {
        this.context = context;
        this.fourthStores = fourthStores;
    }


    @NonNull
    @Override
    public FourthRecyclerView.MyFourthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyFourthViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull FourthRecyclerView.MyFourthViewHolder holder, int position) {
        holder.listTitle.setText(fourthStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return fourthStores.size();
    }

    public class MyFourthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyFourthViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fourthClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface FourthClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(FourthClickListener fourthClickListener){
        FourthRecyclerView.fourthClickListener=fourthClickListener;
    }
}
