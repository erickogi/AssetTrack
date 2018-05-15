package com.erickogi14gmail.assettrack.Data.Models.V1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class IssueModel implements Parcelable {
    private int KEYID;
    private String asset_id;
    private String asset_code;
    private String asset_name;

    private String date;
    private String nextduedervice;
    private String travel_hours;
    private String labour_hours;
    private String failure_desc;
    private String failure_soln;
    private String parts;
    private String issue_status;
    private String safety;
    private String engineer_comment;
    private String engineer_id;
    private String engineer_name;
    private String customer_comment;
    private String customer_id;
    private String customer_name;
    private String work_ticket;
    private int status;

    private ArrayList<PartsModel> partsModels;

    protected IssueModel(Parcel in) {
        this.asset_id = in.readString();
        this.asset_code = in.readString();
        this.date = in.readString();
        this.nextduedervice = in.readString();
        this.travel_hours = in.readString();
        this.labour_hours = in.readString();
        this.failure_desc = in.readString();
        this.failure_soln = in.readString();
        this.parts = in.readString();
        this.issue_status = in.readString();
        this.safety = in.readString();
        this.engineer_comment = in.readString();
        this.engineer_id = in.readString();
        this.customer_comment = in.readString();
        this.customer_id = in.readString();
        this.work_ticket = in.readString();
        this.partsModels = new ArrayList<PartsModel>();
        in.readList(this.partsModels, PartsModel.class.getClassLoader());
    }

    public String getEngineer_name() {
        return engineer_name;
    }

    public void setEngineer_name(String engineer_name) {
        this.engineer_name = engineer_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public int getKEYID() {
        return KEYID;
    }

    public void setKEYID(int KEYID) {
        this.KEYID = KEYID;
    }

    public ArrayList<PartsModel> getPartsModels() {
        return partsModels;
    }

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

    public void setPartsModels(ArrayList<PartsModel> partsModels) {
        this.partsModels = partsModels;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getIssue_status() {
        return issue_status;
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

    public void setIssue_status(String issue_status) {
        this.issue_status = issue_status;
    }

    public String getWork_ticket() {
        return work_ticket;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public void setWork_ticket(String work_ticket) {
        this.work_ticket = work_ticket;
    }

    public IssueModel() {
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
        dest.writeString(this.parts);
        dest.writeString(this.issue_status);
        dest.writeString(this.safety);
        dest.writeString(this.engineer_comment);
        dest.writeString(this.engineer_id);
        dest.writeString(this.customer_comment);
        dest.writeString(this.customer_id);
        dest.writeString(this.work_ticket);
        dest.writeList(this.partsModels);
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
