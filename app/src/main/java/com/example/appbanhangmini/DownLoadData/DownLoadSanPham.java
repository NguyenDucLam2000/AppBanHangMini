package com.example.appbanhangmini.DownLoadData;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadSanPham extends AsyncTask<Void, Void, String>
{
    private String linkDownLoad;
    private String maLoaiSanPham;
    public static String requestMethod = "GET";
    StringBuilder builder = new StringBuilder();

    public DownLoadSanPham()
    {
    }

    public DownLoadSanPham(String linkDownLoad)
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

    public void setMaLoaiSanPham(String maLoaiSanPham)
    {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        try
        {
            if(maLoaiSanPham != null)
            {
                linkDownLoad += "?MaLoaiSanPham=" + maLoaiSanPham;
            }
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
