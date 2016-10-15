package com.choices.codeforgood.choicesgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MultipleChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            TextView scenariotext = (TextView) findViewById(R.id.mc_scenariotext);
            scenariotext.setText(Diary.JSONQUESTION.getString("scenario"));
            JSONArray replies = Diary.JSONQUESTION.getJSONArray("replies");
            for (int i = 0; i < 4; i++) {
                RadioButton radio = (RadioButton) findViewById(getResources().getIdentifier("mc_radio" + (i+1), "id", getPackageName()));
                JSONObject reply = (JSONObject) replies.get(i);
                String answer = reply.getString("answer");
                radio.setText(answer);
            }
        } catch (Exception e) {

        }


    }

}
