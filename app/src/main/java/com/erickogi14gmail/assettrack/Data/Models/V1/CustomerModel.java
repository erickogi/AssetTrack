package com.erickogi14gmail.assettrack.Data.Models.V1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CustomerModel implements Parcelable,Serializable {
    private String id;
    private String name;
    private String address;
    private String telephone;
    private String physical_address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public void setPhysical_address(String physical_address) {
        this.physical_address = physical_address;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.telephone);
        dest.writeString(this.physical_address);
    }

    public CustomerModel() {
    }

    protected CustomerModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.telephone = in.readString();
        this.physical_address = in.readString();
    }

    public static final Parcelable.Creator<CustomerModel> CREATOR = new Parcelable.Creator<CustomerModel>() {
        @Override
        public CustomerModel createFromParcel(Parcel source) {
            return new CustomerModel(source);
        }

        @Override
        public CustomerModel[] newArray(int size) {
            return new CustomerModel[size];
        }
    };
}

