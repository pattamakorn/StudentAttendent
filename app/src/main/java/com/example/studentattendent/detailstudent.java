package com.example.studentattendent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class detailstudent extends AppCompatDialogFragment {
    View v;
    private EditText edtextpost;
    private TextView detailST,dtels,classd,parentd,adviserd,telAdvi;
    private detailstudent.detailListener listener;
    private ImageView imgProfile;
    private String URL_postdetail = "http://203.154.83.137/StudentAttendent/dtailst.php";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),R.style.AlertDialogCustom));
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.profile,null);

        builder.setView(v)
                .setTitle("ข้อมูลส่วนตัว")
                .setPositiveButton("ปิด", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        detailST = v.findViewById(R.id.dname);
        dtels = v.findViewById(R.id.dtel);
        classd = v.findViewById(R.id.dclass);
        parentd = v.findViewById(R.id.dparent);
        adviserd = v.findViewById(R.id.dadviser);
        telAdvi = v.findViewById(R.id.dTadviser);
        imgProfile = v.findViewById(R.id.detailProfile);
        loadDetail();

        return builder.create();
    }
    public interface  detailListener{
        void applyTexts(String textPost);

    }

    public void loadDetail(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_postdetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String fsname = posts.getString("fnamest");
                        String lsname = posts.getString("lnamest");
                        String classS = posts.getString("class");
                        String fpname = posts.getString("fnamePr");
                        String lpname = posts.getString("lnamePr");
                        String tpname = posts.getString("parenttel");
                        String tsname = posts.getString("stel");
                        String fnamet = posts.getString("fnameTC");
                        String lnamet = posts.getString("lnameTC");
                        String ttel = posts.getString("ttel");
                        String Tpic = posts.getString("imgst");

                        detailST.setText("● ชื่อ-นามสกุล: \n"+"    " +fsname+" "+lsname);
                        dtels.setText("● เบอร์โทรศัพท์: \n"+"    " +tsname);
                        classd.setText("● ชั้นมัธยมศึกษาปีที่ "+classS);
                        parentd.setText("● ผู้ปกครอง: \n"+"    " +fpname+" " +lpname+"\n\n" +
                                "● เบอร์โทรศัพท์ผู้ปกครอง:\n    "+tpname);
                        adviserd.setText("● ครูประจำชั้น: \n"+"    "+fnamet+" "+lnamet);
                        telAdvi.setText("● เบอร์โทรศัพท์ครูประจำชั้น: \n"+"    "+ttel);
                        Glide.with(v.getContext()).load(Tpic).into(imgProfile);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("abc",error.toString());
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user","admins");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
