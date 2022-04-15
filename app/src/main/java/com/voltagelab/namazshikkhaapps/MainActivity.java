package com.voltagelab.namazshikkhaapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.DoaActivity;

import com.voltagelab.namazshikkhaapps.Activity.NintyNineNames.NintyNineNames;
import com.voltagelab.namazshikkhaapps.Activity.SadkaiyeJariya;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.GosolActivity;
import com.voltagelab.namazshikkhaapps.Activity.SettingsActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.EidNamazActivity;
import com.voltagelab.namazshikkhaapps.Activity.Tasbih.TasbihActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ChitrosohoNamazActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.JanajarNamazActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.NamazAdayerNiomActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.NamazerMasalActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.NamazerOwaktoActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.OjuActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.SurahActivityNamazShikkha;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.TahajjodTaraviActivity;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.TayamommumActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    Typeface typeface;
    Intent intent;

    Button btnoju, second, third, namazerOwakto, namazerMasala, namazAdayerNiom, seventh, tahajjodtarabi, ninthBtn, doaActivity, chitrosohoNamaz, tasbihBtn, all_names_of_creator;
    RelativeLayout rvSadkayeJariya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Helper helper = new Helper(this);
//        helper.themeChange();
        //-----------------Hooks--------------------

        toolbar = findViewById(R.id.toolbar_top);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        rvSadkayeJariya = findViewById(R.id.rv_sadkaye_jariya);


        btnoju = findViewById(R.id.btnoju);
        second = findViewById(R.id.secondbtn);
        third = findViewById(R.id.thirdbtn);
        namazerOwakto = findViewById(R.id.fourthbtn);
        namazerMasala = findViewById(R.id.fifthbtn);
        namazAdayerNiom = findViewById(R.id.sixthbtn);
        seventh = findViewById(R.id.seventhbtn);
        tahajjodtarabi = findViewById(R.id.eighthbtn);
        ninthBtn = findViewById(R.id.ninthbtn);
//        surahBtn = findViewById(R.id.tenthbtn);
        doaActivity = findViewById(R.id.elevenbtn);
        chitrosohoNamaz = findViewById(R.id.twelvebtn);
        tasbihBtn = findViewById(R.id.tasbihbtn);
        all_names_of_creator = findViewById(R.id.names_of_creator);

        // -------------Collapsing toolbar Layout------------------------

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        collapsingToolbar.setTitle("সহীহ নামাজ ও দোয়া শিক্ষা");
        typeface = ResourcesCompat.getFont(this, R.font.solaiman_lipi);
        collapsingToolbar.setCollapsedTitleTypeface(typeface);
        collapsingToolbar.setExpandedTitleTypeface(typeface);


        //------------------------Different Methode------------------------
        setNavDrawer();
        Oju();
        gosolMethod();
        tayammumMethod();
        namazerOwaktoMethod();
        namazerMasalaMethod();
        namazAdayerNiomMethod();
        seventhButton();
        tahajjodtarabiMethod();
        ninthButton();
//        surahMethod();
        doaMethod();
        chitrosohoNamaz();
        QuranButton();
        tasbihBtn();
        sadkiyeJariya();
        namesOfCreator();


        navigationOnClickListener();
        drawerTitleColor();


//        Log.d("restult","date ---> " + today.getDay() + " / " + today.getMonth() + " / " + today.getYear());
//        Log.d("restult","imsaak --fajar-> "+imsaak +",  "+prayerTimes.fajr());
//        Log.d("restult","imsaak --sunrise-> " +prayerTimes.shuruq());
//        Log.d("restult","imsaak --Zuhr-> " +prayerTimes.thuhr());
//        Log.d("restult","imsaak --asr-> " + prayerTimes.assr());
//        Log.d("restult","imsaak --Maghrib --->" + prayerTimes.maghrib());
//        Log.d("restult","imsaak --isha --->" + prayerTimes.ishaa());


        Coordinates coordinates = new Coordinates(23.8103, 90.4125);

        DateComponents date = new DateComponents(2015, 11, 1);
        DateComponents date1 = DateComponents.from(new Date());
        CalculationParameters params =
                CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();
        params.madhab = Madhab.HANAFI;
        params.adjustments.fajr = 2;
        PrayerTimes prayerTimes = new PrayerTimes(coordinates, date1, params);


        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        formatter.format(prayerTimes.fajr);

