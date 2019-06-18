package com.example.appbanhangmini.View;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangmini.Model.SanPham;
import com.example.appbanhangmini.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class ChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener
{
    Toolbar toolbar;
    TextView txtTenSanPham, txtGiaSanPham, txtMoTa;
    Spinner spSoLuong;
    ImageView imgHinhSanPham;
    ArrayAdapter<String> integerArrayAdapter;
    Button btnThemVaoGioHang;
    Intent intent;
    SanPham sanPham;
    public static String keySoLuong = "SoLuong";
    public static String keyGia = "Gia";
    public static String keyHinh = "Hinh";
    public static String keyMa = "Ma";
    public static String keyTen = "Ten";
    private void addControls()
    {
        toolbar = findViewById(R.id.toolbar);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtGiaSanPham = findViewById(R.id.txtGiaSanPham);
        txtMoTa = findViewById(R.id.txtMoTa);
        spSoLuong = findViewById(R.id.spSoLuong);
        imgHinhSanPham = findViewById(R.id.imgHinhSanPham);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        intent = getIntent();
        sanPham = intent.getParcelableExtra("SanPham");
        txtTenSanPham.setText(sanPham.getTenSanPham());
        txtMoTa.setText(sanPham.getMoTa());
        txtGiaSanPham.setText("Giá : " + NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(sanPham.getGiaSanPham()));
        Picasso.with(this).load(sanPham.getLinkHinhSanPham()).into(imgHinhSanPham);
        integerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 1; i <= 9; ++i)
        {
            integerArrayAdapter.add("Số lượng : " + i);
        }
        integerArrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spSoLuong.setAdapter(integerArrayAdapter);
    }

    private void addEvents()
    {
        btnThemVaoGioHang.setOnClickListener(this);
    }

    private void customActionBar()
    {
        // Truyền toolbar vào cho actionbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        addControls();
        customActionBar();
        addEvents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
        Bundle bundle = new Bundle();
        int soLuong = Integer.parseInt(spSoLuong.getSelectedItem().toString().substring(spSoLuong.getSelectedItem().toString().length()- 1));
        bundle.putInt(keySoLuong, soLuong);
        bundle.putInt(keyGia, sanPham.getGiaSanPham());
        bundle.putInt(keyMa, sanPham.getMaSanPham());
        bundle.putString(keyHinh, sanPham.getLinkHinhSanPham());
        bundle.putString(keyTen, sanPham.getTenSanPham());

/*        Log.d("Số lượng ", String.valueOf(soLuong));
        Log.d("Giá ", String.valueOf(sanPham.getGiaSanPham()));
        Log.d("Mã ", String.valueOf(sanPham.getMaSanPham()));
        Log.d("Tên ", String.valueOf(sanPham.getTenSanPham()));*/
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
