package com.choices.codeforgood.choicesgame;

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
import android.widget.ListView;
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

    public static String JSONTEXT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        int res_id = getResources().getIdentifier("test", "json", getPackageName());
//        InputStream is = getResources().openRawResource(res_id);
//        File test = new File(getClassLoader().getResource("res/test.txt").getFile());

//        File test = new File(getApplicationContext().getFilesDir(), "test.txt");
//        if (test.exists()) {
//            System.out.println("test.txt DETECTED!!!");
//        } else {
//            System.out.println("test.txt NOT DETECTED!!!!!!!!!!!");
//        }

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

        try {
            String str = "{ \"name\": \"Alice\", \"age\": 20 }";
            JSONObject obj = new JSONObject(str);
            String n = obj.getString("name");
            int a = obj.getInt("age");
            System.out.println(n + " " + a);  // prints "Alice 20"
        } catch (Exception e) {

        }

        ListView listView = (ListView) findViewById(R.id.diary_listview);
        ArrayList<String> al = new ArrayList<>();
        al.add("diary item 1");
        al.add("diary item 2");
        al.add("diary item 3");
        al.add("diary item 4");
        al.add("diary item 5");
        al.add("diary item 6");
        al.add("diary item 7");
        al.add("diary item 8");
        al.add("diary item 9");
        al.add("diary item 10");
        al.add("diary item 11");
        al.add("diary item 12");
        al.add("diary item 13");
        al.add("diary item 14");


        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        listView.setAdapter(aa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

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
