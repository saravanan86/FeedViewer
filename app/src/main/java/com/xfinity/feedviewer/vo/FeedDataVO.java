package com.xfinity.feedviewer.vo;

/**
 * Created by kathires on 8/30/16.
 */
public class FeedDataVO {

    private String mTitle;
    private String mDescription;
    private String mImageUrl;

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public FeedDataVO(String title, String description, String imageUrl ){

        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;

    }

}
