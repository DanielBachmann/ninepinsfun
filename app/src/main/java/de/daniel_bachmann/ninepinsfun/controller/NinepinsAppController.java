package de.daniel_bachmann.ninepinsfun.controller;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.daniel_bachmann.ninepinsfun.R;
import de.daniel_bachmann.ninepinsfun.model.NinepinsDatabase;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;
import de.daniel_bachmann.ninepinsfun.model.NinepinsThrows;
import de.daniel_bachmann.ninepinsfun.view.OnFragmentInputListener;
import de.daniel_bachmann.ninepinsfun.view.ThrowInputFragment;

public class NinepinsAppController implements OnFragmentInputListener{

    private static NinepinsAppController staticController = null;

    private Context mContext;
    private AppCompatActivity mCurrentActivity;

    private Fragment mFragmentHolder;

    public static NinepinsAppController getController(Context context){
        if(staticController==null){
            staticController = new NinepinsAppController(context);
        }
        return staticController;
    }

    private NinepinsAppController(Context context){
        mContext = context.getApplicationContext();

        mCurrentActivity = (AppCompatActivity) context;

        NinepinsDatabase.initialize(mContext);

        testCode();
    }

    private void testCode(){
        try{
            mFragmentHolder = ThrowInputFragment.newInstance(new NinepinsPlayer().loadById(1));
        }catch (Exception e){
            mFragmentHolder = ThrowInputFragment.newInstance(new NinepinsPlayer().setmName("Pumpen Toni").saveToDatabase());
        }

        mCurrentActivity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_layout, mFragmentHolder , "throw")
                .commit();
    }

    @Override
    public void onFragmentThrowInput(NinepinsThrows throwResult) {
        mCurrentActivity.getSupportFragmentManager().beginTransaction().remove(mFragmentHolder).commit();
    }

    public NinepinsAppController setCurrentActivity(AppCompatActivity activity){
        mCurrentActivity = activity;
        return this;
    }


}
