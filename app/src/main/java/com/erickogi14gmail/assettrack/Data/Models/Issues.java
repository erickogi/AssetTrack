package com.erickogi14gmail.assettrack.Data.Models;

import java.io.Serializable;

/**
 * Created by Eric on 3/2/2018.
 */

public class Issues implements Serializable{
    private String asset_id;
    private String KEY_ID;
    private String message;
    private String date;
    private String issue;
    private String fix;
    private String comment;
    private String person;

    private String parts_used;
    private String parts_needed;
    private String next_service;

    private String customer_coments;
    private String engineers_comments;

    private String travel_hours;
    private String labour_hour;


    public String getCustomer_coments() {
        return customer_coments;
    }

    public void setCustomer_coments(String customer_coments) {
        this.customer_coments = customer_coments;
    }

    public String getEngineers_comments() {
        return engineers_comments;
    }

    public void setEngineers_comments(String engineers_comments) {
        this.engineers_comments = engineers_comments;
    }

    public String getTravel_hours() {
        return travel_hours;
    }

    public void setTravel_hours(String travel_hours) {
        this.travel_hours = travel_hours;
    }

    public String getLabour_hour() {
        return labour_hour;
    }

    public void setLabour_hour(String labour_hour) {
        this.labour_hour = labour_hour;
    }

    public String getParts_used() {
        return parts_used != null ? parts_used : "";

    }

    public void setParts_used(String parts_used) {
        this.parts_used = parts_used;
    }

    public String getParts_needed() {
        return parts_needed != null ? parts_needed : "";
    }

    public void setParts_needed(String parts_needed) {
        this.parts_needed = parts_needed;
    }

    public String getNext_service() {
        return next_service != null ? next_service : "";
    }

    public void setNext_service(String next_service) {
        this.next_service = next_service;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(String KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
