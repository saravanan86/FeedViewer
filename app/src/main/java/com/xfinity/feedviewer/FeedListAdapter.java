package com.xfinity.feedviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinity.feedviewer.vo.FeedDataVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
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

                callBackObject.onItemSelected( position );
                /*Intent intent = new Intent( context.getApplicationContext(), FeedDetailActivity.class );
                intent.putExtra( FeedDetailFragment.EXTRA_FEED_POSITION, position );
                context.startActivity( intent );*/

            }
        });
        /*holder.mPropertyNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context.getApplicationContext(), FeedDetailActivity.class );
                intent.putExtra( FeedDetailFragment.EXTRA_FEED_POSITION, position );
                context.startActivity( intent );
            }
        });*/
        //holder.mPropertyValueText.setText( mFeedValues.get(position).getDescription() );
        //holder.mImageView.setImageURI( Uri.parse( mFeedValues.get( position ).getImageUrl() ) );
        //Uri url = Uri.parse( mFeedValues.get( position ).getImageUrl() );
        /*try{

            URL url = new URL( mFeedValues.get( position ).getImageUrl() );
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.mImageView.setImageBitmap(bmp);

        }catch (Exception e ){

        }*/
        /*try {
            if (!mFeedValues.get(position).getImageUrl().equals(""))
                Picasso.with(context).load(mFeedValues.get(position).getImageUrl()).resize(150, 150).centerCrop().into(holder.mImageView);
            else
                Picasso.with(context).load(R.drawable.xfinity).resize(150, 150).centerCrop().into(holder.mImageView);
                //Log.d("FeedListAdapter","No IMAGE for Position: "+position);
        }catch (Exception e){
            Log.d("FeedListAdapter",e.getMessage());
        }*/

        //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

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

            /*mPropertyNameText = (TextView) itemView.findViewById( R.id.propertyName );
            mPropertyValueText = (TextView) itemView.findViewById( R.id.propertyValue );
            mImageView = (ImageView) itemView.findViewById( R.id.image );*/
            mPropertyNameText = (TextView) itemView.findViewById( R.id.cardTitle );
            cardView = (CardView) itemView.findViewById( R.id.feedItems );


        }

    }

}