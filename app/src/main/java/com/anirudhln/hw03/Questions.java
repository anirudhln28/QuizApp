package com.anirudhln.hw03;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by anirudhln on 9/23/16.
 */
public class Questions implements Parcelable
{

    String questionID;
    String questionText;
    String imageURLString;
    ArrayList<String> choices;
    String answer;

      protected Questions(Parcel in)
    {
        questionID = in.readString();
        questionText = in.readString();
        imageURLString = in.readString();
        choices = in.createStringArrayList();
        answer = in.readString();
    }
    public Questions(String questionID, String questionText, String imageURLString, ArrayList choices, String answer)
    {
        this.questionID = questionID;
        this.questionText = questionText;
        this.imageURLString = imageURLString;
        this.choices = choices;
        this.answer = answer;
    }


    public static final Creator<Questions> CREATOR = new Creator<Questions>()
     {
        @Override
        public Questions createFromParcel(Parcel in)
         {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size)
        {
            return new Questions[size];
        }
    };

    public String getQuestionID()
     {
        return questionID;
    }

    public void setQuestionID(String questionID)
     {
        this.questionID = questionID;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
     {
        this.questionText = questionText;
    }

    public String getImageURLString()
     {
        return imageURLString;
    }

    public void setImageURLString(String imageURLString)
     {
        this.imageURLString = imageURLString;
    }

    public ArrayList<String> getChoices()
     {
        return choices;
    }

    public void setChoices(ArrayList<String> choices)
    {
        this.choices = choices;
    }

    public String getAnswer()
     {
        return answer;
    }

    public void setAnswer(String answer)
     {
        this.answer = answer;
    }

    @Override
    public int describeContents()
     {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
     {
        dest.writeString(questionID);
        dest.writeString(questionText);
        dest.writeString(imageURLString);
        dest.writeStringList(choices);
        dest.writeString(answer);
    }
}
