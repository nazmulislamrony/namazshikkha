package com.voltagelab.namazshikkhaapps.Activity.NintyNineNames;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.Activity.NintyNineNames.NintyNimesModel;
import com.voltagelab.namazshikkhaapps.EnglishToBangla;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;

/** Created by Sadmansamee on 7/19/15. */
public class NintyNineAdapters extends RecyclerView.Adapter<NintyNineAdapters.SurahViewHolder> {

  OnItemClickListener mItemClickListener;
  private ArrayList<NintyNimesModel> nintyNineList;
  private Context context;
  private Typeface faceName;
  EnglishToBangla englishToBangla;

  public NintyNineAdapters(ArrayList<NintyNimesModel> nintyNineLists, Context context) {
    this.nintyNineList = nintyNineLists;
    this.context = context;
    englishToBangla = new EnglishToBangla();
  }

  @Override
  public SurahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_nintynine, parent, false);
    SurahViewHolder viewHolder = new SurahViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(SurahViewHolder holder, int position) {

    NintyNimesModel nintyninemodes = nintyNineList.get(position);

    holder.txt_bn_name.setText(nintyninemodes.getBn_name());
    holder.txt_ar_name.setText(nintyninemodes.getAr_name());
    holder.txt_bn_meaning.setText("অর্থঃ "+nintyninemodes.getBn_meaning());
    holder.txt_bn_fajilat.setText(nintyninemodes.getFazilat_bn());
    holder.serial_of_id.setText(englishToBangla.getDigitBanglaFromEnglish(nintyninemodes.getId()));
    holder.references_details.setText(nintyninemodes.getReference());

    boolean isExpandable = nintyninemodes.isExpandable();
    holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    Log.d("check_nintynine",": "+nintyninemodes.getId());

  }

  @Override
  public long getItemId(int position) {
    //  Surah surah = surahArrayList.get(position);

    long data =  Long.parseLong(nintyNineList.get(position).getId());
    return data;
  }

  public Object getItem(int position) {

    return nintyNineList.get(position);
  }

  @Override
  public int getItemCount() {
    return nintyNineList.size();
  }

  public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
    this.mItemClickListener = mItemClickListener;
  }

  public class SurahViewHolder extends RecyclerView.ViewHolder
          implements View.OnClickListener // current clickListerner
  {
    public TextView txt_bn_name, txt_ar_name, txt_bn_meaning, txt_bn_fajilat, serial_of_id, references_details;
    RelativeLayout expandableLayout;
    RelativeLayout rlayout;

    public SurahViewHolder(View view) {
      super(view);
      serial_of_id = view.findViewById(R.id.serial_of_names);
      txt_bn_name = view.findViewById(R.id.txt_bn_name);
      txt_ar_name = view.findViewById(R.id.txt_ar_name);
      txt_bn_meaning = view.findViewById(R.id.txt_bn_meaning);
      txt_bn_fajilat = view.findViewById(R.id.txt_bn_fajilat);
      references_details = view.findViewById(R.id.references_details);
      view.setOnClickListener(this); // current clickListerner
      rlayout = view.findViewById(R.id.linearLayout);
      expandableLayout = view.findViewById(R.id.expandable_layout);

      rlayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          NintyNimesModel nintyninemodes = nintyNineList.get(getAdapterPosition());
          nintyninemodes.setExpandable(!nintyninemodes.isExpandable());
          notifyItemChanged(getAdapterPosition());

        }
      });

    }

    @Override
    public void onClick(View v) {
      if (mItemClickListener != null) {
        mItemClickListener.onItemClick(v, getLayoutPosition());
      }
    }
  }
}
