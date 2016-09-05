package com.xfinity.feedviewer;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kathires on 8/29/16.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    protected int getLayoutId(){

        return R.layout.activity_fragment;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( getLayoutId() );
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById( R.id.fragmentContainer );

        if( fragment == null ){

            fragment = createFragment();
            fragmentManager.beginTransaction().add( R.id.fragmentContainer, fragment ).commit();

        }

    }

}