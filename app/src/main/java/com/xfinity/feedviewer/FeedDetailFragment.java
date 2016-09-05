package com.xfinity.feedviewer;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinity.feedviewer.vo.FeedDataVO;
import com.xfinity.feedviewer.vo.FeedListVO;

/**
 * Created by kathires on 9/2/16.
 */
public class FeedDetailFragment extends Fragment {

    public static final String EXTRA_FEED_POSITION = "feedPosition";
    private int feedPosition = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        feedPosition = getArguments().getInt( EXTRA_FEED_POSITION );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.fragment_detail_menu, menu);

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if( id == R.id.menu_item_back ){

            //this.getFragmentManager().findFragmentById(R.id.fragmentdetailContainer).getActivity().setVisible(false);
            //this.getActivity().setVisible(false);
            Intent intent = new Intent( getContext().getApplicationContext(), MainActivity.class );
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity( intent );
            getActivity().finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate( R.layout.fragment_viewer, container, false );
        ImageView image = (ImageView) view.findViewById( R.id.detailImage );
        TextView textTitle = (TextView) view.findViewById( R.id.detailTitle );
        TextView textDescription = (TextView) view.findViewById( R.id.detailDescription );
        FeedDataVO feedData = FeedListVO.getFeedListVO().getFeedData(feedPosition);
        try {
            String imageUrl = feedData.getImageUrl();
            if (!imageUrl.equals("")) {
                Picasso.with(getActivity().getApplicationContext()).load(imageUrl).resize(500, 500).into(image);
            }
            else {
                Picasso.with(getActivity().getApplicationContext()).load(R.drawable.xfinity).resize(500, 500).centerCrop().into(image);
            }
            textTitle.setText( feedData.getTitle() );
            textDescription.setText( feedData.getDescription() );
        }catch (Exception e){
            Log.d("FeedListDetailFragment",e.getMessage());
        }

        if( getResources().getBoolean(R.bool.isPhone) ) {
            getActivity().setTitle(feedData.getTitle());
        }
        return view;
    }

    public static FeedDetailFragment getNewInstance(int feedPostion ){

        FeedDetailFragment fragment = new FeedDetailFragment();
        Bundle args = new Bundle();
        args.putInt( EXTRA_FEED_POSITION, feedPostion );

        fragment.setArguments( args );

        return fragment;
    }

}
