package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mcontext;

    private View view;

    private Typeface faceArabic;

    public ArrayList<AyatDet> ayatlist = new ArrayList<>();

    int surahId;

    SharedPreferences pre;



    public MainAdapter(Context context, ArrayList<AyatDet> aylist) {
        this.mcontext = context;
        this.ayatlist = aylist;
        faceArabic = Typeface.createFromAsset(context.getAssets(), "fonts/noorehuda.ttf");
        pre = context.getSharedPreferences("fontsize",MODE_PRIVATE);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        view = inflater.inflate(R.layout.ayat_main_sample, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final AyatDet ayat = ayatlist.get(position);

        surahId = ayat.getSurahId();

        int pos=position;

        if (surahId == 9) {
            pos = position + 1;
        }

        final AyatDet ayatm;
        if (pos == ayatlist.size()){
            ayatm = ayatlist.get(pos-1);
        } else {
            ayatm = ayatlist.get(pos);
        }

        holder.ayat.setTypeface(faceArabic);
        holder.ayat.setText(ayatm.getAyatdet());

        holder.ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP,pre.getInt("size",30));

        String[] seperated = ayatm.getAyatdet().split(" ");
        String numbering = "";

        for (int idsad = 0; idsad < seperated.length; idsad++) {
            Log.d("checkstrinarray", seperated[idsad]);
            String tempspace = ayatm.getAyatdet().replace("[\\u0600-\\u06FF]", " ");
            numbering  = tempspace;
            numbering = numbering + idsad;
        }

        Spannable spannable = new SpannableString(numbering);

        spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        holder.bottom_desc.setText(spannable, TextView.BufferType.SPANNABLE);

    }

    @Override
    public int getItemCount() {
            return ayatlist.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ayat;
        TextView bottom_desc;
        CardView cv;
        View mview;
        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardViewmain);
            ayat = (TextView) itemView.findViewById(R.id.ayatmain);


//            bottom_desc = (TextView) itemView.findViewById(R.id.bottom_desc);
            mview = itemView;
        }
    }
}
