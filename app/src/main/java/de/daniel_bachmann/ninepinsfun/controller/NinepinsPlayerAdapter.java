package de.daniel_bachmann.ninepinsfun.controller;

import android.content.DialogInterface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;

public class NinepinsPlayerAdapter extends RecyclerView.Adapter<NinepinsPlayerAdapter.PlayerHolder>{

    private ArrayList<NinepinsPlayer> mPlayers;
    private ArrayList<Boolean> markedArray = new ArrayList<Boolean>();
    private int markedCount;
    private View mRootView;
    private RecyclerView mRecyclerView;

    private View mInflatedView;

    public void addPlayer(NinepinsPlayer player){
        mPlayers.add(player);
        markedArray.add(false);
        notifyItemChanged(getItemCount() - 1);
    }

    public ArrayList<NinepinsPlayer> getMarkedPlayers(){
        ArrayList<NinepinsPlayer> tmpList = new ArrayList<NinepinsPlayer>();
        for(int i = 0; i < mPlayers.size(); i++){
            if(markedArray.get(i)){
                tmpList.add(mPlayers.get(i));
            }
        }

        return tmpList;
    }

    public void deleteMarkedPlayers(){
        //sure?????
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        for(int i = markedArray.size()-1; i >= 0; i--){
                            if(markedArray.get(i)){
                                mPlayers.get(i).deleteFromDatabase();
                                mPlayers.remove(i);

                                mRecyclerView.removeViewAt(i);
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, mPlayers.size());

                                setMark(i,false);

                                markedArray.remove(i);
                            }
                        }

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(mRootView.getContext());
        builder.setMessage(markedCount + " Spieler löschen?").setPositiveButton("Ja, klar!", dialogClickListener)
                .setNegativeButton("Nö, doch nicht.", dialogClickListener).show();
    }

    public NinepinsPlayerAdapter(ArrayList<NinepinsPlayer> players){
        mPlayers = players;
        for(int i = 0; i< mPlayers.size(); i++){
            markedArray.add(false);
        }

        markedCount = 0;
    }

    @Override
    public NinepinsPlayerAdapter.PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mRootView = parent.getRootView();
        mRecyclerView = (RecyclerView) parent;

        mInflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item_layout, parent, false);
        return new PlayerHolder(mInflatedView).setAdapter(this);
    }

    @Override
    public void onBindViewHolder(NinepinsPlayerAdapter.PlayerHolder holder, int position) {
        NinepinsPlayer player = mPlayers.get(position);
        holder.setFields(player);
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void setMark(int position, boolean marked){
        markedArray.set(position,marked);
        if(marked){
            markedCount++;
        }else{
            markedCount--;
        }

        Button tmpButton = ((Button) mRootView.findViewById(R.id.continuePlayers));
        tmpButton.setText("Weiter ( "+markedCount+" Spieler )");
        if(markedCount == 0){
            tmpButton.setVisibility(View.INVISIBLE);
        }else{
            tmpButton.setVisibility(View.VISIBLE);
        }
    }



    public static class PlayerHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private TextView mPlayerName;

        private NinepinsPlayerAdapter mAdapter;

        private boolean marked = false;
        private View mInflatedView;

        public PlayerHolder(View view){
            super(view);

            mInflatedView = view;

            mPlayerName = (TextView) view.findViewById(R.id.playerName);
            mPlayerName.setOnClickListener(this);
        }

        public void setFields(NinepinsPlayer player) {
            mPlayerName.setText(player.getmName());
        }



        @Override
        public void onClick(View view) {
            int drawableId;
            if(marked){
                marked = false;
                drawableId = R.drawable.throw_number_button;
            }else{
                marked = true;
                drawableId = R.drawable.throw_number_marked;
            }

            mAdapter.setMark(getAdapterPosition(), marked);

            mPlayerName
                    .setBackground(
                            ResourcesCompat.getDrawable(
                                    NinepinsAppController.getActivity().getResources(),
                                    drawableId,
                                    null)
                    );
        }

        public PlayerHolder setAdapter(NinepinsPlayerAdapter adapter){
            mAdapter = adapter;
            return this;
        }

        public PlayerHolder setInflatedView(View inflatedView){
            mInflatedView = inflatedView;
            return this;
        }
    }
}
