package de.daniel_bachmann.ninepinsfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;

import de.daniel_bachmann.ninepinsfun.controller.NinepinsAppController;
import de.daniel_bachmann.ninepinsfun.controller.NinepinsPlayerAdapter;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private NinepinsAppController mAppController;
    private ArrayList<NinepinsPlayer> mPlayersList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NinepinsPlayerAdapter mAdapter;

    private ArrayList<NinepinsPlayer> mPlayerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppController = NinepinsAppController.getController(this);
        mAppController.initialize();

        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }*/
}
