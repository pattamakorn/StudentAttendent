package com.example.studentattendent;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
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
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeTeacher extends Fragment implements teachposttext.teachposttextListener{
    private TextView clickpost;
    private String textpostja;
    private ImageView click;
    View v;

    private RecyclerView recyclerView;
    private List<news> listnews;
    private String Url_Loadnews = "http://203.154.83.137/StudentAttendent/loadnews.php";


    private String TURL_Profile = "http://203.154.83.137/StudentAttendent/loaduserteacher.php";
    private ImageView imgprofileT,profileS;
    private TextView Tname,Tclass,teacher_sub,teacher_tel;


    public homeTeacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home_teacher, container, false);

//        imgprofileT = v.findViewById(R.id.teacherProfile);
//        Tname = v.findViewById(R.id.fullnameteacher);
//        Tclass = v.findViewById(R.id.adviser);
//        teacher_tel = v.findViewById(R.id.mytelteacher);

        recyclerView = v.findViewById(R.id.recyclerrrr);
        newsAdapter NewsAdapter = new newsAdapter(getContext(),listnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(NewsAdapter);
        imgprofileT = v.findViewById(R.id.teacherProfile);
        Tname = v.findViewById(R.id.fullnameteacher);

        return v;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listnews = new ArrayList<>();

        loadnews();
        loadprofileteacher();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = view.findViewById(R.id.impost);
        clickpost = view.findViewById(R.id.textpost);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        clickpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    public void loadnews(){StringRequest stringRequest = new StringRequest(Request.Method.POST,
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


    public void loadprofileteacher(){
        StringRequest stringRequest = new StringRequest(
                TURL_Profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String imgP = posts.getString("imgT");
                        String fnameT = posts.getString("fnameteacher");
                        String lnameT = posts.getString("lnameteacher");
                        Glide.with(v.getContext()).load(imgP).into(imgprofileT);
                        Tname.setText(fnameT+" "+lnameT);
                        //Toast.makeText(v.getContext(), fnameS, Toast.LENGTH_SHORT).show();
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

    public void openDialog(){
        teachposttext Teachposttext = new teachposttext();
        Teachposttext.show(getChildFragmentManager(),"testDialog");

    }

    @Override
    public void applyTexts(String textPost) {
        textpostja = textPost;
        Toast.makeText(getContext(), textpostja, Toast.LENGTH_SHORT).show();
        //onResume();

//        getFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();


    }
}
