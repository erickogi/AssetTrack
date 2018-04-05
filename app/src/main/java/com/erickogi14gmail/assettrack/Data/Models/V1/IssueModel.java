package com.erickogi14gmail.assettrack.Data.Models.V1;

import android.os.Parcel;
import android.os.Parcelable;

public class IssueModel implements Parcelable {
    private String asset_id;
    private String asset_code;

    private String date;
    private String nextduedervice;
    private String travel_hours;
    private String labour_hours;
    private String failure_desc;
    private String failure_soln;
    private String parts_changed;
    private String parts_needed;
    private String issues_status;
    private String safety;
    private String engineer_comment;
    private String engineer_id;
    private String customer_comment;
    private String customer_id;
    private String work_tickets;


    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_code() {
        return asset_code;
    }

    public void setAsset_code(String asset_code) {
        this.asset_code = asset_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNextduedervice() {
        return nextduedervice;
    }

    public void setNextduedervice(String nextduedervice) {
        this.nextduedervice = nextduedervice;
    }

    public String getTravel_hours() {
        return travel_hours;
    }

    public void setTravel_hours(String travel_hours) {
        this.travel_hours = travel_hours;
    }

    public String getLabour_hours() {
        return labour_hours;
    }

    public void setLabour_hours(String labour_hours) {
        this.labour_hours = labour_hours;
    }

    public String getFailure_desc() {
        return failure_desc;
    }

    public void setFailure_desc(String failure_desc) {
        this.failure_desc = failure_desc;
    }

    public String getFailure_soln() {
        return failure_soln;
    }

    public void setFailure_soln(String failure_soln) {
        this.failure_soln = failure_soln;
    }

    public String getParts_changed() {
        return parts_changed;
    }

    public void setParts_changed(String parts_changed) {
        this.parts_changed = parts_changed;
    }

    public String getParts_needed() {
        return parts_needed;
    }

    public void setParts_needed(String parts_needed) {
        this.parts_needed = parts_needed;
    }

    public String getIssues_status() {
        return issues_status;
    }

    public void setIssues_status(String issues_status) {
        this.issues_status = issues_status;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public String getEngineer_comment() {
        return engineer_comment;
    }

    public void setEngineer_comment(String engineer_comment) {
        this.engineer_comment = engineer_comment;
    }

    public String getEngineer_id() {
        return engineer_id;
    }

    public void setEngineer_id(String engineer_id) {
        this.engineer_id = engineer_id;
    }

    public String getCustomer_comment() {
        return customer_comment;
    }

    public void setCustomer_comment(String customer_comment) {
        this.customer_comment = customer_comment;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getWork_tickets() {
        return work_tickets;
    }

    public void setWork_tickets(String work_tickets) {
        this.work_tickets = work_tickets;
    }

    public static Creator<IssueModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.asset_id);
        dest.writeString(this.asset_code);
        dest.writeString(this.date);
        dest.writeString(this.nextduedervice);
        dest.writeString(this.travel_hours);
        dest.writeString(this.labour_hours);
        dest.writeString(this.failure_desc);
        dest.writeString(this.failure_soln);
        dest.writeString(this.parts_changed);
        dest.writeString(this.parts_needed);
        dest.writeString(this.issues_status);
        dest.writeString(this.safety);
        dest.writeString(this.engineer_comment);
        dest.writeString(this.engineer_id);
        dest.writeString(this.customer_comment);
        dest.writeString(this.customer_id);
        dest.writeString(this.work_tickets);
    }

    public IssueModel() {
    }

    protected IssueModel(Parcel in) {
        this.asset_id = in.readString();
        this.asset_code = in.readString();
        this.date = in.readString();
        this.nextduedervice = in.readString();
        this.travel_hours = in.readString();
        this.labour_hours = in.readString();
        this.failure_desc = in.readString();
        this.failure_soln = in.readString();
        this.parts_changed = in.readString();
        this.parts_needed = in.readString();
        this.issues_status = in.readString();
        this.safety = in.readString();
        this.engineer_comment = in.readString();
        this.engineer_id = in.readString();
        this.customer_comment = in.readString();
        this.customer_id = in.readString();
        this.work_tickets = in.readString();
    }

    public static final Parcelable.Creator<IssueModel> CREATOR = new Parcelable.Creator<IssueModel>() {
        @Override
        public IssueModel createFromParcel(Parcel source) {
            return new IssueModel(source);
        }

        @Override
        public IssueModel[] newArray(int size) {
            return new IssueModel[size];
        }
    };
}
