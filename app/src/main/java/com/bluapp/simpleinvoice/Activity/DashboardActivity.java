package com.bluapp.simpleinvoice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluapp.simpleinvoice.Fragment.HomeFragment;
import com.bluapp.simpleinvoice.Fragment.InvoiceFragment;
import com.bluapp.simpleinvoice.R;

public class DashboardActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView NavHeaderProfilepic;
    private TextView NavHeaderFullname;
    private Toolbar toolbar;
    private int k = 0;


    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    public static String CURRENT_TAG = TAG_HOME;
    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbarTitle();
        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        NavHeaderProfilepic = (ImageView) navHeader.findViewById(R.id.profile_image);
        NavHeaderFullname = (TextView) navHeader.findViewById(R.id.fullname);

        // load nav menu header data
        loadNavHeader();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame,new HomeFragment()).commit();

        // initializing navigation menu
        setUpNavigationView();


    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        //getting user detail from sharedpreference
      //  User user = SharedPrefManager.getInstance(HomeActivity.this).getUser();
        //setting profile pic
      //  GlideApp.with(HomeActivity.this).load(user.getProfilepic()).thumbnail(GlideApp.with(HomeActivity.this).load(R.drawable.profilepic)).error(R.drawable.profilepic).into(NavHeaderProfilepic);
        //setting user fullname
     //   NavHeaderFullname.setText(user.getFullname().toUpperCase());
    }





    private void setToolbarTitle() {
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawer.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                        xfragmentTransaction.replace(R.id.frame,new HomeFragment()).commit();
                        return true;
                    case R.id.nav_invoice:
                        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame,new InvoiceFragment()).commit();
                        return true;

                    default:
                        navItemIndex = 0;
                }

                return true;
            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,drawer, toolbar,R.string.app_name,
                R.string.app_name);

        drawer.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }





    }



}
