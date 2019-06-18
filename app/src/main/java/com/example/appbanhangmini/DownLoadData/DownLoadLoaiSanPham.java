package com.example.appbanhangmini.DownLoadData;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadLoaiSanPham extends AsyncTask<Void, Void, String>
{
    private String linkDownLoad;
    public static String requestMethod = "GET";
    StringBuilder builder = new StringBuilder();

    public DownLoadLoaiSanPham()
    {
    }

    public DownLoadLoaiSanPham(String linkDownLoad)
    {
        this.linkDownLoad = linkDownLoad;
    }

    public String getLinkDownLoad()
    {
        return linkDownLoad;
    }

    public void setLinkDownLoad(String linkDownLoad)
    {
        this.linkDownLoad = linkDownLoad;
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        try
        {
            URL url = new URL(linkDownLoad);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }

            return  builder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
