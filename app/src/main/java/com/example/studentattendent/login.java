package com.example.studentattendent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText user,pass;
    private Button login;
    private String URL_LOGIN = "http://203.154.83.137/StudentAttendent/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        user = findViewById(R.id.userlogin);
        pass = findViewById(R.id.passwordlogin);
        login = findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String muser = user.getText().toString().trim();
                String mpass = pass.getText().toString().trim();
                if (!muser.isEmpty() || !mpass.isEmpty()){
                    login(muser,mpass);
//                    Intent intent = new Intent(login.this,MainActivity.class);
//                    startActivity(intent);
                }else {
                    user.setError("Please Insert username");
                    pass.setError("Please Insert password");
                }
            }
        });
    }

    private void login(final String user, final String pass){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_LOGIN,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")){
                                for (int i = 0;i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("role").trim();
                                    Toast.makeText(login.this, "Hello :"+name, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "ไม่สามารถเข้าสู่ระบบได้กรุณาเช็ค \n E-mail\n Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user",user);
                params.put("password",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
