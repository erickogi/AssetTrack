package com.erickogi14gmail.assettrack.Data.Models;

import java.io.Serializable;

/**
 * Created by Eric on 3/2/2018.
 */

public class Assets implements Serializable{
    private int KEY_ID;
    private String image;
    private Integer imager;
    private String tag;
    private String type;
    private String site;
    private String serial;
    private String condition;
    private String date;
    private String installedby;
    private String model;
    private String contract;
    private String last_maintenance;
    private String last_maintenance_by;

    public Integer getImager() {
        return imager;
    }

    public void setImager(Integer imager) {
        this.imager = imager;
    }

    public String getInstalledby() {
        return installedby;
    }

    public void setInstalledby(String installedby) {
        this.installedby = installedby;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public int getKEY_ID() {
        return KEY_ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setKEY_ID(int KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLast_maintenance() {
        return last_maintenance;
    }

    public void setLast_maintenance(String last_maintenance) {
        this.last_maintenance = last_maintenance;
    }

    public String getLast_maintenance_by() {
        return last_maintenance_by;
    }

    public void setLast_maintenance_by(String last_maintenance_by) {
        this.last_maintenance_by = last_maintenance_by;
    }
}
