 package com.anirudhln.hw03;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by anirudhln on 9/23/16.
 */
public class GetData extends AsyncTask<String,Void,ArrayList<Questions>>
 {

    MainActivity activity;
    ProgressDialog pd;

    public GetData(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Questions> doInBackground(String... params)
     {

        BufferedReader br = null;

        try
         {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line= "";

            while ((line=br.readLine())!=null)
            {
                sb.append(line+"\n");
            }

            return parseJsonData.parseJsonDataAndReturnArrayList(sb.toString());


        }
         catch (MalformedURLException e)
         {
            e.printStackTrace();
        }
         catch (ProtocolException e)
         {
            e.printStackTrace();
         }
          catch (IOException e)
           {
                e.printStackTrace();
           }
           finally
            {
                if(br!=null)
                {
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                     {
                        e.printStackTrace();
                    }
                }
            }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        pd=new ProgressDialog(activity);
        pd.setMessage("Loading Data...");
        pd.setMax(100);
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Questions> questions)
     {
        super.onPostExecute(questions);

        activity.qList = questions;
        pd.dismiss();
        activity.triviaIV.setVisibility(View.VISIBLE);
        activity.startTriviaButton.setEnabled(true);
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
