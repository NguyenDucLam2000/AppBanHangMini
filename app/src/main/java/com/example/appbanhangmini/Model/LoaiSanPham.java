package com.example.appbanhangmini.Model;

public class LoaiSanPham
{
    private int maLoaiSanPham;
    private String tenLoaiSanPham, hinhLoaiSanPham, linkHinhLoaiSanPham;
    //private byte[] hinhLoaiSanPhamByte;

    public String getLinkHinhLoaiSanPham()
    {
        return linkHinhLoaiSanPham;
    }

    public void setLinkHinhLoaiSanPham(String linkHinhLoaiSanPham)
    {
        this.linkHinhLoaiSanPham = linkHinhLoaiSanPham;
    }

    public LoaiSanPham()
    {
    }

    public LoaiSanPham(int maLoaiSanPham, String tenLoaiSanPham, String linkHinhLoaiSanPham)
    {
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.linkHinhLoaiSanPham = linkHinhLoaiSanPham;
    }

/*    public LoaiSanPham(int maLoaiSanPham, String tenLoaiSanPham, String hinhLoaiSanPham)
    {
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.hinhLoaiSanPham = hinhLoaiSanPham;
    }*/

/*    public byte[] getHinhLoaiSanPhamByte()
    {
        return hinhLoaiSanPhamByte;
    }

    public void setHinhLoaiSanPhamByte(byte[] hinhLoaiSanPhamByte)
    {
        this.hinhLoaiSanPhamByte = hinhLoaiSanPhamByte;
    }*/

    public int getMaLoaiSanPham()
    {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham)
    {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham()
    {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham)
    {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getHinhLoaiSanPham()
    {
        return hinhLoaiSanPham;
    }

    public void setHinhLoaiSanPham(String hinhLoaiSanPham)
    {
        this.hinhLoaiSanPham = hinhLoaiSanPham;
    }
}
