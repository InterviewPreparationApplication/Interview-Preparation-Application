package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dmc.interviewpreparationapp.CppInterviewQuestion;
import com.dmc.interviewpreparationapp.JavaInterViewQuestion;
import com.dmc.interviewpreparationapp.MysqlInterviewQuestion;
import com.dmc.interviewpreparationapp.R;

public class PracticeQuestionFragment extends Fragment {
    CardView javacardview,cppcardview,mysqlcardview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return inflater.inflate(R.layout.fragment_practice_question, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        javacardview=view.findViewById(R.id.javacardview);
        cppcardview=view.findViewById(R.id.cppcardview);
        mysqlcardview=view.findViewById(R.id.mysqlcardview);

        javacardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), JavaInterViewQuestion.class));

            }
        });

        cppcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CppInterviewQuestion.class));
            }
        });

        mysqlcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MysqlInterviewQuestion.class));
            }
        });
    }

}