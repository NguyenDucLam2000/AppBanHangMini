package com.example.appbanhangmini.View;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhangmini.Adapter.AdapterLoaiSanPham;
import com.example.appbanhangmini.Adapter.AdapterSanPham;
import com.example.appbanhangmini.DownLoadData.DownLoadLoaiSanPham;
import com.example.appbanhangmini.DownLoadData.DownLoadSanPham;
import com.example.appbanhangmini.Model.GioHang;
import com.example.appbanhangmini.Model.LoaiSanPham;
import com.example.appbanhangmini.Model.SanPham;
import com.example.appbanhangmini.ParserJSON.ParserLoaiSanPham;
import com.example.appbanhangmini.ParserJSON.ParserSanPham;
import com.example.appbanhangmini.R;
import com.example.appbanhangmini.View.Fragment.FragmentDangNhap;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    Toolbar toolbar;
    RecyclerView rvDanhSachSanPham;
    AdapterSanPham adapterSanPham;
    ArrayList<SanPham> listSanPham;
    RecyclerView.LayoutManager layoutManager;
    ListView lvLoaiSanPham;
    AdapterLoaiSanPham adapterLoaiSanPham;
    ViewFlipper vfQuangCao;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle; // Để đóng/mở DrawerLayout
    ArrayList<String> linkHinhQuangCao;
    //String linkIP = "http://192.168.43.157/";
    String linkIP = "http://192.168.190.100/";
    String linkLayListLoaiSanPham = linkIP + "banhangmini/api/LoaiSanPham";
    String linkLayListSanPham = linkIP + "banhangmini/api/SanPham";
    ArrayList<LoaiSanPham> listLoaiSanPham;
    ConnectivityManager connectivityManager;
    DownLoadLoaiSanPham downLoadLoaiSanPham;
    DownLoadSanPham downLoadSanPham;
    Intent intent;
    public static ArrayList<GioHang> listGioHang;
    String duLieuJSONDangNhap;
   private String userName, email, link, id;

    private void addControls()
    {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        rvDanhSachSanPham = findViewById(R.id.rvDanhSachSanPham);
        listSanPham = new ArrayList<>();
        adapterSanPham = new AdapterSanPham(listSanPham, this);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rvDanhSachSanPham.setLayoutManager(layoutManager);
        rvDanhSachSanPham.setAdapter(adapterSanPham);
        loadDuLieuVaoRecycleView();
        lvLoaiSanPham = findViewById(R.id.lvLoaiSanPham);
        vfQuangCao = findViewById(R.id.vfQuangCao);
        listLoaiSanPham = new ArrayList<>();
        adapterLoaiSanPham = new AdapterLoaiSanPham(this, listLoaiSanPham);
        lvLoaiSanPham.setAdapter(adapterLoaiSanPham);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        listGioHang = new ArrayList<>();
    }

    private void loadDuLieuVaoRecycleView()
    {

        downLoadSanPham = new DownLoadSanPham();
        downLoadSanPham.setLinkDownLoad(linkLayListSanPham);
        downLoadSanPham.execute();
        try
        {
            listSanPham = ParserSanPham.layListSanPham(downLoadSanPham.get());
            adapterSanPham.setListSanPham(listSanPham);
            adapterSanPham.notifyDataSetChanged();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void addEvents()
    {
        lvLoaiSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //adapterSanPham.notifyItemRangeRemoved(0, listSanPham.size());
                downLoadSanPham = new DownLoadSanPham();
                downLoadSanPham.setLinkDownLoad(linkLayListSanPham);
                String maLoaiSanPham = String.valueOf(((LoaiSanPham) parent.getAdapter().getItem(position)).getMaLoaiSanPham());
                if (!maLoaiSanPham.equals("0"))
                {
                    downLoadSanPham.setMaLoaiSanPham(maLoaiSanPham);
                    if(maLoaiSanPham.equals("1"))
                    {
                        toolbar.setTitle("Điện thoại");
                    }
                    else
                    {
                        toolbar.setTitle("Máy tính");
                    }
                }
                else
                {
                    toolbar.setTitle("Trang chủ");
                }
                downLoadSanPham.execute();
                try
                {
                    listSanPham = ParserSanPham.layListSanPham(downLoadSanPham.get());
                    adapterSanPham.setListSanPham(listSanPham);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                adapterSanPham.notifyDataSetChanged();
            }
        });
    }

    private void customActionBar()
    {
        // Truyền toolbar vào cho actionbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        // Set sự kiện để đóng/mở drawerLayout
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState(); // Hiển thị mũi tên khi đang mở và nút cài đặt khi không được mở
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        customActionBar();
        actionViewFlipper();
        addEvents();
        loadDuLieuVaoNavigationView();
        //loadDuLieuVaoNavigationViewSuDungVolley();
    }

    private void loadDuLieuVaoNavigationViewSuDungVolley()
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(linkLayListLoaiSanPham, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                if (response != null)
                {
                    int length = response.length();
                    for (int i = 0; i < length; ++i)
                    {
                        JSONObject jLoaiSanPham = null;
                        try
                        {
                            jLoaiSanPham = response.getJSONObject(i);
                            LoaiSanPham loaiSanPham = new LoaiSanPham();
                            loaiSanPham.setMaLoaiSanPham(jLoaiSanPham.getInt("MaLoaiSanPham"));
                            loaiSanPham.setTenLoaiSanPham(jLoaiSanPham.getString("TenLoaiSanPham"));
                            loaiSanPham.setHinhLoaiSanPham(jLoaiSanPham.getString("LinkHinhLoaiSanPham"));
                            loaiSanPham.setLinkHinhLoaiSanPham(jLoaiSanPham.getString("LinkHinhLoaiSanPham"));
                            listLoaiSanPham.add(loaiSanPham);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    adapterLoaiSanPham.setListLoaiSanPham(listLoaiSanPham);
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //CheckConnection
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void loadDuLieuVaoNavigationView()
    {
        if (connectivityManager.getActiveNetworkInfo() != null)
        {
            downLoadLoaiSanPham = new DownLoadLoaiSanPham();
            downLoadLoaiSanPham.setLinkDownLoad(linkLayListLoaiSanPham);
            downLoadLoaiSanPham.execute();

            try
            {
                //Log.d("Dữ liệu ", downLoadLoaiSanPham.get());
                listLoaiSanPham = ParserLoaiSanPham.layListLoaiSanPham(downLoadLoaiSanPham.get());
                adapterLoaiSanPham.setListLoaiSanPham(listLoaiSanPham);
                adapterLoaiSanPham.notifyDataSetChanged();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this, "Vui lòng kiểm tra lại kết nối Internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void actionViewFlipper()
    {
        linkHinhQuangCao = new ArrayList<>();
        linkHinhQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEtF4DgE6IY5i3Sd46V3tTshxiG160X_2jO41cMKtvibZdHkXj");
        linkHinhQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSK2xteLzSEDBbUY78Sl28hT1kw9jpRQK8jxqiTZ5Qk3qGRqTaO");
        linkHinhQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc7Tpf7TImHn7F6-cxh5nP7QDkRkQ2WTg5JOwep0ZAonCJnoSnoA");
        linkHinhQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG_0VP2fb9qNYpeVaWFzKCWtkAfhWjx3Qc0o2GYdoAPttDqle-kg");
        linkHinhQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVbhQhw9I2eooy7y2HKj6cDgn-3VW_BXbSp9OZbZ9piZP0r91_");
        linkHinhQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPrc_L4EFtS04-l0ZKEr8NgZVfox3yeJukQAQi1leEYQI49BXSDw");
        int size = linkHinhQuangCao.size();
        if (connectivityManager.getActiveNetworkInfo() != null)
        {
            //DownLoadImage downLoadImage = new DownLoadImage();
            for (int i = 0; i < size; ++i)
            {
                ImageView imageView = new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

/*            if(!(downLoadImage.getStatus() == AsyncTask.Status.RUNNING))
            {
                //Dùng AsysnTask load hình
                downLoadImage.setLinkHinh(linkHinhQuangCao.get(i));
                downLoadImage.execute();
            }*/
/*            DownLoadImage downLoadImage = new DownLoadImage();
            downLoadImage.setLinkHinh(linkHinhQuangCao.get(i));
            downLoadImage.execute();
            try
            {
                imageView.setImageBitmap(downLoadImage.get());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }*/
                // Load hình vào ImageView thông qua Picasso
                Picasso.with(getApplicationContext()).load(linkHinhQuangCao.get(i)).into(imageView);
                // Thêm Image vào ViewFlipper
                vfQuangCao.addView(imageView);
            }
            // Set ViewFlipper tự động chuyển đổi hình
            vfQuangCao.setAutoStart(true);
            vfQuangCao.setFlipInterval(3500);
            Animation inAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_in);
            Animation outAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_out);
            vfQuangCao.setInAnimation(inAnimation);
            vfQuangCao.setOutAnimation(outAnimation);
        }
        else
        {
            Toast.makeText(this, "Vui lòng kiểm tra lại kết nối Internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        updateTitleMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void updateTitleMenu(Menu menu)
    {
        MenuItem menuItemDangNhap = menu.findItem(R.id.itDangNhap);
        MenuItem menuItemDangXuat = menu.findItem(R.id.itDangXuat);
        if(AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired())
        {
            //hienThiThongTin();
            userName= FragmentDangNhap.userName;
            menuItemDangNhap.setTitle(userName);
            Log.d("UserName ", userName);
            menuItemDangXuat.setVisible(true);
        }
    }

    public void hienThiThongTin()
    {
        final GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback()
        {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                // Application code
                //Log.d("JSON ", response.getJSONObject().toString());
                // Lúc này hàm này sẽ nhận được 1 mảng JSON gồm thông tin người dùng đã Put qua
                try
                {
                    userName = object.getString("name");
                    id = object.getString("id");
                    link = object.getString("link");
                    email = object.getString("email");
                    //Log.d("UserName ", userName);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email");
        request.setParameters(parameters);
        request.executeAsync();
        //request.executeAndWait(); // Sau hàm này sẽ đợi AsysnTask chạy xong rồi có thể trả kết quả về
        //return duLieuJSONDangNhap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Nếu drawerToggle click thì đóng/mở tương ứng
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        switch (item.getItemId())
        {
            case R.id.itDangNhap:
                if(AccessToken.getCurrentAccessToken() == null)
                {
                    intent = new Intent(MainActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.itThongBao:
                break;
            case R.id.itDanhSachMongMuon:
                break;
            case R.id.itDonHangCuaToi:
                break;
            case R.id.itCaiDat:
                break;
            case R.id.itChinhSach:
                break;
            case R.id.itTroGiup:
                break;
            case R.id.itDangXuat:
                if(AccessToken.getCurrentAccessToken() != null)
                {
                    LoginManager.getInstance().logOut();
                    // Load lại Activity
                    finish();
                    startActivity(getIntent());
                }
                break;
            case R.id.itGioHang:
                intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
