package de.daniel_bachmann.ninepinsfun.controller;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import de.daniel_bachmann.ninepinsfun.model.NinepinsDatabase;
import de.daniel_bachmann.ninepinsfun.model.NinepinsGames;
import de.daniel_bachmann.ninepinsfun.model.NinepinsGamesPlayers;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlaces;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;
import de.daniel_bachmann.ninepinsfun.model.NinepinsThrows;
import de.daniel_bachmann.ninepinsfun.view.OnFragmentInputListener;

public class NinepinsAppController implements OnFragmentInputListener{

    private static NinepinsAppController staticController = null;

    private Context mContext;
    private AppCompatActivity mCurrentActivity;

    private Fragment mFragmentHolder;

    private ArrayList<NinepinsPlayer> mPlayerList;
    private NinepinsPlaces mPlace;
    private int activePlayer = 0;

    private int countThrowsInGame = 0;
    private int round = 0;

    private NinepinsGames currentGame;

    public static NinepinsAppController getController(Context context){
        if(staticController==null){
            staticController = new NinepinsAppController(context);
        }
        return staticController;
    }

    public static AppCompatActivity getActivity() {

        return staticController.getCurrentActivity();
    }

    private NinepinsAppController(Context context){
        mContext = context.getApplicationContext();

        mCurrentActivity = (AppCompatActivity) context;

        NinepinsDatabase.initialize(mContext);
    }

    public void initGame(){
        //Todo: Choose game variation and place, offer choice to continue aborted games


    }

    private void startGame(){

    }


    @Override
    public void onFragmentThrowInput(NinepinsThrows throwResult) {
        throwResult.saveToDatabase();
        mCurrentActivity.getSupportFragmentManager().beginTransaction().remove(mFragmentHolder).commit();
        //next("highNumbersAddedData", throwResult);
    }

    public void onManagePlayersContinue(ArrayList<NinepinsPlayer> playerList){
        //enter relation of players to this game to the database
        mPlayerList = playerList;

        for(NinepinsPlayer player : mPlayerList){
            new NinepinsGamesPlayers()
                    .setWithObjects(currentGame,player)
                    .saveToDatabase();
        }

        mCurrentActivity.getSupportFragmentManager().beginTransaction().remove(mFragmentHolder).commit();

        startGame();
    }

    public NinepinsAppController setCurrentActivity(AppCompatActivity activity){
        mCurrentActivity = activity;
        return this;
    }

    private AppCompatActivity getCurrentActivity(){
        return mCurrentActivity;
    }


}
