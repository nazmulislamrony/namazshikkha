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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.voltagelab.namazshikkhaapps.Activity.EighthButton;
import com.voltagelab.namazshikkhaapps.Activity.EleventhButton;
import com.voltagelab.namazshikkhaapps.Activity.FifthButton;
import com.voltagelab.namazshikkhaapps.Activity.FirstButton;
import com.voltagelab.namazshikkhaapps.Activity.FourthButton;
import com.voltagelab.namazshikkhaapps.Activity.NinthButton;
import com.voltagelab.namazshikkhaapps.Activity.SecondButton;
import com.voltagelab.namazshikkhaapps.Activity.SeventhButton;
import com.voltagelab.namazshikkhaapps.Activity.SixthButton;
import com.voltagelab.namazshikkhaapps.Activity.TenthButton;
import com.voltagelab.namazshikkhaapps.Activity.ThirdButton;
import com.voltagelab.namazshikkhaapps.Activity.TwelveButton;
import com.voltagelab.namazshikkhaapps.DrawerButton.BoiporichitiActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    Typeface typeface;
    Intent intent;


    Button first, second, third, fourth, fifth, sixth, seventh, eight, ninthBtn, tenth, eleventh, twelve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----------------Hooks--------------------

        toolbar = findViewById(R.id.toolbar_top);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);


        first = findViewById(R.id.firstbtn);
        second = findViewById(R.id.secondbtn);
        third = findViewById(R.id.thirdbtn);
        fourth = findViewById(R.id.fourthbtn);
        fifth = findViewById(R.id.fifthbtn);
        sixth = findViewById(R.id.sixthbtn);
        seventh = findViewById(R.id.seventhbtn);
        eight = findViewById(R.id.eighthbtn);
        ninthBtn = findViewById(R.id.ninthbtn);
        tenth = findViewById(R.id.tenthbtn);
        eleventh = findViewById(R.id.elevenbtn);
        twelve = findViewById(R.id.twelvebtn);



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
        publicButton();
        secondButton();
        thirdButton();
        fourthButton();
        fifthButton();
        sixthButton();
        seventhButton();
        eightButton();
        ninthButton();
        tenthButton();
        eleventhButton();
        twelveButton();


        navigationOnClickListener();
        drawerTitleColor();
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
                    case R.id.bookPorichit:
                        intent = new Intent(MainActivity.this, BoiporichitiActivity.class);
                        startActivity(intent);
                        break;


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

                    case R.id.shareapp:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "সহীহ নামাজ ও দোয়া শিক্ষা এপ ডাউনলোড করুনঃ https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
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
                        Uri uri = Uri.parse("market://details?id=com.voltagelab.namazshikkhaapps" + getBaseContext().getPackageName());
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
                        final String urlFb = "fb://page/" + "623249964534978";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(urlFb));

                        // If a Facebook app is installed, use it. Otherwise, launch
                        // a browser
                        final PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> list =
                                packageManager.queryIntentActivities(intent,
                                        PackageManager.MATCH_DEFAULT_ONLY);
                        if (list.size() == 0) {
                            final String urlBrowser = "https://www.facebook.com/" + "623249964534978";
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

    public void publicButton() {
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstButton.class);
                startActivity(intent);
            }
        });
    }

    // --------------- second Button click listener ----------------------------------------

    public void secondButton() {
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondButton.class);
                startActivity(intent);
            }
        });
    }

    //====================third button click listener ===========================================

    public void thirdButton() {
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdButton.class);
                startActivity(intent);
            }
        });
    }


    //====================fourth button click listener ===========================================


    public void fourthButton() {
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FourthButton.class);
                startActivity(intent);
            }
        });
    }

    // ===================fifth button click listener==============================================

    public void fifthButton() {
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FifthButton.class);
                startActivity(intent);
            }
        });
    }

    // ===================sixth button click listener==============================================

    public void sixthButton() {
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SixthButton.class);
                startActivity(intent);
            }
        });
    }


    // ===================seventh button click listener==============================================

    public void seventhButton() {
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeventhButton.class);
                startActivity(intent);
            }
        });
    }

    // ===================eight button click listener==============================================

    public void eightButton() {
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EighthButton.class);
                startActivity(intent);
            }
        });
    }


    // ===================ninth button click listener==============================================

    public void ninthButton() {
        ninthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NinthButton.class);
                startActivity(intent);
            }
        });
    }

    // ===================tenth button click listener==============================================

    public void tenthButton() {
        tenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TenthButton.class);
                startActivity(intent);
            }
        });
    }

    public void eleventhButton() {
        eleventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EleventhButton.class);
                startActivity(intent);
            }
        });
    }


    public void twelveButton() {
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TwelveButton.class);
                startActivity(intent);
            }
        });
    }

}



