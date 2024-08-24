package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmc.interviewpreparationapp.Candidateinfodataadmin;
import com.dmc.interviewpreparationapp.InterviwerinfodataAdmin;
import com.dmc.interviewpreparationapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class Interviewerinfoadapter extends RecyclerView.Adapter<Interviewerinfoadapter.MyViewHolder> {

    List<Interviewerhelper> userlist;
    Context context;

    public Interviewerinfoadapter(List<Interviewerhelper> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.candidateinfodesighnadapter, parent, false);
        return new Interviewerinfoadapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Interviewerhelper interviewerhelper = userlist.get(position);
        holder.Uname.setText("Interviwer name: " + interviewerhelper.getInterviewerfirstname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Get the clicked item
                    Interviewerhelper clickedItem = userlist.get(adapterPosition);
                    String userId = clickedItem.getInterviewermobno(); // Assuming you have a method to get user ID

                    // Fetch data from Firebase and start new activity
                    fetchDataAndStartActivity(userId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Uname;
        ImageView seeinfoarrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Uname = itemView.findViewById(R.id.Uname);
            seeinfoarrow = itemView.findViewById(R.id.seeinfoarrow);
        }
    }

    private void fetchDataAndStartActivity(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Interviwer").child(userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Interviewerhelper userData = dataSnapshot.getValue(Interviewerhelper.class);
                    if (userData != null) {

                        // Start new activity and pass the UserHelperClass object
                        Intent intent = new Intent(context, InterviwerinfodataAdmin.class);
                        intent.putExtra("iuser", userData);
                        context.startActivity(intent);
                    }
                } else {
                    // Data does not exist
                    Toast.makeText(context, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
