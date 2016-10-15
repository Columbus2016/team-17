package com.choices.codeforgood.choicesgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Diary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String JSONTEXT = null;
    public static JSONObject JSONMASTER = null;
    public static JSONObject JSONQUESTION = null;
    public static boolean RETURNREADY = false;

    public static Intent FIRSTINTENT = null;

    private static int replyIndex = -1;

    private void onCreate2(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onCreate2(savedInstanceState);

        try {
            InputStream test = getApplicationContext().getAssets().open("test.txt");
            BufferedReader brtest = new BufferedReader(new InputStreamReader(test));
            StringBuilder sb = new StringBuilder();
            String line = brtest.readLine();
            sb.append(line);
            while (line != null) {
                line = brtest.readLine();
                sb.append(line);
            }

//            TextView tv = (TextView) findViewById(R.id.diary_test);
//            tv.setText(sb.toString());
            JSONTEXT = sb.toString();
            brtest.close();
            test.close();
            System.out.println("INPUTSTREAM GOOD");
        } catch (Exception e) {
            System.out.println("INPUTSTREAM NOT SUCCESSFUL, #3");
        }

        JSONObject master = null;
        try {
            JSONMASTER = new JSONObject(JSONTEXT);
            JSONQUESTION = JSONMASTER.getJSONObject("a0001");
        } catch (Exception e) {

        }

        try {

            ListView listView = (ListView) findViewById(R.id.diary_listview);
            ArrayList<String> al = new ArrayList<>();
            al.add(0, "item 1");
            al.add(0, "item 2");
            al.add(0, JSONQUESTION.getString("scenario"));
            System.out.println("flag 1");
            ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
            listView.setAdapter(aa);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
//                        startActivity(new Intent(getApplicationContext(), MultipleChoice.class));
                        FIRSTINTENT = new Intent(getApplicationContext(), MultipleChoice.class);
                        System.out.println("replyIndex check#1: " + replyIndex);
                        startActivityForResult(FIRSTINTENT, 1);
                        System.out.println("replyIndex check#3: " + replyIndex);
                    }
                }
            });

            System.out.println("replyIndex check#2: " + replyIndex);

//            listView.setAdapter(aa);
            System.out.println("flag 2");
            System.out.println("sanity check: RETURNREADY value is " + RETURNREADY);
//            while (true) {
                if (RETURNREADY) {
                    System.out.println("RETURNREADY triggered to be true");
                    String response = MultipleChoice.JSONREPLY.getString("reponse");
                    al.add(0, response);
                    aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
                    listView.setAdapter(aa);
                    String go = MultipleChoice.JSONREPLY.getString("go");
                    JSONQUESTION = JSONMASTER.getJSONObject(go);
                    al.add(0, JSONQUESTION.getString("scenario"));
                    aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
                    listView.setAdapter(aa);
                    RETURNREADY = false;

                } else {

                }
//                System.out.println("while loop iteration complete");
//            }
        } catch (Exception e) {
            System.out.println("CATCH FLAG");
        }
//
//        Button sendbutton = (Button) findViewById(R.id.mc_sendbutton);
//        sendbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("cross activity flag triggered");
//            }
//        });


    }

    private void updateListView() {
//        ListView listView = (ListView) findViewById(R.id.)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult reached. resultCode = " + resultCode);
        System.out.println("onActivityResult reached. requestCode = " + requestCode);
        System.out.println("onActivityResult reached. data.getIntExtra = " + data.getIntExtra("repliesIndex", -2));
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
//                String result=data.getStringExtra("result");
                replyIndex = data.getIntExtra("repliesIndex", -2);
                System.out.println("Activity.RESULT_OK");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println("Activity.RESULT_CANCELED");
            }
        }
    }//onActivityResult

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.nav_diary) {
            // Handle the camera action
        } else if (id == R.id.nav_stats) {

        } else if (id == R.id.nav_settings) {

        }
        intent = new Intent(this, MultipleChoice.class);
        if (intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
