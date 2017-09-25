package com.anirudhln.hw03;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by anirudhln on 9/23/16
 */
public class GetImage extends AsyncTask<String,Void, Bitmap>
 {

    TriviaActivity activity;
    ProgressDialog pd;

    public GetImage(TriviaActivity activity)
     {
        this.activity = activity;
     }

    @Override
    protected Bitmap doInBackground(String... params)
     {

        try
         {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = null;
            try
             {
                httpURLConnection = (HttpURLConnection)url.openConnection();
           }
            catch (IOException e)
             {
                e.printStackTrace();
             }
            httpURLConnection.setRequestMethod("GET");

            Bitmap image = BitmapFactory.decodeStream(httpURLConnection.getInputStream());

            return image;

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
        return null;
    }

    @Override
    protected void onPreExecute()
     {
        super.onPreExecute();
        pd=new ProgressDialog(activity);
        pd.setMessage("Loading Trivia...");
        pd.setMax(100);
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap)
     {
        super.onPostExecute(bitmap);

        activity.questionImageView.setImageResource(0);
        if(bitmap!=null)
        {
            activity.questionImageView.setImageBitmap(bitmap);
        }
        activity.nextButton.setEnabled(true);
        pd.dismiss();
    }

}
