package com.anirudhln.hw03;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity
{

    LinearLayout rbLayout;
    ArrayList<Questions> qlist;
    int questionNumber = 0;
    TextView qTextView;
    TextView questionTextView;
    ImageView questionImageView;
    Button nextButton;
    int result=0;
    int checkedRadioButtonId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        qTextView = (TextView)findViewById(R.id.qTextView);
        questionTextView = (TextView)findViewById(R.id.questionTextView);

        questionImageView = (ImageView)findViewById(R.id.questionImageView);
        rbLayout = (LinearLayout)findViewById(R.id.radioButtonsLayout);

        nextButton = (Button)findViewById(R.id.nextButton);

        qlist = (ArrayList<Questions>) getIntent().getExtras().getSerializable(MainActivity.QUESTIONS_LIST);

        setQData(questionNumber);

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(checkedRadioButtonId == Integer.parseInt(qlist.get(questionNumber).getAnswer().toString()))
                {
                    result = result + 1;
                }

                questionNumber = questionNumber+1;
                if(questionNumber < qlist.size())
                {
                    setQData(questionNumber);
                }
                else
                {
                    Intent intent = new Intent(TriviaActivity.this,StatsActivity.class);
                    intent.putExtra("result",result);
                    intent.putExtra(MainActivity.QUESTIONS_LIST,qlist);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.quitButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(TriviaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final TextView timeTextView = (TextView)findViewById(R.id.timeTextView);

        new CountDownTimer(120000, 1000)
         {

            public void onTick(long millisUntilFinished)
             {
                timeTextView.setText("Time Left:"+ millisUntilFinished / 1000 + "Seconds");
            }

            public void onFinish()
             {
                timeTextView.setText("done!");
                Intent intent = new Intent(TriviaActivity.this,StatsActivity.class);
                intent.putExtra("result",result);
                intent.putExtra(MainActivity.QUESTIONS_LIST,qlist);
                startActivity(intent);
            }
        }.start();

    }

    private void setQData(final int qI)
    {
        ArrayList choicesList = null;
        nextButton.setEnabled(false);
        rbLayout.removeAllViews();
        choicesList = (ArrayList) qlist.get(qI).getChoices();

        if(qI==0){}
        else{}

        qTextView.setText("Q"+(qI+1));

        questionTextView.setText(qlist.get(qI).getQuestionText());

        new GetImage(this).execute(qlist.get(qI).getImageURLString());

        final RadioButton[] rb = new RadioButton[choicesList.size()];
        RadioGroup rg = new RadioGroup(this);
        rg.removeAllViews();
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int j = 0; j < choicesList.size(); j++)
        {
                rb[j] = new RadioButton(this);
                rb[j].setId(j+1);
                rg.addView(rb[j]);

                rb[j].setText(choicesList.get(j).toString());
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                checkedRadioButtonId = checkedId;
            }
        });

        rbLayout.addView(rg);

    }
}
