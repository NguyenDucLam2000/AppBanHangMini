package com.example.appbanhangmini.View.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangmini.R;
import com.example.appbanhangmini.View.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener
{
    TextView txtQuenMatKhau;
    EditText edtDiaChiEmail, edtMatKhau;
    Button btnDangNhap, btnDangNhapVoiFacebook, btnDangNhapVoiGoogle;
    CallbackManager callbackManager;
    Intent intent;
    public static String userName, email, link, id, JSONLayVe;
    //AccessToken accessToken;

    private void addControls(View view)
    {
        txtQuenMatKhau = view.findViewById(R.id.txtQuenMatKhau);
        edtDiaChiEmail = view.findViewById(R.id.edtDiaChiEmail);
        edtMatKhau = view.findViewById(R.id.edtMatKhau);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btnDangNhapVoiFacebook = view.findViewById(R.id.btnDangNhapVoiFacebook);
        btnDangNhapVoiGoogle = view.findViewById(R.id.btnDangNhapVoiGoogle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);
        FacebookSdk.sdkInitialize(getContext());
        // callbackManager để khi chúng ta gửi lên sever 1 thông điệp thì sever sẽ trả về cho chúng
        // ta một lời nhắn thông qua hàm onActivityResult
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                hienThiThongTin();
                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                // Đăng nhập thành công thì mới lưu lại thông tin trong cache của facebook
                // Muốn lấy thông tin đó thì phải thông qua GRAP API
                // Muốn lấy dữ liệu của người dùng để hiển thị thì phải trong qua GrapRequest
            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException error)
            {

            }
        });
        addControls(view);
        addEvents();
        return view;
    }

    public String hienThiThongTin()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                super.run();
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

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        JSONLayVe = response.getJSONObject().toString();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email");
                request.setParameters(parameters);
                //request.executeAsync();
                request.executeAndWait(); // Sau hàm này sẽ đợi AsysnTask chạy xong rồi có thể trả kết quả về
            }
        };
        thread.start();
        return userName;
    }

    private void addEvents()
    {
        btnDangNhapVoiFacebook.setOnClickListener(this);
        btnDangNhapVoiGoogle.setOnClickListener(this);
    }

    @Override
    public String toString()
    {
        return "Đăng nhập";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnDangNhapVoiFacebook:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_status", "email"));
                break;
            case R.id.btnDangNhapVoiGoogle:
                break;
            default:
                break;
        }
    }
}
