package com.imkiran.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by imkiran on 30/12/17.
 */

public class Steps implements Parcelable{
    private String id;

    private String shortDescription;

    private String description;

    private String videoURL;

    private String thumbnailURL;


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getShortDescription ()
    {
        return shortDescription;
    }

    public void setShortDescription (String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getVideoURL ()
    {
        return videoURL;
    }

    public void setVideoURL (String videoURL)
    {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL ()
    {
        return thumbnailURL;
    }

    public void setThumbnailURL (String thumbnailURL)
    {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", shortDescription = "+shortDescription+", description = "+description+", videoURL = "+videoURL+", thumbnailURL = "+thumbnailURL+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Steps(Parcel in) {
        id = in.readString();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnailURL);
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        public Steps createFromParcel(Parcel source) {
            return new Steps(source);
        }

        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
