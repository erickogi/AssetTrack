package com.erickogi14gmail.assettrack.Adapter.TimeLine;

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
    private String mDate;
    private OrderStatus mStatus;
    private String issue;
    private String fix;
    private String comment;
    private String person;
    private String labour_hours;
    private String travel_hours;
    private String engcomments;
    private String custcomments;




    public TimeLineModel() {
    }

    public TimeLineModel(String mMessage, String mDate, OrderStatus mStatus) {
        this.mMessage = mMessage;
        this.mDate = mDate;
        this.mStatus = mStatus;
    }

    protected TimeLineModel(Parcel in) {
        this.mMessage = in.readString();
        this.mDate = in.readString();
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
        this.issue=in.readString();
        this.fix=in.readString();
        this.comment=in.readString();
        this.person=in.readString();
        this.labour_hours = in.readString();
        this.travel_hours = in.readString();
        this.engcomments = in.readString();
        this.custcomments = in.readString();

    }


    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getMessage() {
        return mMessage;
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

    public String getLabour_hours() {
        return labour_hours;
    }

    public void setLabour_hours(String labour_hours) {
        this.labour_hours = labour_hours;
    }

    public String getTravel_hours() {
        return travel_hours;
    }

    public void setTravel_hours(String travel_hours) {
        this.travel_hours = travel_hours;
    }

    public String getEngcomments() {
        return engcomments;
    }

    public void setEngcomments(String engcomments) {
        this.engcomments = engcomments;
    }

    public String getCustcomments() {
        return custcomments;
    }

    public void setCustcomments(String custcomments) {
        this.custcomments = custcomments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mDate);
        dest.writeString(this.fix);
        dest.writeString(this.issue);
        dest.writeString(this.person);
        dest.writeString(this.comment);
        //dest.writeString(this.mDate);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
        dest.writeString(this.labour_hours);
        dest.writeString(this.travel_hours);
        dest.writeString(this.engcomments);
        dest.writeString(this.custcomments);
    }
}
