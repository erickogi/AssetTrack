package com.erickogi14gmail.assettrack.Data.Models.V1;

import android.os.Parcel;
import android.os.Parcelable;

public class AccessoriesModel implements Parcelable {
    private String id;
    private String asset_id;
    private String name;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public AccessoriesModel() {
    }

    protected AccessoriesModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<AccessoriesModel> CREATOR = new Parcelable.Creator<AccessoriesModel>() {
        @Override
        public AccessoriesModel createFromParcel(Parcel source) {
            return new AccessoriesModel(source);
        }

        @Override
        public AccessoriesModel[] newArray(int size) {
            return new AccessoriesModel[size];
        }
    };
}
