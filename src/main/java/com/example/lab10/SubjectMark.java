package com.example.lab10;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubjectMark extends Fragment{

    private String subject = "Subject";
    public SubjectMark() {
        // Required empty public constructor
    }
    public SubjectMark(String subject){
        this.subject = subject;
    }

    public interface SubjectMarkListener{
        public void getData(String data);
    }
    private SubjectMarkListener listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_subject_mark, container, false);

        TextView sub = v.findViewById(R.id.subname);
        final EditText mm = v.findViewById(R.id.mm);
        final EditText om = v.findViewById(R.id.om);
        sub.setText(subject);
        final Button trigger = v.findViewById(R.id.subjectPush);
        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = subject +","+ om.getText().toString().trim() + "," + mm.getText().toString().trim();
                listener.getData(data);
                trigger.setVisibility(View.GONE);
            }
        });
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if( context instanceof SubjectMarkListener){
            listener = (SubjectMarkListener) context;
        } else  {
            throw new RuntimeException(context.toString()+ " must implement SubjectDataListener ");
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}
