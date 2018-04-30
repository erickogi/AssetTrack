package com.erickogi14gmail.assettrack.Data.Models.V1;

import java.io.Serializable;

public class EngineerModel implements Serializable {

    private int KEYID;
    private String id;
    private String name;
    private String email;
    private String phone;
    private String first_name;
    private String last_name;
    private String speciality;
    private String location;
    private String engagement;
    private String national_id;
    private String current_work_card_id;
    private String current_asset_id;
    private String current_issue;
    private String current_location;

    public int getKEYID() {
        return KEYID;
    }

    public void setKEYID(int KEYID) {
        this.KEYID = KEYID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getCurrent_work_card_id() {
        return current_work_card_id;
    }

    public void setCurrent_work_card_id(String current_work_card_id) {
        this.current_work_card_id = current_work_card_id;
    }

    public String getCurrent_asset_id() {
        return current_asset_id;
    }

    public void setCurrent_asset_id(String current_asset_id) {
        this.current_asset_id = current_asset_id;
    }

    public String getCurrent_issue() {
        return current_issue;
    }

    public void setCurrent_issue(String current_issue) {
        this.current_issue = current_issue;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }
}
