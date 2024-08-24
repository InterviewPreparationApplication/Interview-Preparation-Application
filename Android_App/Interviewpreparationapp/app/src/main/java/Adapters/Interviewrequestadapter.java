package Adapters;

import static Adapters.InterviewerlistAdapter.LoggedinUser;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmc.interviewpreparationapp.R;
import com.dmc.interviewpreparationapp.VideoCallActivityInterviwerside;
import com.dmc.interviewpreparationapp.VideoCallActivityUserside;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import HelperClasses.Interviewerrecivedreqhelper;
import HelperClasses.UserHelperClass;

public class Interviewrequestadapter extends RecyclerView.Adapter<Interviewrequestadapter.MyViewHolder> {


    List<Interviewerrecivedreqhelper> userlist;


    Context context;

    public Interviewrequestadapter(List<Interviewerrecivedreqhelper> dataList,Context context) {
        this.userlist=dataList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.interviewrequestadapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Interviewerrecivedreqhelper interviewerrecivedreqhelper=userlist.get(position);
        holder.Uname.setText("Student name: " +interviewerrecivedreqhelper.getFirstname());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Uname;
        Button acceptdbt,cancelbt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Uname=itemView.findViewById(R.id.Uname);
            acceptdbt=itemView.findViewById(R.id.acceptdbt);
            cancelbt=itemView.findViewById(R.id.cancelbt);

            acceptdbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context != null) {
                        Intent intent = new Intent(context, VideoCallActivityUserside.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

}
