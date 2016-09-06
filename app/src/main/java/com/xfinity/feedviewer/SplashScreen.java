package com.xfinity.feedviewer;

/**
 * Created by kathires on 8/30/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.xfinity.feedviewer.util.FeedFetcher;
import com.xfinity.feedviewer.vo.FeedListVO;

import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FeedFetcher feedFetcher = new FeedFetcher(new FeedFetcher.AsyncResponse() {
            @Override
            public void processFinish(String result) {

                try {



                    FeedListVO feedListVO = FeedListVO.getFeedListVO( result );
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();

                }catch( Exception e ){

                }

            }
        });
        feedFetcher.execute( BuildConfig.FEED_URL );

    }

}