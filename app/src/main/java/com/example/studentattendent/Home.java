package com.example.studentattendent;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    View v;
    private String URL_Profile = "http://203.154.83.137/StudentAttendent/studentprofile.php";
    private ImageView imgprofile;
    private TextView stdname,stdclass,teachername,teachertel;

    private RecyclerView recyclerView;
    private List<news> listnews;
    private String Url_Loadnews = "http://203.154.83.137/StudentAttendent/loadnews.php";

    SessionManager sessionManager;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        imgprofile = v.findViewById(R.id.rentProfile);
        stdname = v.findViewById(R.id.fullname);
        stdclass = v.findViewById(R.id.classstudent);
        teachername = v.findViewById(R.id.myteacher);
        teachertel = v.findViewById(R.id.teal_teacherClass);

        recyclerView = v.findViewById(R.id.recycler);
        newsAdapter NewsAdapter = new newsAdapter(getContext(),listnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(NewsAdapter);

        sessionManager = new SessionManager(getContext());
        sessionManager.checklogin();
        HashMap<String,String> user = sessionManager.getUserDetail();
        String myusername = user.get(sessionManager.USER);
        //String mypassword = user.get(sessionManager.PASS);
        String myname = user.get(sessionManager.NAME);



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listnews = new ArrayList<>();

//        listfoot.add(new footHit("ผัดกระเพา","กระเพาแล้วแต่","55 บาท"));
//        listfoot.add(new footHit("ข้าวผัดต้มยำ","เจ้ไก่ กังสดาล","45 บาท"));
//        loadfood();
        loadprofile();
        loadnews();
    }

    public void loadprofile(){
        StringRequest stringRequest = new StringRequest(
                URL_Profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String fnameS = posts.getString("fname");
                        String lnameL = posts.getString("lname");
                        String classja = posts.getString("class");
                        String imgP = posts.getString("img");
                        String fnameP = posts.getString("fparent");
                        String lnameP = posts.getString("lparent");
                        String fnameT = posts.getString("fteacher");
                        String lnameT = posts.getString("lteacher");
                        String Tth = posts.getString("Telteacher");
                        Glide.with(v.getContext()).load(imgP).into(imgprofile);
                        Toast.makeText(v.getContext(), fnameS, Toast.LENGTH_SHORT).show();
                        stdname.setText(fnameS+" "+lnameL);
                        stdclass.setText("มัธยมศึกษาปีที่ "+classja);
                        teachername.setText(fnameT+" "+lnameT);
                        teachertel.setText("เบอร์โทรศัพท์ "+Tth);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadnews(){StringRequest stringRequest = new StringRequest(
            Url_Loadnews, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject posts = array.getJSONObject(i);
                    String TFname = posts.getString("ptfname");
                    String TLname = posts.getString("ptlname");
                    String Txtpost = posts.getString("textnews");
                    String timenowPost = posts.getString("Tpost");
                    listnews.add(new news(
                            TFname+" "+TLname,
                            "หัวข้อเรื่อง : "+posts.getString("topic"),
                            Txtpost,
                            timenowPost,
                            posts.getString("img"))
                    );
                    newsAdapter NewsAdapter = new newsAdapter(getContext(),listnews);
                    recyclerView.setAdapter(NewsAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }

            }) {
    };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
