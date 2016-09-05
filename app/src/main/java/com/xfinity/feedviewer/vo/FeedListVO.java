package com.xfinity.feedviewer.vo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kathires on 8/30/16.
 */
public class FeedListVO {

    private ArrayList<FeedDataVO> mFeedList = new ArrayList();
    private static FeedListVO feedListVO = null;

    private FeedListVO( JSONObject feedJson ){

        try{

            //JSONArray arr = new JSONArray( feedJson );
            JSONArray arr = feedJson.getJSONArray("RelatedTopics");
            for( int i=0,len=arr.length();i<len;i++ ) {

                String text = "";
                String title = "";
                String description = "";
                String imageUrl = "";
                JSONObject jObj = (JSONObject) arr.get(i);
                if (jObj.has("Text")) {
                    text = jObj.getString("Text");
                    if (text != null) {
                        title = text.indexOf(" - ") != -1 ? text.substring(0, text.indexOf(" - ")) : text;
                        description = text.indexOf(" - ") != -1 ? text.substring(text.indexOf(" - ") + 3) : text;
                    }
                }
                if (jObj.has("Icon")) {

                    JSONObject obj = (JSONObject) jObj.getJSONObject("Icon");
                    if (obj.has("URL")) {
                        imageUrl = obj.getString("URL");
                    }

                }
                mFeedList.add(new FeedDataVO(title, description, imageUrl));

            }

        }catch( Exception e ){

        }

    }

    public static FeedListVO getFeedListVO( String feedJson ){

        JSONObject jsonObject = null;
        try {

            jsonObject = new JSONObject(feedJson);

        }catch (Exception e){



        }

        if( feedListVO == null ){

            feedListVO = new FeedListVO( jsonObject );

        }

        return feedListVO;

    }

    public static FeedListVO getFeedListVO(){

        if( feedListVO == null ){

            feedListVO = new FeedListVO( null );

        }

        return feedListVO;

    }

    public ArrayList getFeedList(){

        return mFeedList;

    }

    public ArrayList getFeedList( String searchCriteria ){

        ArrayList<FeedDataVO> resultData = new ArrayList();
        int len = mFeedList.size();
        for( int i = 0; i < len; i++ ){

            if( mFeedList.get(i).getTitle().toLowerCase().indexOf( searchCriteria.toLowerCase() ) != -1 ){

                resultData.add( mFeedList.get(i) );

            }

        }
        return resultData;

    }

    public FeedDataVO getFeedData( int position ){

        return mFeedList.size() > position ? mFeedList.get( position ) : null;


    }

}
