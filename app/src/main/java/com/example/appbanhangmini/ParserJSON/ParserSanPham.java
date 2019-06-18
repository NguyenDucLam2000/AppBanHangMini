package com.example.appbanhangmini.ParserJSON;

import com.example.appbanhangmini.Model.LoaiSanPham;
import com.example.appbanhangmini.Model.SanPham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParserSanPham
{
    public static ArrayList<SanPham> layListSanPham(String data)
    {
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        if (data != null)
        {
            try
            {
                JSONArray jListSanPham = new JSONArray(data);
                int length = jListSanPham.length();
                for (int i = 0; i < length; ++i)
                {
                    JSONObject jSanPham = jListSanPham.getJSONObject(i);
                    SanPham sanPham = new SanPham();
                    sanPham.setMaSanPham(jSanPham.getInt("MaSanPham"));
                    sanPham.setTenSanPham(jSanPham.getString("TenSanPham"));
                    sanPham.setGiaSanPham(jSanPham.getInt("GiaSanPham"));
                    sanPham.setMoTa(jSanPham.getString("MoTa"));
                    sanPham.setHinhSanPham(jSanPham.getString("HinhSanPham"));
                    sanPham.setLinkHinhSanPham(jSanPham.getString("LinkHinhSanPham"));
                    listSanPham.add(sanPham);
                }

                return listSanPham;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
