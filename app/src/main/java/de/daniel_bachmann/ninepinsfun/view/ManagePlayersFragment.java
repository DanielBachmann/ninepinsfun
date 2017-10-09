package de.daniel_bachmann.ninepinsfun.view;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.controller.NinepinsAppController;
import de.daniel_bachmann.ninepinsfun.controller.NinepinsPlayerAdapter;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagePlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagePlayersFragment extends Fragment {

    private View fragmentView;
    private Button addPlayerButton;
    private Button deleteMarkedPlayersButton;
    private Button continuePlayersButton;
    private NinepinsPlayerAdapter mAdapter;

    private ArrayList<NinepinsPlayer> mPlayersList;

    public static ManagePlayersFragment newInstance() {
        ManagePlayersFragment fragment = new ManagePlayersFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onAddPlayer(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Bitte Namen eingeben:");

        final EditText input = new EditText(getActivity());

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playerName = input.getText().toString();
                mAdapter.addPlayer(new NinepinsPlayer().setmName(playerName).saveToDatabase());
            }
        });
        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void onDeleteMarkedPlayers(){
        mAdapter.deleteMarkedPlayers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_manage_players, container, false);

        addPlayerButton = fragmentView.findViewById(R.id.addPlayer);
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddPlayer(view);
            }
        });

        deleteMarkedPlayersButton = fragmentView.findViewById(R.id.deleteMarkedPlayers);
        deleteMarkedPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteMarkedPlayers();
            }
        });

        continuePlayersButton = fragmentView.findViewById(R.id.continuePlayers);
        continuePlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NinepinsAppController.getController(getContext()).onManagePlayersContinue(mAdapter.getMarkedPlayers());
            }
        });

        mPlayersList = NinepinsPlayer.getAllPlayers();

        RecyclerView recyclerView = fragmentView.findViewById(R.id.managePlayersRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPlayersList = NinepinsPlayer.getAllPlayers();
        mAdapter = new NinepinsPlayerAdapter(mPlayersList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        return fragmentView;
    }

}
