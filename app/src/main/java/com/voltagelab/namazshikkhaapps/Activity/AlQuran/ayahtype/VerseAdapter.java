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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.helper.OnVersePlayUpdateListener;

import java.util.ArrayList;


public class VerseAdapter extends RecyclerView.Adapter<VerseAdapter.ViewHolder> {

    private Context mcontext;

    private View view;

    private Typeface faceArabic;

    public ArrayList<AyatDetails> ayatlist = new ArrayList<>();


    SharedPreferences pre;
    int surahId;

    OnVersePlayUpdateListener onVersePlayUpdateListener;


    public VerseAdapter(Context context, ArrayList<AyatDetails> aylist, int surahId, OnVersePlayUpdateListener onVersePlayUpdateListener) {
        this.onVersePlayUpdateListener = onVersePlayUpdateListener;
        this.mcontext = context;
        this.ayatlist = aylist;
        this.surahId = surahId;
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
    int verseClick = -1000;
    int previousSelected = -1000;
    boolean isPlayListAndVerse = false, isLoopPlaying = false;
    public void isPlayingMedia(boolean isPlaying, boolean isLoopingPlaying) {
        this.isPlayListAndVerse = isPlaying;
        this.isLoopPlaying = isLoopingPlaying;
        if (isPlaying) {
        } else if (isLoopingPlaying) {
            isPlayListAndVerse = false;
        } else {
            verseClick = -1000;
        }
        notifyDataSetChanged();
    }
    public void currentPlayingIndex(int position) {
        verseClick = position;
        if (position != 0) {
            previousSelected = position;
        }
        if (previousSelected > 0) {
            notifyItemChanged(previousSelected - 1);
        }
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(mcontext) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(position);
        AlQuranActivity.layoutManager.startSmoothScroll(smoothScroller);
        notifyItemChanged(position);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AyatDetails ayat = ayatlist.get(position);
        if (surahId==1) {
            holder.txtversenumber.setText(Helper.getDigitBanglaFromEnglish(position+1+""));
        } else {
            holder.txtversenumber.setText(Helper.getDigitBanglaFromEnglish(ayat.getVerse_id()));
        }
        holder.ayat.setTypeface(faceArabic);
        holder.ayat.setText(ayat.getQuran_quotes());
        holder.ayattranslate.setText(ayat.getQuran_translation());


        holder.ayat.setTextSize(TypedValue.COMPLEX_UNIT_SP,pre.getInt("size",30));

        String[] seperated = ayat.getQuran_quotes().split(" ");
        String numbering = "";

        for (int idsad = 0; idsad < seperated.length; idsad++) {
            Log.d("checkstrinarray", seperated[idsad]);
            String tempspace = ayat.getQuran_quotes().replace("[\\u0600-\\u06FF]", " ");
            numbering  = tempspace;
            numbering = numbering + idsad;
        }
        Spannable spannable = new SpannableString(numbering);
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (surahId != 9 && position == 0) {
                    surahId = 1;
                    onVersePlayUpdateListener.onUpdateVersePlay(surahId, position);
                } else if (surahId == 9) {
                    onVersePlayUpdateListener.onUpdateVersePlay(surahId, (position + 1));
                } else {
                    onVersePlayUpdateListener.onUpdateVersePlay(surahId, position);
                }
                verseClick = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
            return ayatlist.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ayat;
        TextView ayattranslate;
        TextView txtversenumber;
        TextView bottom_desc;
        CardView cv;
        View mview;
        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardViewmain);
            ayat = (TextView) itemView.findViewById(R.id.ayatmain);
            ayattranslate = (TextView) itemView.findViewById(R.id.txt_bangla_ayat);
            txtversenumber = (TextView) itemView.findViewById(R.id.txt_verse_numbe);


//            bottom_desc = (TextView) itemView.findViewById(R.id.bottom_desc);
            mview = itemView;
        }
    }
}
