package de.daniel_bachmann.ninepinsfun.controller;

import android.content.res.Resources;

import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;
import de.daniel_bachmann.ninepinsfun.view.ThrowInputFragment;

public class NinepinsStartScreenCtrl {

    public NinepinsStartScreenCtrl(){

    }

    public NinepinsStartScreenCtrl startFragment(){
        Resources res = NinepinsAppController.getActivity().getResources();

        NinepinsAppController.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(ThrowInputFragment.newInstance( ,new NinepinsPlayer().loadById(1)), )

        return this;
    }
}
