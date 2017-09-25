package com.anirudhln.hw03;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
 {

    ImageView triviaIV;
    ArrayList<Questions> qList;
    public final static String QUESTIONS_LIST = "questionsList";
    Button startTriviaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qList = new ArrayList<Questions>();
        triviaIV = (ImageView)findViewById(R.id.triviaImageView);
        triviaIV.setVisibility(View.INVISIBLE);
        startTriviaButton = (Button)findViewById(R.id.startTriviaButton);
        startTriviaButton.setEnabled(false);
        isConnected();

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                finish();
            }
        });

        findViewById(R.id.startTriviaButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this,TriviaActivity.class);
                intent.putExtra(QUESTIONS_LIST,qList);
                startActivity(intent);

            }
        });
    }

    private Boolean isConnected()
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if(nf!=null && nf.isConnected())
        {
            new GetData(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
        }
        return  false;
    }
 }
