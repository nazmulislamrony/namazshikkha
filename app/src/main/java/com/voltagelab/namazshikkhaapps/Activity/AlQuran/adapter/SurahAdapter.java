package com.voltagelab.namazshikkhaapps.Activity.AlQuran.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.ModelSura;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.SurahModel;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;

/** Created by Sadmansamee on 7/19/15. */
public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

  OnItemClickListener mItemClickListener;
  private ArrayList<ModelSura> surahModelArrayList;
  private Context context;
  private Typeface faceName;


  public SurahAdapter(ArrayList<ModelSura> surahModelArrayList, Context context) {
    this.surahModelArrayList = surahModelArrayList;
    this.context = context;
    faceName = Typeface.createFromAsset(context.getAssets(), "fonts/noorehuda.ttf");
  }

  @Override
  public SurahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_surah_ui, parent, false);
    SurahViewHolder viewHolder = new SurahViewHolder(view);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(SurahViewHolder holder, int position) {

    ModelSura surahModel = surahModelArrayList.get(position);
    holder.surah_idTextView.setText(surahModel.getId() + ".");
    holder.translateTextView.setText(surahModel.getBnSuraName());
    holder.arabicTextView.setText(surahModel.getName_arabic());
    holder.arabicTextView.setTypeface(faceName);

//    if (position % 2 == 0) {
//      holder.row_surah.setBackgroundColor(ContextCompat.getColor(context, R.color.mushaf3));
//
//    } else {
//      holder.row_surah.setBackgroundColor(ContextCompat.getColor(context, R.color.mushaf2));
//    }
  }

  @Override
  public long getItemId(int position) {
    //  Surah surah = surahArrayList.get(position);
    long data = Long.parseLong(surahModelArrayList.get(position).getId());
    return data;
  }

  public Object getItem(int position) {

    return surahModelArrayList.get(position);
  }

  @Override
  public int getItemCount() {
    return surahModelArrayList.size();
  }

  public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
    this.mItemClickListener = mItemClickListener;
  }

  public class SurahViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener // current clickListerner
  {
    public TextView translateTextView;

    public TextView surah_idTextView;

    public TextView arabicTextView;
    public RelativeLayout row_surah;

    public SurahViewHolder(View view) {
      super(view);
      translateTextView = view.findViewById(R.id.translate_textView);
      arabicTextView = view.findViewById(R.id.arabic_textView);
      surah_idTextView = view.findViewById(R.id.surah_idTextView);
      row_surah = view.findViewById(R.id.row_surah);

      view.setOnClickListener(this); // current clickListerner
    }

    @Override
    public void onClick(View v) {
      if (mItemClickListener != null) {
        mItemClickListener.onItemClick(v, getLayoutPosition());
      }
    }
  }
}
