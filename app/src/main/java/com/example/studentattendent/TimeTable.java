package com.example.studentattendent;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

    View v;

    private RecyclerView recyclerView;
    private List<stimetable> listtimetable;
    private String Url_Loadtimeble = "http://203.154.83.137/StudentAttendent/loadtimetable.php";


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

        Calendar c = Calendar.getInstance();
        SimpleDateFormat year = new SimpleDateFormat("dd/MM");
        myear = year.format(c.getTime());
        int d = c.get(Calendar.YEAR)+543;
        mday = new SimpleDateFormat("EEEE").format(c.getTime());

        datenow.setText(myear+"/"+d);
        daynow.setText(mday);
    }

    public void loadtime(){
        StringRequest stringRequest = new StringRequest(
                Url_Loadtimeble, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                    Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("day","Monday");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
