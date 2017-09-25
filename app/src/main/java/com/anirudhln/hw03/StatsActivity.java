package com.anirudhln.hw03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity
 {

    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        TextView resultTV = (TextView)findViewById(R.id.percentageTextView);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        final ArrayList<Questions> qqlist = (ArrayList<Questions>) getIntent().getExtras().getSerializable(MainActivity.QUESTIONS_LIST);

        int result = getIntent().getExtras().getInt("result");

        double resultRate = (double)result/16;

        double percentage = resultRate*100;

        resultTV.setText(percentage+"%");
        progressBar.setProgress((int)percentage);

        findViewById(R.id.quitButton_result).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(StatsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.tryAgainButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(StatsActivity.this, TriviaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(MainActivity.QUESTIONS_LIST,qqlist);
                startActivity(intent);
            }
        });
    }
}
