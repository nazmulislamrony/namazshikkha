package com.voltagelab.namazshikkhaapps.Activity.AlQuran;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.adapter.SurahAdapter;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.SurahModel;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;

/** A simple {@link Fragment} subclass. */
public class SurahFragment extends Fragment {

  static String lang;
  private ArrayList<SurahModel> surahModelArrayList;
  private RecyclerView mRecyclerView;
  private SurahAdapter surahAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  public SurahFragment() {
    // Required empty public constructor
  }

  public static SurahFragment newInstance() {
    SurahFragment surahFragment = new SurahFragment();
    return surahFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
    lang = sp.getString(Config.LANG, Config.defaultLang);
    surahModelArrayList = getSurahArrayList();
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_surah, container, false);
    mRecyclerView = view.findViewById(R.id.recycler_surah_view);
//    surahAdapter = new SurahAdapter(surahModelArrayList, getActivity());

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mRecyclerView.setAdapter(surahAdapter);

    mRecyclerView.setHasFixedSize(true);
    // use a linear layout manager
    mLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    surahAdapter.SetOnItemClickListener(
        new OnItemClickListener() {
          @Override
          public void onItemClick(View v, int position) {
            SurahModel surahModel = (SurahModel) surahAdapter.getItem(position);

            long surah_id = surahModel.getId();
            long ayah_number = surahModel.getAyahNumber();
            String surah_name = surahModel.getNameTranslate();

            Log.d("SurahFragment", "ID: " + surah_id + " Surah Name: " + surah_name);

            Bundle dataBundle = new Bundle();
            dataBundle.putLong(SurahDataSource.SURAH_ID_TAG, surah_id);
            dataBundle.putLong(SurahDataSource.SURAH_AYAH_NUMBER, ayah_number);
            dataBundle.putString(SurahDataSource.SURAH_NAME_TRANSLATE, surah_name);

//            Intent intent = new Intent(getActivity(), AyahWordActivity.class);
//            intent.putExtras(dataBundle);
//            startActivity(intent);
          }
        });
  }

  private ArrayList<SurahModel> getSurahArrayList() {
    ArrayList<SurahModel> surahModelArrayList = new ArrayList<SurahModel>();
    SurahDataSource surahDataSource = new SurahDataSource(getActivity());

    switch (lang) {
      case Config.LANG_BN:
        surahModelArrayList = surahDataSource.getBanglaSurahArrayList();
        break;
      case Config.LANG_INDO:
        surahModelArrayList = surahDataSource.getIndonesianSurahArrayList();
        break;
      case Config.LANG_EN:
        surahModelArrayList = surahDataSource.getEnglishSurahArrayList();
        break;
    }

    return surahModelArrayList;
  }
}