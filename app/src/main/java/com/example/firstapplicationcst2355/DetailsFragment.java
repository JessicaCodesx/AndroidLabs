package com.example.firstapplicationcst2355;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        //retrieve arguments
        Bundle args = getArguments();
        if (args != null) {
            //populate data from the bundle
            String name = args.getString("name", "Fill_Me");
            String height = args.getString("height", "Fill_Me");
            String mass = args.getString("mass", "Fill_Me");

            TextView textViewName = view.findViewById(R.id.textViewName);
            TextView textViewHeight = view.findViewById(R.id.textViewHeight);
            TextView textViewMass = view.findViewById(R.id.textViewMass);

            //set text for text views
            textViewName.setText(name);
            textViewHeight.setText(height);
            textViewMass.setText(mass);
        }

        return view;
    }
}
