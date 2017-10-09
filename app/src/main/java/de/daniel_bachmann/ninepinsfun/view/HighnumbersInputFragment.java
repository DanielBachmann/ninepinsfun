package de.daniel_bachmann.ninepinsfun.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.model.NinepinsGames;
import de.daniel_bachmann.ninepinsfun.model.NinepinsThrows;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighnumbersInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighnumbersInputFragment extends Fragment {

    private NinepinsThrows mThrowInput;
    private NinepinsGames mGame;
    private int mRound;

    private Button position1Button = null;
    private Button position2Button = null;
    private Button position3Button = null;

    private View fragmentView;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onPositionClick(view);
        }
    };

    public HighnumbersInputFragment() {
        // Required empty public constructor
    }

    public HighnumbersInputFragment setThrowInput(NinepinsThrows throwInput){
        mThrowInput = throwInput;
        return this;
    }

    public HighnumbersInputFragment setPositionsHolder(){

        return this;
    }

    public HighnumbersInputFragment setPositions(Integer position_1, Integer position_2, Integer position_3){

        return this;
    }

    public static HighnumbersInputFragment newInstance(NinepinsThrows throwInput) {
        HighnumbersInputFragment fragment = new HighnumbersInputFragment();
        fragment.setThrowInput(throwInput);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onPositionClick(View view){
        //whatever we did before with newPositions (if anything) is now irrelevant


        int position = -1;

        switch (view.getId()){

            case R.id.position_1:
                position = 0;
                break;

            case R.id.position_2:
                position = 1;
                break;

            case R.id.position_3:
                position = 2;
                break;

        }


        //reset buttons
        initPositionButtons();

        //and give the one clicked a value
        ((Button)view).setText(""+mThrowInput.getmPoints());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_highnumber_input, container, false);



        initPositionButtons();

        /*Button button;

        for(int buttonId : numberButtons){
            Button btn = (Button) fragmentView.findViewById(buttonId);
            btn.setOnClickListener(this);
        }

        for(int buttonId : additionalButtons){
            Button btn = (Button) fragmentView.findViewById(buttonId);
            btn.setOnClickListener(this);
        }

        ((TextView)fragmentView.findViewById(R.id.playerName)).setText(mPlayer.getmName().toUpperCase());*/

        return fragmentView;
    }

    private void initPositionButtons(){

    }

}
