package com.example.appbanhangmini.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appbanhangmini.Model.GioHang;
import com.example.appbanhangmini.R;
import com.example.appbanhangmini.View.GioHangActivity;
import com.example.appbanhangmini.View.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterGioHang extends BaseAdapter/* implements View.OnClickListener*/
{
    ViewHolder viewHolder;
    private List<GioHang> listGioHang;
    private Context context;
    private int resoure;
    private int position;
    private int tongTien;
    private int size;
    private int ma, soLuong, gia;
    private boolean btnCongClick;

    public AdapterGioHang()
    {

    }

    public AdapterGioHang(Context context, int resoure, List<GioHang> listGioHang)
    {
        this.context = context;
        this.resoure = resoure;
        this.listGioHang = listGioHang;
    }

    public List<GioHang> getListGioHang()
    {
        return listGioHang;
    }

    public void setListGioHang(List<GioHang> listGioHang)
    {
        this.listGioHang = listGioHang;
    }

    @Override
    public int getCount()
    {
        return listGioHang.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listGioHang.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return listGioHang.get(position).getMaSanPham();
    }

/*    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCong:
                xuLyCong();
                break;
            case R.id.btnTru:
                xuLyTru();
                break;
        }
        notifyDataSetChanged();
    }*/

    public void xuLyCong(int position)
    {
        btnCongClick = true;
/*        Log.d("Giá ", gia + "");
        Log.d("Số lượng ", Integer.parseInt(viewHolder.txtSoLuong.getText().toString()) + "");
        Log.d("Tổng giá ", MainActivity.listGioHang.get(position).getTongGiaSanPham() + "");*/
        listGioHang.get(position).setSoLuong(listGioHang.get(position).getSoLuong() + 1);
        viewHolder.txtSoLuong.setText(String.valueOf(listGioHang.get(position).getSoLuong()));
        toggleButtonCong(position);
        loadTongTien(position);
    }

    public void loadTongTien(int position)
    {
        tongTien = 0;
        updateSoLuong(position);
        for (int i = 0; i < size; ++i)
        {
            tongTien += MainActivity.listGioHang.get(i).getTongGiaSanPham();
            //Log.d("Giá ", String.valueOf(tongTien));
        }
        Log.d("Tổng tiền ", String.valueOf(tongTien));
        GioHangActivity.txtTongTien.setText(String.valueOf(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(tongTien)));
    }

    public void updateSoLuong(int position)
    {
        ma = listGioHang.get(position).getMaSanPham();
        //Log.d("Mã ", String.valueOf(ma));
        for (int i = 0; i < size; ++i)
        {
            //Log.d("Mã thứ " + (i + 1), String.valueOf(MainActivity.listGioHang.get(i).getMaSanPham()));
            if (ma == MainActivity.listGioHang.get(i).getMaSanPham())
            {
                soLuong = Integer.parseInt(viewHolder.txtSoLuong.getText().toString());
                if (btnCongClick)
                {
                    gia = MainActivity.listGioHang.get(position).getTongGiaSanPham() / (soLuong - 1);
                }
                else
                {
                    gia = MainActivity.listGioHang.get(position).getTongGiaSanPham() / (soLuong + 1);
                }
                Log.d("Số lượng update", soLuong + "");
                MainActivity.listGioHang.get(i).setSoLuong(soLuong);
                MainActivity.listGioHang.get(i).setTongGiaSanPham(soLuong * gia);
                //Log.d("Số lượng ", viewHolder.txtSoLuong.getText().toString());
                break;
            }
        }
    }

    public void xuLyTru(int position)
    {
        btnCongClick = false;
/*        Log.d("Giá ", gia + "");
        Log.d("Số lượng ", Integer.parseInt(viewHolder.txtSoLuong.getText().toString()) + "");
        Log.d("Tổng giá ", MainActivity.listGioHang.get(position).getTongGiaSanPham() + "");*/
        listGioHang.get(position).setSoLuong(listGioHang.get(position).getSoLuong() - 1);
        viewHolder.txtSoLuong.setText(String.valueOf(listGioHang.get(position).getSoLuong()));
        toggleButtonTru(position);
        loadTongTien(position);
    }

    public void toggleButtonCong(int position)
    {
        if (listGioHang.get(position).getSoLuong() == 9)
        {
            viewHolder.btnCong.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewHolder.btnCong.setVisibility(View.VISIBLE);
        }
    }

    public void toggleButtonTru(int position)
    {
        if (listGioHang.get(position).getSoLuong() == 1)
        {
            viewHolder.btnTru.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewHolder.btnTru.setVisibility(View.VISIBLE);
        }
    }

    public class ViewHolder
    {
        ImageView imgHinhSanPham;
        TextView txtTenSanPham, txtGiaSanPham, txtSoLuong;
        Button btnCong, btnTru;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resoure, parent, false);
            viewHolder.txtGiaSanPham = convertView.findViewById(R.id.txtGiaSanPham);
            viewHolder.txtSoLuong = convertView.findViewById(R.id.txtSoLuong);
            viewHolder.txtTenSanPham = convertView.findViewById(R.id.txtTenSanPham);
            viewHolder.imgHinhSanPham = convertView.findViewById(R.id.imgHinhSanPham);
            viewHolder.btnCong = convertView.findViewById(R.id.btnCong);
            viewHolder.btnTru = convertView.findViewById(R.id.btnTru);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtTenSanPham.setText(listGioHang.get(position).getTenSanPham());
        Picasso.with(context).load(listGioHang.get(position).getHinhSanPham()).into(viewHolder.imgHinhSanPham);
        viewHolder.txtSoLuong.setText(String.valueOf(listGioHang.get(position).getSoLuong()));
        viewHolder.txtGiaSanPham.setText(String.valueOf(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(listGioHang.get(position).getTongGiaSanPham())));
        toggleButtonCong(position);
        toggleButtonTru(position);
/*      viewHolder.btnTru.setOnClickListener(this);
        viewHolder.btnCong.setOnClickListener(this);*/
        viewHolder.btnTru.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                size = MainActivity.listGioHang.size();
                xuLyTru(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                size = MainActivity.listGioHang.size();
                xuLyCong(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
