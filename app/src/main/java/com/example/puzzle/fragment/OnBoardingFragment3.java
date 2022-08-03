package com.example.puzzle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.puzzle.R;
import com.example.puzzle.ui.MainActivity;

public class OnBoardingFragment3 extends Fragment {

    TextView txt_finish3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root3 = inflater.inflate(R.layout.fragment_on_boarding3, container, false);

        txt_finish3 = root3.findViewById(R.id.txt_finish3);


        txt_finish3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        return root3;
    }
}
