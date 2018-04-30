package com.erickogi14gmail.assettrack.Data.Models.V1;

public class PartsModel {
    private int KEYID;
    private String asset_id;
    private String work_ticket;
    private String part_no;
    private String desc;
    private String qty;
    private String part_state;

    public int getKEYID() {
        return KEYID;
    }

    public void setKEYID(int KEYID) {
        this.KEYID = KEYID;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getWork_ticket() {
        return work_ticket;
    }

    public void setWork_ticket(String work_ticket) {
        this.work_ticket = work_ticket;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPart_state() {
        return part_state;
    }

    public void setPart_state(String part_state) {
        this.part_state = part_state;
    }
}
