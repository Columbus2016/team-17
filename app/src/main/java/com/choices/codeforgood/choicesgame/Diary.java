package com.choices.codeforgood.choicesgame;

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
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Diary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String json_string = "{\n" +
            "\t\\\"a0001\\\": {\n" +
            "\t\t\\\"type\\\": \\\"drug\\\",\n" +
            "\t\t\\\"name\\\": \\\"Kyle\\\",\n" +
            "\t\t\\\"scenario\\\": \\\"Hey, Joe’s cousin from Colorado is in town. I hear brownies have been added to the menu for tonight. You gonna show?\\\",\n" +
            "\t\t\\\"replies\\\":\n" +
            "\t\t\t[\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0004\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Nah, man. My parents would kill me if I got caught.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"Maybe it isn't worth it.\\\", \\\"wait\\\": 5 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Come on, do you really think pot brownies are a good call?\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"You only get in trouble if you get caught\\\", \\\"wait\\\": 5 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"I don’t know. Maybe I’ll come and see what it’s all about.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"I'll take that as a yes.\\\", \\\"wait\\\": 5 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"For sure. Should we meet up at 4:20? XD\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"Wicked, dude. See you then.\\\", \\\"wait\\\": 5 }\n" +
            "\t\t\t]\n" +
            "\t},\n" +
            "\t\\\"b0002\\\": {\n" +
            "\t\t\\\"type\\\": \\\"drug\\\",\n" +
            "\t\t\\\"name\\\": \\\"Kyle\\\",\n" +
            "\t\t\\\"scenario\\\": \\\"Sean got his hands on some brewskis over the weekend. Should I partake?\\\",\n" +
            "\t\t\\\"replies\\\":\n" +
            "\t\t\t[\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"21 is 21. You know that.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"You're such a buzz kill\\\", \\\"wait\\\": 5 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"What if you get caught? You already played it pretty close with Joe’s party. \\\", \\\"grade\\\": 2, \\\"response\\\": \\\"You're right. I nearly got caught too.\\\", \\\"wait\\\": 5 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"I don’t know. Just be careful things to get too out of hand.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"Isn't getting out of hand the point?\\\", \\\"wait\\\": 5 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Great opportunities don’t come around too often. Live it up.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"Righteous! You ought to come out with us.\\\", \\\"wait\\\": 35 }\n" +
            "\t\t\t]\n" +
            "\t},\n" +
            "\t\\\"b0003\\\": {\n" +
            "\t\t\\\"type\\\": \\\"bully\\\",\n" +
            "\t\t\\\"name\\\": \\\"Kyle\\\",\n" +
            "\t\t\\\"scenario\\\": \\\"[Generate the tweet message]\\\",\n" +
            "\t\t\\\"replies\\\":\n" +
            "\t\t\t[\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 }\n" +
            "\t\t\t]\n" +
            "\t},\n" +
            "\t\\\"b0004\\\": {\n" +
            "\t\t\n" +
            "\t\t\\\"id\\\": \\\"b0004\\\",\n" +
            "\t\t\\\"type\\\": \\\"Social\\\",\n" +
            "\t\t\\\"name\\\": \\\"Kyle\\\",\n" +
            "\t\t\\\"scenario\\\": \\\"Hey Kev and his squad are throwing THE most amazing party with lots of beer, and lots of fun. I feel like a loser because I bail out all the time, but they're really insisting this time. Should I just lie to mom and sneak out tonight?\\\",\n" +
            "\t\t\\\"replies\\\":\n" +
            "\t\t\t[\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"No, you're good, stay at home and chill\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"Yeah man, you gotta go\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"I mean you could go and check it out, but just don't drink or do something stupid\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"Find new friends\\\", \\\"wait\\\": 35 }\n" +
            "\t\t\t]\n" +
            "\t},\n" +
            "\t\\\"b0005\\\": {\n" +
            "\t\t\\\"type\\\": \\\"Grades\\\",\n" +
            "\t\t\\\"name\\\": \\\"Kyle\\\",\n" +
            "\t\t\\\"scenario\\\": \\\"I just got a D on my damn Math quiz, which I studied for so long. I feel incredibly dumb right now, I just wanna quit school, there's just no point of me staying :(\\\",\n" +
            "\t\t\\\"replies\\\":\n" +
            "\t\t\t[\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Yes\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 }\n" +
            "\t\t\t]\n" +
            "\t},\n" +
            "\t\\\"c0006\\\": {\n" +
            "\t\t\\\"type\\\": \\\"ethics\\\",\n" +
            "\t\t\\\"name\\\": \\\"Kyle\\\",\n" +
            "\t\t\\\"scenario\\\": \\\"I just found a wallet with $200 in it. I was walking home from school and there it was, just sitting there with no one in sight. You have to help me decide what games to get?\\\",\n" +
            "\t\t\\\"replies\\\":\n" +
            "\t\t\t[\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"You really should find the wallet’s owner. Perhaps there was an ID in there. I’ll help you if you want.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Someone is going to really miss that. Perhaps you should just turn it in to our teacher.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"Sweet! After you pocket the cash, you really should turn the ID and credit cards in to someone.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 },\n" +
            "\t\t\t\t{ \\\"go\\\": \\\"b0002\\\", \\\"delay\\\": 1440, \\\"answer\\\": \\\"$200 in cash? Nice. I bet you could also buy some cool stuff if there are any credit cards in the wallet.\\\", \\\"grade\\\": 2, \\\"response\\\": \\\"[Response]\\\", \\\"wait\\\": 35 }\n" +
            "\t\t\t]\n" +
            "\t}\n" +
            "}";

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

            TextView tv = (TextView) findViewById(R.id.diary_test);
            tv.setText(sb.toString());
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
