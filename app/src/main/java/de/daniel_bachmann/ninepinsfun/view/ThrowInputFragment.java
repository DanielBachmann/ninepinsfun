package de.daniel_bachmann.ninepinsfun.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;

public class ThrowInputFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NINEPINS_PLAYER = "NinepinsPlayer";

    // TODO: Rename and change types of parameters
    private long mPlayerId;

    private OnFragmentInputListener mListener;

    public ThrowInputFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ThrowInputFragment newInstance(){//NinepinsPlayer player) {
        ThrowInputFragment fragment = new ThrowInputFragment();
        /*Bundle args = new Bundle();

        args.putLong(NINEPINS_PLAYER, player.getmId());
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Again: its questionable if the whole player object is needed...
            mPlayerId = getArguments().getLong(NINEPINS_PLAYER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_throw_input, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInputListener) {
            mListener = (OnFragmentInputListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInputListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInputListener {
        // TODO: Update argument type and name
        void onFragmentInput(long playerId, long points);
    }
}
