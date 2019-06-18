package com.example.appbanhangmini.ParserJSON;

import com.example.appbanhangmini.Model.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParserLoaiSanPham
{
    public static ArrayList<LoaiSanPham> layListLoaiSanPham(String data)
    {
        ArrayList<LoaiSanPham> listLoaiSanPham = new ArrayList<>();
        if (data != null)
        {
            try
            {
                JSONArray jListLoaiSanPham = new JSONArray(data);
                listLoaiSanPham.add(new LoaiSanPham(0, "Trang chủ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZPvOvhPvzoi8pLmk-8RV-zhqm5UmZWJQRF2vSVp0PxX6uCCcF"));
                int length = jListLoaiSanPham.length();
                for (int i = 0; i < length; ++i)
                {
                    JSONObject jLoaiSanPham = jListLoaiSanPham.getJSONObject(i);
                    LoaiSanPham loaiSanPham = new LoaiSanPham();
                    loaiSanPham.setMaLoaiSanPham(jLoaiSanPham.getInt("MaLoaiSanPham"));
                    loaiSanPham.setTenLoaiSanPham(jLoaiSanPham.getString("TenLoaiSanPham"));
                    loaiSanPham.setHinhLoaiSanPham(jLoaiSanPham.getString("HinhLoaiSanPham"));
                    loaiSanPham.setLinkHinhLoaiSanPham(jLoaiSanPham.getString("LinkHinhLoaiSanPham"));
                    listLoaiSanPham.add(loaiSanPham);
                }

                return listLoaiSanPham;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
