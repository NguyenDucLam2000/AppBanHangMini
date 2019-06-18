package com.example.appbanhangmini.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appbanhangmini.Adapter.AdapterGioHang;
import com.example.appbanhangmini.Model.GioHang;
import com.example.appbanhangmini.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity implements OnClickListener
{
    Intent intent;
    Bundle bundle;
    private int tongGia, soLuong, gia, ma, size, tongTien;
    private String hinh, ten;
    private boolean exists = false;
    Toolbar toolbar;
    ListView lvGioHang;
    AdapterGioHang adapterGioHang;
    Button btnThanhToanGioHang, btnTiepTucMuaSam;
    public static TextView txtTongTien, txtGioHangTrong;
    private void addControls()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtGioHangTrong = findViewById(R.id.txtGioHangTrong);
        btnThanhToanGioHang = findViewById(R.id.btnThanhToanGioHang);
        btnTiepTucMuaSam = findViewById(R.id.btnTiepTucMuaSam);
        txtTongTien = findViewById(R.id.txtTongTien);
        lvGioHang = findViewById(R.id.lvGioHang);
        adapterGioHang = new AdapterGioHang(getApplicationContext(), R.layout.listview_item_giohang, MainActivity.listGioHang);
        lvGioHang.setAdapter(adapterGioHang);
    }

    private void addEvents()
    {
        btnTiepTucMuaSam.setOnClickListener(this);
        btnThanhToanGioHang.setOnClickListener(this);
    }
    private void loadDuLieu()
    {
        size = MainActivity.listGioHang.size();
        // Nếu listGioHang = 0 thì thêm GioHang vào GioHang
        intent = getIntent();
        bundle = intent.getExtras();
        if(bundle != null)
        {
            addProductToCart();
        }
        if(MainActivity.listGioHang.size() != 0)
        {
            txtGioHangTrong.setVisibility(View.INVISIBLE);
        }
    }

    private void addProductToCart()
    {
        soLuong = bundle.getInt(ChiTietSanPhamActivity.keySoLuong);
        gia = bundle.getInt(ChiTietSanPhamActivity.keyGia);
        ma = bundle.getInt(ChiTietSanPhamActivity.keyMa);
        for (int i = 0; i < size; ++i)
        {
            if(ma == MainActivity.listGioHang.get(i).getMaSanPham())
            {
                MainActivity.listGioHang.get(i).setSoLuong(soLuong);
                exists = true;
                break;
            }
        }
        hinh = bundle.getString(ChiTietSanPhamActivity.keyHinh);
        ten = bundle.getString(ChiTietSanPhamActivity.keyTen);
        tongGia = soLuong * gia;
        if(!exists)
        {
            MainActivity.listGioHang.add(new GioHang(ma, tongGia, soLuong, ten, hinh));
            size++;
        }
        adapterGioHang.notifyDataSetChanged();
    }

    private void loadTongTien()
    {
        for (int i = 0; i < size; ++i)
        {
            tongTien += MainActivity.listGioHang.get(i).getTongGiaSanPham();
        }
        txtTongTien.setText(String.valueOf(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(tongTien)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        addControls();
        loadDuLieu();
        loadTongTien();
        addEvents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {

    }
}
