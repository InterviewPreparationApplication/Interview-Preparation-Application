package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmc.interviewpreparationapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import HelperClasses.Javademoquestionhelper;
public class JavaQuestionAdapter extends FirebaseRecyclerAdapter<Javademoquestionhelper,JavaQuestionAdapter.MyViewHolder> {

    ArrayList<Javademoquestionhelper> listjavaquestion;
    Context context;

    public JavaQuestionAdapter(@NonNull FirebaseRecyclerOptions<Javademoquestionhelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Javademoquestionhelper model) {

        holder.question.setText("Question: " +model.getQue());
        holder.answer.setText("Answer: " +model.getAns());

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.java_question,parent,false);
        return  new MyViewHolder(view);
    }

//    public JavaQuestionAdapter(Context context,ArrayList<Javademoquestionhelper> listjavaquestion) {
//        this.listjavaquestion = listjavaquestion;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=LayoutInflater.from(context).inflate(R.layout.java_question,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Javademoquestionhelper javademoquestionhelper=listjavaquestion.get(position);
//        holder.question.setText(javademoquestionhelper.getQuestion());
//        holder.answer.setText(javademoquestionhelper.getAnswer());
//    }
//
//    @Override
//    public int getItemCount() {
//        return listjavaquestion.size();
//    }

     class MyViewHolder extends RecyclerView.ViewHolder{

        TextView question,answer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            answer=itemView.findViewById(R.id.answer);
        }



    }
}
