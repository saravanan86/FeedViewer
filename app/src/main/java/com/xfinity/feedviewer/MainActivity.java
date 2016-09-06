package com.xfinity.feedviewer;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SingleFragmentActivity implements FeedListFragment.CallBacks {

    private FragmentManager fm;
    @Override
    protected int getLayoutId() {

        return R.layout.activity_master;

    }

    @Override
    public void onItemSelected(int position) {


        if (findViewById(R.id.fragmentdetailContainer) == null) {

            Intent intent = new Intent( MainActivity.this, FeedDetailActivity.class );
            intent.putExtra( FeedDetailFragment.EXTRA_FEED_POSITION, position );
            startActivity( intent );

        } else {

            fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment oldDetail = fm.findFragmentById(R.id.fragmentdetailContainer);
            Fragment newDetail = FeedDetailFragment.getNewInstance( position );
            if (oldDetail != null) {
                ft.remove(oldDetail);
            }
            ft.add(R.id.fragmentdetailContainer, newDetail);
            ft.commit();
        }

    }

    @Override
    protected Fragment createFragment() {

        //FeedDetailFragment.getNewInstance(0);
        return new FeedListFragment();//CrimeFragment.getNewInstance( (UUID) getIntent().getSerializableExtra( CrimeFragment.EXTRA_CRIME_ID ) );

    }


}
