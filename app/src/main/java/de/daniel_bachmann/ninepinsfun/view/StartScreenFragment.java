package de.daniel_bachmann.ninepinsfun.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.controller.NinepinsAppController;

public class StartScreenFragment extends Fragment {

    private View fragmentView;

    public StartScreenFragment() {
        // Required empty public constructor
    }

    public static StartScreenFragment newInstance() {
        StartScreenFragment fragment = new StartScreenFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_start_screen, container, false);

        fragmentView.findViewById(R.id.newGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NinepinsAppController.getCurrentSubCtrl().onFragmentButton(view);
            }
        });


        return fragmentView;
    }

}
