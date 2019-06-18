package com.example.appbanhangmini.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SanPham implements Parcelable
{
    private int maSanPham, maLoaiSanPham, giaSanPham;
    private String tenSanPham, hinhSanPham, linkHinhSanPham, moTa;

    public SanPham(int maSanPham, int maLoaiSanPham, int giaSanPham, String tenSanPham, String hinhSanPham, String linkHinhSanPham, String moTa)
    {
        this.maSanPham = maSanPham;
        this.maLoaiSanPham = maLoaiSanPham;
        this.giaSanPham = giaSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.linkHinhSanPham = linkHinhSanPham;
        this.moTa = moTa;
    }

    protected SanPham(Parcel in)
    {
        maSanPham = in.readInt();
        maLoaiSanPham = in.readInt();
        giaSanPham = in.readInt();
        tenSanPham = in.readString();
        hinhSanPham = in.readString();
        linkHinhSanPham = in.readString();
        moTa = in.readString();
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>()
    {
        @Override
        public SanPham createFromParcel(Parcel in)
        {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size)
        {
            return new SanPham[size];
        }
    };

    public String getMoTa()
    {
        return moTa;
    }

    public void setMoTa(String moTa)
    {
        this.moTa = moTa;
    }

    public SanPham()
    {
    }

    public int getMaSanPham()
    {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham)
    {
        this.maSanPham = maSanPham;
    }

    public int getMaLoaiSanPham()
    {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham)
    {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public int getGiaSanPham()
    {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham)
    {
        this.giaSanPham = giaSanPham;
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

    public String getLinkHinhSanPham()
    {
        return linkHinhSanPham;
    }

    public void setLinkHinhSanPham(String linkHinhSanPham)
    {
        this.linkHinhSanPham = linkHinhSanPham;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(maSanPham);
        dest.writeInt(maLoaiSanPham);
        dest.writeInt(giaSanPham);
        dest.writeString(tenSanPham);
        dest.writeString(hinhSanPham);
        dest.writeString(linkHinhSanPham);
        dest.writeString(moTa);
    }
}
