package com.example.studentattendent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class timetableAdapter extends RecyclerView.Adapter<timetableAdapter.MyViewHolder> {
    Context mcontext;
    List<stimetable> mtime;
    View view;

    public timetableAdapter(Context mcontext,List<stimetable> mtime) {
        this.mcontext = mcontext;
        this.mtime = mtime;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(mcontext).inflate(R.layout.item_timetable,parent,false);
        timetableAdapter.MyViewHolder vHolder = new timetableAdapter.MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull timetableAdapter.MyViewHolder holder, int position) {
        holder.idsub.setText(mtime.get(position).getIdsubject());
        holder.nabsub.setText(mtime.get(position).getNamesubject());
        holder.teachsub.setText(mtime.get(position).getSubjectteacher());
        holder.studyclass.setText(mtime.get(position).getClassroom());
        holder.timesub.setText(mtime.get(position).getTeachtime());

    }

    @Override
    public int getItemCount() {
        return mtime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView idsub,nabsub,teachsub,studyclass,timesub;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idsub = itemView.findViewById(R.id.id_subject);
            nabsub = itemView.findViewById(R.id.name_subject);
            teachsub = itemView.findViewById(R.id.teacher_subject);
            studyclass = itemView.findViewById(R.id.textView2);
            timesub = itemView.findViewById(R.id.timetablesubject);
        }
    }
}
