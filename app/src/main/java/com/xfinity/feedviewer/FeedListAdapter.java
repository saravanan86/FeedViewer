package com.xfinity.feedviewer;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfinity.feedviewer.vo.FeedDataVO;
import com.xfinity.feedviewer.vo.FeedListVO;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by kathires on 8/30/16.
 */
public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedListHolder> {

    private ArrayList<FeedDataVO> mFeedValues;
    private Context context;
    private String propertyValue;
    private Hashtable<String,String> mgidValues = new Hashtable<String, String>();
    public FeedListFragment.CallBacks callBackObject;

    public FeedListAdapter(ArrayList<FeedDataVO> feedValues, FeedListFragment.CallBacks callBacks){//String feedName, String feedValue

        try {
            callBackObject = callBacks;
            mFeedValues = feedValues;

        }catch (Exception e){

        }

    }

    @Override
    public FeedListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from( context );
        FeedListAdapter.FeedListHolder viewHolder = new FeedListAdapter.FeedListHolder( inflater.inflate( R.layout.feed_items_fragment, parent, false ) );

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(FeedListHolder holder, final int position) {

        holder.mPropertyNameText.setText( mFeedValues.get(position).getTitle() );
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callBackObject.onItemSelected(FeedListVO.getFeedListVO().getFeedItemPosition(mFeedValues.get(position).getTitle()));

            }
        });
    }

    @Override
    public int getItemCount() {

        return ( mFeedValues == null ? 0 : mFeedValues.size() );

    }

    public ArrayList<FeedDataVO> getFeedValues() {
        return mFeedValues;
    }

    public static class FeedListHolder extends RecyclerView.ViewHolder {

        public TextView mPropertyNameText;
        public TextView mPropertyValueText;
        public ImageView mImageView;
        public CardView cardView;

        public FeedListHolder( View itemView ) {

            super( itemView );

            mPropertyNameText = (TextView) itemView.findViewById( R.id.cardTitle );
            cardView = (CardView) itemView.findViewById( R.id.feedItems );


        }

    }

}