package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmc.interviewpreparationapp.R;

import java.util.List;

import HelperClasses.Feedbackhelper;

public class Feedbackadapteruser extends RecyclerView.Adapter<Feedbackadapteruser.MyviewHolder> {


    public List<Feedbackhelper> list;
    Context context;
    private MyviewHolder holder;
    private int position;

    public Feedbackadapteruser(List<Feedbackhelper> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.feedbacklistadapterview,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Feedbackhelper feedbackhelper=list.get(position);
        holder.intvname.setText("Interviewer name: " +feedbackhelper.getInterviewername().toString());
        holder.intvfeedback.setText("Feedback give:" +feedbackhelper.getFeedback());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyviewHolder extends RecyclerView.ViewHolder{

        TextView intvname,intvfeedback;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            intvname=itemView.findViewById(R.id.intvname);
            intvfeedback=itemView.findViewById(R.id.intvfeedback);
        }
    }
}
