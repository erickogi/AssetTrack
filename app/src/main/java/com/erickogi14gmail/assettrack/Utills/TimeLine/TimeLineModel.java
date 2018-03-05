package com.erickogi14gmail.assettrack.Utills.TimeLine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineModel implements Parcelable {

    public static final Creator<TimeLineModel> CREATOR = new Creator<TimeLineModel>() {
        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };
    private String mMessage;
    private String title;
    private String mDate;
    private OrderStatus mStatus;
    private String mNoOfPhotos;
    private String mCoverImage;
    private String[] mPaths;
    private int id;

//    public TimeLineModel() {
//    }

    public TimeLineModel(String mMessage, String mNoOfPhotos, String mDate, String mCoverImage, OrderStatus mStatus,
                         String title, String[] paths, int id) {
        this.mMessage = mMessage;
        this.mDate = mDate;
        this.mStatus = mStatus;
        this.mNoOfPhotos=mNoOfPhotos;
        this.mCoverImage=mCoverImage;

        this.title = title;
        this.id = id;
        this.mPaths = paths;
    }


    protected TimeLineModel(Parcel in) {
        this.mMessage = in.readString();
        this.mDate = in.readString();
        this.mNoOfPhotos=in.readString();
        this.mCoverImage=in.readString();
        this.title = in
                .readString();
        this.id = in.readInt();

        // this.mPaths=in.readStringArray(mPaths);
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
    }

    public TimeLineModel() {

    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public OrderStatus getmStatus() {
        return mStatus;
    }

    public void setmStatus(OrderStatus mStatus) {
        this.mStatus = mStatus;
    }

    public String[] getmPaths() {
        return mPaths;
    }

    public void setmPaths(String[] mPaths) {
        this.mPaths = mPaths;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmNoOfPhotos() {
        return mNoOfPhotos;
    }

    public void setmNoOfPhotos(String mNoOfPhotos) {
        this.mNoOfPhotos = mNoOfPhotos;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getmCoverImage() {
        return mCoverImage;
    }

    public void setmCoverImage(String mCoverImage) {
        this.mCoverImage = mCoverImage;
    }

    public void semMessage(String message) {
        this.mMessage = message;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public OrderStatus getStatus() {
        return mStatus;
    }

    public void setStatus(OrderStatus mStatus) {
        this.mStatus = mStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mDate);
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeStringArray(this.mPaths);
        dest.writeString(this.mCoverImage);
        dest.writeString(this.mNoOfPhotos);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
    }
}
