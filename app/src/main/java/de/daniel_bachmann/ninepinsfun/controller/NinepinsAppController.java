package de.daniel_bachmann.ninepinsfun.controller;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.model.*;
import de.daniel_bachmann.ninepinsfun.view.ManagePlayersFragment;
import de.daniel_bachmann.ninepinsfun.view.OnFragmentInputListener;

public class NinepinsAppController implements OnFragmentInputListener{

    private static NinepinsAppController staticController = null;

    private Context mContext;
    private AppCompatActivity mCurrentActivity;

    private Fragment mFragmentHolder;
    private SubCtrlInterface mSubController;

    private NinepinsGames mCurrentGame = null;
    private ArrayList<NinepinsPlayer> mPlayerList = null;
    private NinepinsGameVariationInterface mGameVariation = null;

    public static NinepinsAppController getController(Context context){
        if(staticController==null){
            staticController = new NinepinsAppController(context);
        }
        return staticController;
    }

    public static AppCompatActivity getActivity() {

        return staticController.getCurrentActivity();
    }

    private AppCompatActivity getCurrentActivity(){
        return mCurrentActivity;
    }

    public static SubCtrlInterface getCurrentSubCtrl(){
        return staticController.getmSubController();
    }

    public SubCtrlInterface getmSubController(){
        return mSubController;
    }

    private NinepinsAppController(Context context){
        mContext = context.getApplicationContext();

        mCurrentActivity = (AppCompatActivity) context;

        NinepinsDatabase.initialize(mContext);
    }

    public void initialize(){


        mFragmentHolder = ManagePlayersFragment.newInstance();

        NinepinsAppController.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_layout, mFragmentHolder, "ManagePlayers")
                .commit();
    }

    public void startGame(){

    }

    public void endGame(){

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

        mCurrentActivity.getSupportFragmentManager().beginTransaction().remove(mFragmentHolder).commit();

        //startGame();
    }

    public NinepinsAppController setCurrentActivity(AppCompatActivity activity){
        mCurrentActivity = activity;
        return this;
    }




}
