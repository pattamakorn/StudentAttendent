package com.example.studentattendent;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.MyViewHolder> {

    Context mcontext;
    List<news> mnews;
    View view;
    Dialog myDialog;
    private ImageView img;

    public newsAdapter(Context mcontext, List<news> mnews) {
        this.mcontext = mcontext;
        this.mnews = mnews;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_news,parent,false);
        MyViewHolder vHolder = new MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameteachpost.setText(mnews.get(position).getTeachpost());
        holder.showtopic.setText(mnews.get(position).getTopic());
        holder.textpost.setText(mnews.get(position).getMassagepost());
        holder.postdata.setText(mnews.get(position).getDatepost());
        // holder.url_ima.setImageURI();

        // holder.url_ima.setText(mfoothit.get(position).getIma());
        // Picasso.get().load("http://i.imgur.com/DvpvklR.png").into();
        Glide.with(view.getContext()).load(mnews.get(position).getIma()).into(holder.url_ima);

    }

    @Override
    public int getItemCount() {
        return mnews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameteachpost,showtopic,textpost,postdata;
        public ImageView url_ima;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameteachpost = itemView.findViewById(R.id.nameteacherpost);
            showtopic = itemView.findViewById(R.id.topic);
            textpost = itemView.findViewById(R.id.maseeageNews);
            postdata = itemView.findViewById(R.id.DataOfPost);
            url_ima = itemView.findViewById(R.id.imagetPost);

        }
    }
}
