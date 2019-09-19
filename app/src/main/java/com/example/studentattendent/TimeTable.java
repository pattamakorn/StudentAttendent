package com.example.studentattendent;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTable extends Fragment {
    private TextView daynow,datenow;
    String myear,mday;
    String myuser,myname;
    SessionManager sessionManager;

    View v;

    private RecyclerView recyclerView;
    private List<stimetable> listtimetable;
    private String Url_Loadtimeble = "http://203.154.83.137/StudentAttendent/loaduser.php";


    public TimeTable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_time_table, container, false);

        recyclerView = v.findViewById(R.id.recyclertime);
        daynow = v.findViewById(R.id.daynow);
        datenow = v.findViewById(R.id.datenow);
        timetableAdapter TimetableAdapter = new timetableAdapter(getContext(),listtimetable);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(TimetableAdapter);
        sessionManager = new SessionManager(getContext());
        HashMap<String,String>date = sessionManager.getDate();
        mday = date.get(sessionManager.DAY);
        myear = date.get(sessionManager.DATE);

        HashMap<String,String>user = sessionManager.getUserDetail();
        myname = user.get(sessionManager.NAME);
        myuser = user.get(sessionManager.USER);

        datenow.setText(myear);
        daynow.setText(mday);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listtimetable = new ArrayList<>();

        loadtime();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadtime(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url_Loadtimeble, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String fnameTe = posts.getString("fnameteacher");
                        String lnameTe = posts.getString("lnameteacher");
                        listtimetable.add(new stimetable(
                                posts.getString("idsubject"),
                                posts.getString("subjectname"),
                                fnameTe + " " + lnameTe,
                                posts.getString("classroom"),
                                posts.getString("time"))
                        );
                        timetableAdapter TimetableAdapter = new timetableAdapter(getContext(),listtimetable);
                        recyclerView.setAdapter(TimetableAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("abc",error.toString());
                       // Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("day",mday);
                params.put("username","admins");
                params.put("term","1");
                params.put("year","2562");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


//    private void loadtime(){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,Url_Loadtimeble,
//                new Response.Listener<String>(){
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String success = jsonObject.getString("success");
//                            JSONArray jsonArray = jsonObject.getJSONArray("read");
//                            if (success.equals("11")){
//                                for (int i = 0;i < jsonArray.length();i++){
//                                    JSONObject object = jsonArray.getJSONObject(i);
//                                    String fnameTe = object.getString("fnameteacher");
//                                    String lnameTe = object.getString("lnameteacher");
//                                    listtimetable.add(new stimetable(
//                                                  object.getString("idsubject"),
//                                                  object.getString("subjectname"),
//                                                  fnameTe + " " + lnameTe,
//                                                  object.getString("classroom"),
//                                                  object.getString("time"))
//                        );
//                        timetableAdapter TimetableAdapter = new timetableAdapter(getContext(),listtimetable);
//                        recyclerView.setAdapter(TimetableAdapter);
//
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("user","admins");
//                params.put("day","Monday");
//                params.put("term","2");
//                params.put("pyear","2562");
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);
//    }


}
