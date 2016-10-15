package com.choices.codeforgood.choicesgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MultipleChoice extends AppCompatActivity {

    public static JSONObject JSONREPLY = null; //kill me now


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("MultipleChoice class onCreate");
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
            final JSONArray replies = Diary.JSONQUESTION.getJSONArray("replies");
            for (int i = 0; i < 4; i++) {
                RadioButton radio = (RadioButton) findViewById(getResources().getIdentifier("mc_radio" + (i + 1), "id", getPackageName()));
                System.out.println("radio object is: " + radio.toString());

//                final int i_final = i;
//                View.OnClickListener templistener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            JSONREPLY = (JSONObject) replies.get(i_final);
//                            System.out.println("JSONREPLY: " + JSONREPLY);
//                        } catch (JSONException e) {
//                            System.out.println("radio exception thrown" + e);
//                        }
//                        System.out.println("radio onclick");
//                    }
//                };
//                radio.setOnClickListener(templistener);
                String answer = replies.getJSONObject(i).getString("answer");
                radio.setText(answer);
                System.out.println("radio answer should be: " + answer);
            }
            Button sendbutton = (Button) findViewById(R.id.mc_sendbutton);
            sendbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 4; i++) {
                        RadioButton radio = (RadioButton) findViewById(getResources().getIdentifier("mc_radio" + (i + 1), "id", getPackageName()));
                        if (radio.isChecked()) {
                            try {
                                JSONREPLY = (JSONObject) replies.get(i);
                            } catch (Exception e) {

                            }
                            Diary.RETURNREADY = true;
                            System.out.println("JSONREPLY IS: " + JSONREPLY);
//                            finish();
//                            Intent returnIntent = new Intent();
//                            returnIntent.putExtra("repliesIndex", i);
                            Diary.FIRSTINTENT.putExtra("repliesIndex", i);
//                            setResult(Diary.RESULT_OK, returnIntent);
                            setResult(Diary.RESULT_OK, Diary.FIRSTINTENT);
                            finish();
                            break;
                        }
                    }
                }
            });
        } catch (Exception e) {

        }


    }

}
