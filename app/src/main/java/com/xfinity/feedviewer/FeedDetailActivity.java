package com.xfinity.feedviewer;


import android.support.v4.app.Fragment;

/**
 * Created by kathires on 9/1/16.
 */
public class FeedDetailActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return FeedDetailFragment.getNewInstance( (int) getIntent().getSerializableExtra( FeedDetailFragment.EXTRA_FEED_POSITION ) );

    }
}
