package de.daniel_bachmann.ninepinsfun;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import de.daniel_bachmann.ninepinsfun.model.NinepinsDatabase;
import de.daniel_bachmann.ninepinsfun.model.NinepinsPlayer;
import de.daniel_bachmann.ninepinsfun.view.ThrowInputFragment;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements ThrowInputFragment.OnFragmentInputListener {
    @Override
    public void onFragmentInput(long player, long points) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NinepinsDatabase.initialize(this);

        testCode();

        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    private void testCode(){

        int i = 1;//this is mainly to have a breakpoint for the debugger

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}
