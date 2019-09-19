package com.example.studentattendent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class teachAdapter extends RecyclerView.Adapter<teachAdapter.MyViewHolder> {

    Context mcontext;
    List<teach> mteach;
    View view;

    public teachAdapter(Context mcontext,List<teach> mteach) {
        this.mcontext = mcontext;
        this.mteach = mteach;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(mcontext).inflate(R.layout.item_timeteach,parent,false);
        teachAdapter.MyViewHolder vHolder = new teachAdapter.MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.isub.setText(mteach.get(position).getIdsub());
        holder.nub.setText(mteach.get(position).getNamesub());
        holder.csub.setText(mteach.get(position).getClasst());
        holder.rsub.setText(mteach.get(position).getClassR());
        holder.tsub.setText(mteach.get(position).getTimeT());



    }

    @Override
    public int getItemCount() {
        return mteach.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView isub,nub,tsub,csub,rsub;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            isub = itemView.findViewById(R.id.id_subject);
            nub = itemView.findViewById(R.id.name_subject);
            tsub = itemView.findViewById(R.id.timetablesubject);
            csub = itemView.findViewById(R.id.teacher_subject);
            rsub = itemView.findViewById(R.id.textView2);
        }
    }
}
