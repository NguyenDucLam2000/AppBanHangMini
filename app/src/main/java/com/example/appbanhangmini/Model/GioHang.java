package com.example.appbanhangmini.Model;

public class GioHang
{
    private int maSanPham, tongGiaSanPham, soLuong;
    private String tenSanPham, hinhSanPham;
    public GioHang()
    {
    }

    public GioHang(int maSanPham, int tongGiaSanPham, int soLuong, String tenSanPham, String hinhSanPham)
    {
        this.maSanPham = maSanPham;
        this.tongGiaSanPham = tongGiaSanPham;
        this.soLuong = soLuong;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
    }

    public int getMaSanPham()
    {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham)
    {
        this.maSanPham = maSanPham;
    }


    public int getSoLuong()
    {
        return soLuong;
    }

    public int getTongGiaSanPham()
    {
        return tongGiaSanPham;
    }

    public void setTongGiaSanPham(int tongGiaSanPham)
    {
        this.tongGiaSanPham = tongGiaSanPham;
    }

    public void setSoLuong(int soLuong)
    {
        this.soLuong = soLuong;
    }

    public String getTenSanPham()
    {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham)
    {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhSanPham()
    {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham)
    {
        this.hinhSanPham = hinhSanPham;
    }
}
