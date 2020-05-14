package com.voltagelab.namazshikkhaapps.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Model.NinthStore;
import com.voltagelab.namazshikkhaapps.R;

import java.util.List;

public class NinthRecyclerView extends RecyclerView.Adapter<NinthRecyclerView.MyNinthViewHolder> {

    public static NinthClickListener ninthClickListener;

    private Context context;
    private List<NinthStore> ninthStores;

    public NinthRecyclerView(Context context, List<NinthStore> ninthStores) {
        this.context = context;
        this.ninthStores = ninthStores;
    }


    @NonNull
    @Override
    public NinthRecyclerView.MyNinthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itmeView = layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyNinthViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull NinthRecyclerView.MyNinthViewHolder holder, int position) {
        holder.listTitle.setText(ninthStores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return ninthStores.size();
    }

    public class MyNinthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTitle;

        public MyNinthViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle=itemView.findViewById(R.id.textListing);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ninthClickListener.onItemClickListener(getAdapterPosition(),v);
        }
    }

    public interface NinthClickListener{
        void onItemClickListener(int position, View v);
    }

    public void setOnItemClickListener(NinthClickListener ninthClickListener){
        NinthRecyclerView.ninthClickListener=ninthClickListener;
    }
}
