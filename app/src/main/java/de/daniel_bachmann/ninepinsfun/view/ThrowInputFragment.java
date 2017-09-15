package de.daniel_bachmann.ninepinsfun.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.controller.NinepinsAppController;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;
import de.daniel_bachmann.ninepinsfun.model.NinepinsThrows;

public class ThrowInputFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NINEPINS_PLAYER = "NinepinsPlayer";

    //Todo: change to enum when I actually remember java again... (?)
    private static final int[] numberButtons = {
            R.id.points_1,
            R.id.points_2,
            R.id.points_3,
            R.id.points_4,
            R.id.points_5,
            R.id.points_6,
            R.id.points_7,
            R.id.points_8,
            R.id.points_9
    };

    private static final int[] additionalButtons = {
        R.id.resetThrow,
        R.id.confirmThrow
    };

    private View fragmentView;

    private long points;

    private NinepinsPlayer mPlayer;

    private OnFragmentInputListener mListener;

    public ThrowInputFragment() {
        // Required empty public constructor
    }

    public static ThrowInputFragment newInstance(NinepinsPlayer player) {
        ThrowInputFragment fragment = new ThrowInputFragment();
        fragment.setNinepinsPlayer(player);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_throw_input, container, false);

        Button button;

        for(int buttonId : numberButtons){
            Button btn = (Button) fragmentView.findViewById(buttonId);
            btn.setOnClickListener(this);
        }

        for(int buttonId : additionalButtons){
            Button btn = (Button) fragmentView.findViewById(buttonId);
            btn.setOnClickListener(this);
        }

        ((TextView)fragmentView.findViewById(R.id.playerName)).setText(mPlayer.getmName().toUpperCase());

        return fragmentView;
    }

    //Todo: I don't want the listener implemented in the activity now.
    //As I don't see right now, how to make a similar check, I just don't...
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setListener(NinepinsAppController.getController(context));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Fragment button handlers
    public void onClick(View view) {
        Button btn = (Button) view;

        //Todo: pretty sure there is a better way...
        switch (view.getId()) {
            case R.id.points_1:
                markNumbers(1);
                break;

            case R.id.points_2:
                markNumbers(2);
                break;

            case R.id.points_3:
                markNumbers(3);
                break;

            case R.id.points_4:
                markNumbers(4);
                break;

            case R.id.points_5:
                markNumbers(5);
                break;

            case R.id.points_6:
                markNumbers(6);
                break;

            case R.id.points_7:
                markNumbers(7);
                break;

            case R.id.points_8:
                markNumbers(8);
                break;

            case R.id.points_9:
                markNumbers(9);
                break;

            case R.id.resetThrow:
                markNumbers(0);
                break;

            case R.id.confirmThrow:
                //Todo: I'm recklessly saving the player here, to ensure it has/gets an db id
                //probably better to ensure I get one with an id... (Exception?)
                mPlayer.saveToDatabase();
                mListener.onFragmentThrowInput(new NinepinsThrows().setmPlayer(mPlayer.getmId()).setmPoints(points));
                break;
        }
    }

    public ThrowInputFragment setNinepinsPlayer(NinepinsPlayer player){
        mPlayer = player;
        return this;
    }

    public ThrowInputFragment setListener(OnFragmentInputListener listener){
        mListener = listener;
        return this;
    }

    private void markNumbers(int till){
        points = till;

        if(points == 0){
            ((Button)fragmentView.findViewById(R.id.confirmThrow)).setText("Weiter (Pumpe)");
        }else{
            ((Button)fragmentView.findViewById(R.id.confirmThrow)).setText("Weiter ("+points+" Punkte)");
        }

        int drawableId; //Todo: better name?
        Resources res = getResources();
        for(int i=0; i < 9; i++){
            if(i < till) {
                drawableId = R.drawable.throw_number_marked;
            }else{
                drawableId = R.drawable.throw_number_button;
            }

            fragmentView.findViewById(numberButtons[i]).setBackground(ResourcesCompat.getDrawable(res, drawableId, null));
        }

    }
}
