package com.example.appbanhangmini.DownLoadData;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadImage extends AsyncTask<String, Void, Bitmap>
{
    private String linkHinh;
    Bitmap bitmap;

    public void setLinkHinh(String linkHinh)
    {
        this.linkHinh = linkHinh;
    }

    @Override
    protected Bitmap doInBackground(String... strings)
    {
        try
        {
/*            URL url = null;
            int length = strings.length;
            for (int i = 0; i < length; ++i)
            {
                url = new URL(strings[i]);
            }*/
            URL url = new URL(linkHinh);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

            return bitmap;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
