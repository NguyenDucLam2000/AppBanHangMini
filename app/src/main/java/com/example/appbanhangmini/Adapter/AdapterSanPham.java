package com.example.appbanhangmini.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangmini.Model.SanPham;
import com.example.appbanhangmini.R;
import com.example.appbanhangmini.View.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolder>
{
    List<SanPham> listSanPham;
    Context context;
    public AdapterSanPham()
    {

    }
    public AdapterSanPham(List<SanPham> listSanPham)
    {
        this.listSanPham = listSanPham;
    }

    public AdapterSanPham(List<SanPham> listSanPham, Context context)
    {
        this.listSanPham = listSanPham;
        this.context = context;
    }

    public List<SanPham> getListSanPham()
    {
        return listSanPham;
    }

    public void setListSanPham(List<SanPham> listSanPham)
    {
        this.listSanPham = listSanPham;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //View view = inflater.inflate(R.layout.recycle_view_item, viewGroup, false);
        View view = inflater.inflate(R.layout.recycle_view_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        viewHolder.txtTenSanPham.setText(listSanPham.get(i).getTenSanPham());
        viewHolder.txtGiaSanPham.setText("Giá " + NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(listSanPham.get(i).getGiaSanPham()));
        //viewHolder.imgHinhSanPham
        Picasso.with(context).load(listSanPham.get(i).getLinkHinhSanPham()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgHinhSanPham);
    }

    @Override
    public int getItemCount()
    {
        return listSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener
    {
        TextView txtTenSanPham, txtGiaSanPham;
        ImageView imgHinhSanPham;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtGiaSanPham = itemView.findViewById(R.id.txtGiaSanPham);
            imgHinhSanPham = itemView.findViewById(R.id.imgHinhSanPham);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
            intent.putExtra("SanPham", listSanPham.get(getAdapterPosition()));
            context.startActivity(intent);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            
        }
    }
}