//        Log.d("restult","date ---> " + today.getDay() + " / " + today.getMonth() + " / " + today.getYear());
        Log.d("restult", "imsaak --fajar-> " + ",  " + prayerTimes.fajr);
        Log.d("restult", "imsaak --dhuhr-> " + prayerTimes.dhuhr);
        Log.d("restult", "imsaak --asr-> " + prayerTimes.asr);
        Log.d("restult", "imsaak --Maghrib-> " + prayerTimes.maghrib);
        Log.d("restult", "imsaak --isha --->" + prayerTimes.isha);
        Log.d("restult", "imsaak -- --->" + prayerTimes.nextPrayer());
    }

    private void namesOfCreator() {
        all_names_of_creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NintyNineNames.class));
            }
        });
    }

    private void sadkiyeJariya() {
        rvSadkayeJariya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://groups/359779002578811")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/359779002578811")));
                }
            }
        });
    }

    private void tasbihBtn() {
        tasbihBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TasbihActivity.class);
                startActivity(intent);
            }
        });
    }


    //  ====================== new Alert Dialog builder ===========================================

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("আপনি কি অ্যাপ্লিকেশন টি থেকে বের হতে চাচ্ছেন?")
                .setPositiveButton("হ্যা", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("না", null)
                .show();
    }


    // -------------------- Navigation Drawer Toggle when nav fab click, toggle open and close the drawer-------------

    public void setNavDrawer() {
        setSupportActionBar(toolbar);

        drawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    //        =============== Navigation Drawer item text color and size change =======================

    public void drawerTitleColor() {
        Menu menu = navigationView.getMenu();

        MenuItem item = menu.findItem(R.id.communication_title);
        SpannableString s = new SpannableString(item.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        item.setTitle(s);
    }

//     ===================== Navigation Drawer on click listener =========================================


    public void navigationOnClickListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                    case R.id.bookPorichit:
//                        intent = new Intent(MainActivity.this, BoiporichitiActivity.class);
//                        startActivity(intent);
//                        break;


                    case R.id.motamotnav:
                        new AlertDialog.Builder(MainActivity.this, R.style.MyDialog)
                                .setTitle("মতামত")
                                .setMessage("আসসালামু আলাইকুম। এই এপ্স এ যদি কোন ভুল পরিলক্ষীত হয় অথবা আপনার কোন মূল্যবান মতামত বা পরামর্শ থাকলে আমাদের জানান। আপনার মতামত আমাদের এগিয়ে যেতে সাহায্য করবে ইনশা আল্লাহ।")
                                .setPositiveButton("Email Us", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        intent = new Intent(Intent.ACTION_VIEW);
                                        String subject = " Namaz Shikkha App - মতামত / পরামর্শ";
                                        String body = "";
                                        Uri data = Uri.parse("mailto:voltagelabbd@gmail.com?subject=" + subject + "&body=" + body);
                                        intent.setData(data);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                        break;

                    case R.id.settings:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;

                    case R.id.sadkaye_jariya:
                        startActivity(new Intent(MainActivity.this, SadkaiyeJariya.class));
                        break;

                    case R.id.shareapp:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "সহীহ নামাজ ও দোয়া শিক্ষা এপ ডাউনলোড করুনঃ https://play.google.com/store/apps/details?id=" + "com.voltagelab.namazshikkhaapps");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;

                    case R.id.emailus:
                        intent = new Intent(Intent.ACTION_VIEW);
                        String subject = " Namaz Shikkha App - মতামত / পরামর্শ";
                        String body = "";
                        Uri data = Uri.parse("mailto:voltagelabbd@gmail.com?subject=" + subject + "&body=" + body);
                        intent.setData(data);
                        startActivity(intent);
                        break;

                    case R.id.rating:
                        Uri uri = Uri.parse("market://details?id=" + getBaseContext().getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=com.voltagelab.namazshikkhaapps" + getBaseContext().getPackageName())));
                        }
                        break;


                    case R.id.facebook:
                        final String urlFb = "fb://groups/359779002578811";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(urlFb));

                        // If a Facebook app is installed, use it. Otherwise, launch
                        // a browser
                        final PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> list =
                                packageManager.queryIntentActivities(intent,
                                        PackageManager.MATCH_DEFAULT_ONLY);
                        if (list.size() == 0) {
                            final String urlBrowser = "https://www.facebook.com/" + "/groups/359779002578811";
                            intent.setData(Uri.parse(urlBrowser));
                        }



                        startActivity(intent);
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    //-------------------first btn onclick listener intent --------------------------------------

    public void Oju() {
        btnoju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OjuActivity.class);
                startActivity(intent);
            }
        });
    }

    // --------------- second Button click listener ----------------------------------------

    public void gosolMethod() {
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GosolActivity.class);
                startActivity(intent);
            }
        });
    }

    //====================third button click listener ===========================================

    public void tayammumMethod() {
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TayamommumActivity.class);
                startActivity(intent);
            }
        });
    }


    //====================fourth button click listener ===========================================


    public void namazerOwaktoMethod() {
        namazerOwakto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NamazerOwaktoActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===================fifth button click listener==============================================

    public void namazerMasalaMethod() {
        namazerMasala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NamazerMasalActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===================sixth button click listener==============================================

    public void namazAdayerNiomMethod() {
        namazAdayerNiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NamazAdayerNiomActivity.class);
                startActivity(intent);
            }
        });
    }


    // ===================seventh button click listener==============================================

    public void seventhButton() {
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EidNamazActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===================eight button click listener==============================================

    public void tahajjodtarabiMethod() {
        tahajjodtarabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TahajjodTaraviActivity.class);
                startActivity(intent);
            }
        });
    }


    // ===================ninth button click listener==============================================

    public void ninthButton() {
        ninthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JanajarNamazActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===================tenth button click listener==============================================

//    public void surahMethod() {
//        surahBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SurahActivityNamazShikkha.class);
//                startActivity(intent);
//            }
//        });
//    }

    public void doaMethod() {
        doaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoaActivity.class);
                startActivity(intent);
            }
        });
    }


    public void chitrosohoNamaz() {
        chitrosohoNamaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChitrosohoNamazActivity.class);
                startActivity(intent);
            }
        });
    }

    private void QuranButton() {
        Button btnAlQuran = findViewById(R.id.btn_alquran);
        btnAlQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahActivity.class));
            }
        });
    }

}



