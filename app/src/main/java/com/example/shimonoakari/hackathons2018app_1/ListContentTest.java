package com.example.shimonoakari.hackathons2018app_1;


public class ListContentTest  {

    private int mImageView = 0;
    private String mName = null;
    private String mScore = null;
    private String mOrder = null;

    public ListContentTest(String order, int imageView, String name, String score)
    {
        this.mImageView = imageView;
        this.mName = name;
        this.mScore = score;
        this.mOrder = order;

    };

    public String getmOrder(){
        return mOrder;
    }

    public String getmScore() {
        return mScore;
    }

    public String getmName() {
        return mName;
    }

    public int getmImageView() {
        return mImageView;
    }


}
