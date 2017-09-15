package de.daniel_bachmann.ninepinsfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.daniel_bachmann.ninepinsfun.controller.NinepinsAppController;

public class NinepinsManagePlayerActivity extends AppCompatActivity {

    private NinepinsAppController mAppController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninepins_player);

        mAppController = NinepinsAppController.getController(this).setCurrentActivity(this);
    }
}
