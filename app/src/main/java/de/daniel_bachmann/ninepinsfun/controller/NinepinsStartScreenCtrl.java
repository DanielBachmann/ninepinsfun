package de.daniel_bachmann.ninepinsfun.controller;

import android.support.v4.app.Fragment;
import android.view.View;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.view.ManagePlayersFragment;
import de.daniel_bachmann.ninepinsfun.view.StartScreenFragment;

public class NinepinsStartScreenCtrl implements SubCtrlInterface{

    public NinepinsStartScreenCtrl(){

    }

    public Fragment startFragment(){
        Fragment fragmentHolder = StartScreenFragment.newInstance();

        NinepinsAppController.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_layout, fragmentHolder, "startScreen")
                .commit();

        return fragmentHolder;
    }

    public void onFragmentButton(View view){
        NinepinsAppController.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, ManagePlayersFragment.newInstance(), "managePlayers")
                .addToBackStack(null)
                .commit();
    }
}
