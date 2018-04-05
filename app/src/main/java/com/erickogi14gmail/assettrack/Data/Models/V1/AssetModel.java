package com.erickogi14gmail.assettrack.Data.Models.V1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class AssetModel implements Parcelable {
    private String asset_code;
    private String asset_image;
    private String asset_name;

    private String warranty;
    private String warranty_duration;
    private String model;
    private String serial;

    private String contract;
    private String asset_status;

    private String manufacturer;
    private String yr_of_manufacture;
    private String nextservicedate;
    private String contanct_person;
    private String customer_id;
    private String contact_person_position;
    private String department;
    private String roomsizestatus;
    private String room_meets_specification;
    private String engineer_id;
    private String trainees;
    private String trainee_position;
    private String engineer_remarks;
    private String installation_date;
    private String receiver_name;
    private String receiver_designation;
    private String receiver_comments;


    private String accessories;
    private ArrayList<AccessoriesModel> accessoriesModels;


    public String getAsset_code() {
        return asset_code;
    }

    public void setAsset_code(String asset_code) {
        this.asset_code = asset_code;
    }

    public String getAsset_image() {
        return asset_image;
    }

    public void setAsset_image(String asset_image) {
        this.asset_image = asset_image;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getWarranty_duration() {
        return warranty_duration;
    }

    public void setWarranty_duration(String warranty_duration) {
        this.warranty_duration = warranty_duration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getAsset_status() {
        return asset_status;
    }

    public void setAsset_status(String asset_status) {
        this.asset_status = asset_status;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYr_of_manufacture() {
        return yr_of_manufacture;
    }

    public void setYr_of_manufacture(String yr_of_manufacture) {
        this.yr_of_manufacture = yr_of_manufacture;
    }

    public String getNextservicedate() {
        return nextservicedate;
    }

    public void setNextservicedate(String nextservicedate) {
        this.nextservicedate = nextservicedate;
    }

    public String getContanct_person() {
        return contanct_person;
    }

    public void setContanct_person(String contanct_person) {
        this.contanct_person = contanct_person;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getContact_person_position() {
        return contact_person_position;
    }

    public void setContact_person_position(String contact_person_position) {
        this.contact_person_position = contact_person_position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoomsizestatus() {
        return roomsizestatus;
    }

    public void setRoomsizestatus(String roomsizestatus) {
        this.roomsizestatus = roomsizestatus;
    }

    public String getRoom_meets_specification() {
        return room_meets_specification;
    }

    public void setRoom_meets_specification(String room_meets_specification) {
        this.room_meets_specification = room_meets_specification;
    }

    public String getEngineer_id() {
        return engineer_id;
    }

    public void setEngineer_id(String engineer_id) {
        this.engineer_id = engineer_id;
    }

    public String getTrainees() {
        return trainees;
    }

    public void setTrainees(String trainees) {
        this.trainees = trainees;
    }

    public String getTrainee_position() {
        return trainee_position;
    }

    public void setTrainee_position(String trainee_position) {
        this.trainee_position = trainee_position;
    }

    public String getEngineer_remarks() {
        return engineer_remarks;
    }

    public void setEngineer_remarks(String engineer_remarks) {
        this.engineer_remarks = engineer_remarks;
    }

    public String getInstallation_date() {
        return installation_date;
    }

    public void setInstallation_date(String installation_date) {
        this.installation_date = installation_date;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_designation() {
        return receiver_designation;
    }

    public void setReceiver_designation(String receiver_designation) {
        this.receiver_designation = receiver_designation;
    }

    public String getReceiver_comments() {
        return receiver_comments;
    }

    public void setReceiver_comments(String receiver_comments) {
        this.receiver_comments = receiver_comments;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public ArrayList<AccessoriesModel> getAccessoriesModels() {
        return accessoriesModels;
    }

    public void setAccessoriesModels(ArrayList<AccessoriesModel> accessoriesModels) {
        this.accessoriesModels = accessoriesModels;
    }

    public static Creator<AssetModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.asset_code);
        dest.writeString(this.asset_image);
        dest.writeString(this.asset_name);
        dest.writeString(this.warranty);
        dest.writeString(this.warranty_duration);
        dest.writeString(this.model);
        dest.writeString(this.serial);
        dest.writeString(this.contract);
        dest.writeString(this.asset_status);
        dest.writeString(this.manufacturer);
        dest.writeString(this.yr_of_manufacture);
        dest.writeString(this.nextservicedate);
        dest.writeString(this.contanct_person);
        dest.writeString(this.customer_id);
        dest.writeString(this.contact_person_position);
        dest.writeString(this.department);
        dest.writeString(this.roomsizestatus);
        dest.writeString(this.room_meets_specification);
        dest.writeString(this.engineer_id);
        dest.writeString(this.trainees);
        dest.writeString(this.trainee_position);
        dest.writeString(this.engineer_remarks);
        dest.writeString(this.installation_date);
        dest.writeString(this.receiver_name);
        dest.writeString(this.receiver_designation);
        dest.writeString(this.receiver_comments);
        dest.writeString(this.accessories);
        dest.writeList(this.accessoriesModels);
    }

    public AssetModel() {
    }

    protected AssetModel(Parcel in) {
        this.asset_code = in.readString();
        this.asset_image = in.readString();
        this.asset_name = in.readString();
        this.warranty = in.readString();
        this.warranty_duration = in.readString();
        this.model = in.readString();
        this.serial = in.readString();
        this.contract = in.readString();
        this.asset_status = in.readString();
        this.manufacturer = in.readString();
        this.yr_of_manufacture = in.readString();
        this.nextservicedate = in.readString();
        this.contanct_person = in.readString();
        this.customer_id = in.readString();
        this.contact_person_position = in.readString();
        this.department = in.readString();
        this.roomsizestatus = in.readString();
        this.room_meets_specification = in.readString();
        this.engineer_id = in.readString();
        this.trainees = in.readString();
        this.trainee_position = in.readString();
        this.engineer_remarks = in.readString();
        this.installation_date = in.readString();
        this.receiver_name = in.readString();
        this.receiver_designation = in.readString();
        this.receiver_comments = in.readString();
        this.accessories = in.readString();
        this.accessoriesModels = new ArrayList<AccessoriesModel>();
        in.readList(this.accessoriesModels, AccessoriesModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<AssetModel> CREATOR = new Parcelable.Creator<AssetModel>() {
        @Override
        public AssetModel createFromParcel(Parcel source) {
            return new AssetModel(source);
        }

        @Override
        public AssetModel[] newArray(int size) {
            return new AssetModel[size];
        }
    };
}
