package com.example.appbanhangmini.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhangmini.Model.LoaiSanPham;
import com.example.appbanhangmini.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLoaiSanPham extends BaseAdapter
{
    List<LoaiSanPham> listLoaiSanPham;
    Context context;
    public AdapterLoaiSanPham()
    {

    }

    public AdapterLoaiSanPham( Context context, List<LoaiSanPham> listLoaiSanPham)
    {
        this.listLoaiSanPham = listLoaiSanPham;
        this.context = context;
    }

    public List<LoaiSanPham> getListLoaiSanPham()
    {
        return listLoaiSanPham;
    }

    public void setListLoaiSanPham(List<LoaiSanPham> listLoaiSanPham)
    {
        this.listLoaiSanPham = listLoaiSanPham;
    }

    public AdapterLoaiSanPham(List<LoaiSanPham> listLoaiSanPham)
    {
        this.listLoaiSanPham = listLoaiSanPham;
    }

    @Override
    public int getCount()
    {
        return listLoaiSanPham.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listLoaiSanPham.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return listLoaiSanPham.get(position).getMaLoaiSanPham();
    }

    public class ViewHolder
    {
        TextView txtTenLoaiSanPham;
        ImageView imgHinhLoaiSanPham;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_item_loaisanpham, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgHinhLoaiSanPham = convertView.findViewById(R.id.imgHinhLoaiSanPham);
            viewHolder.txtTenLoaiSanPham = convertView.findViewById(R.id.txtTenLoaiSanPham);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtTenLoaiSanPham.setText(listLoaiSanPham.get(position).getTenLoaiSanPham());
        // Sử dụng thư viện Picasso
        Picasso.with(context).load(listLoaiSanPham.get(position).getLinkHinhLoaiSanPham()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgHinhLoaiSanPham);
        //byte[] bytes = listLoaiSanPham.get(position).getHinhLoaiSanPham().getBytes();
        //Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0, bytes.length);

        //viewHolder.imgHinhLoaiSanPham.setImageBitmap(bitmap);
        //Glide.with(context).load(bytes).into(viewHolder.imgHinhLoaiSanPham);

        return convertView;
    }
}
