package com.anirudhln.hw03;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anirudhln on 9/23/16.
 */
public class parseJsonData
{

    static ArrayList<Questions> questionsList;

    public static ArrayList parseJsonDataAndReturnArrayList(String jsonData)
    {

        try
        {
            JSONObject jsonObj = new JSONObject(jsonData);
            JSONArray results = jsonObj.getJSONArray("questions");

            questionsList = new ArrayList<Questions>();

            for(int i=0;i<results.length();i++)
            {
                JSONObject jsonQuestion = results.getJSONObject(i);
                JSONObject choicesObj = jsonQuestion.getJSONObject("choices");
                JSONArray choicesArray = choicesObj.getJSONArray("choice");

                ArrayList<String> choicesList = new ArrayList<String>();

                if (choicesArray != null)
                    {
                    for (int j=0;j<choicesArray.length();j++)
                        {
                            choicesList.add(choicesArray.get(j).toString());
                        }
                    }
                if(jsonQuestion.has("image"))
                {
                    Questions newQuestion = new Questions(jsonQuestion.getString("id"),jsonQuestion.getString("text"),jsonQuestion.getString("image"),choicesList,choicesObj.getString("answer"));
                    questionsList.add(newQuestion);
                }
                else
                {
                    Questions newQuestion = new Questions(jsonQuestion.getString("id"), jsonQuestion.getString("text"), "", choicesList, choicesObj.getString("answer"));
                    questionsList.add(newQuestion);
                }

            }


        }
        catch (JSONException e) {e.printStackTrace();}

        return questionsList;
    }
}
