package com.erickogi14gmail.assettrack.Data.Sqlite;

import com.erickogi14gmail.assettrack.Data.Models.V1.AccessoriesModel;

import java.util.ArrayList;

/**
 * Created by Eric on 1/11/2018.
 */

public class DbConstants {


    public static final String KEY_ID = "key_id";
    public static final String TABLE_EXAMPLE="table_example";


    public static final String TABLE_ITEMS="assets_table";
    public static String TABLE_ISSUE="issue_table";


    public static final String  tag="tag_c";
    public static final String  type="type_c";
    public static final String image="image_c";
    public static final String  site="site_c";
    public static final String  serial="serial_c";
    public static final String  condition="condition_c";
    public static final String  installatiodate="date_c";
    public static final String  last_maintenance="last_maintenance";
    public static final String  last_maintenance_by="last_maintenance_by";

    public static final String installedby="installed_by";
    public static final String model="model";
    public static final String contract="contract";


    public static final  String asset_id="a_asset_id";

    public static final  String message="amessage";
    public static final  String date="adate";
    public static final  String issue="aissue";
    public static final  String fix="afix";
    public static final  String comment="acomment";
    public static final  String person="aperson";

    public static final  String parts_used="parts_used";
    public static final  String parts_needed="parts_needed";
    public static final  String next_service="next_service";
    public static final String cusomerremarks = "customer_remarks";
    public static final String engineersremarks = "engineers_remarks";
    public static final String travelhours = "travelhours";
    public static final String labourhours = "labourhours";



    //Asset
    public static final String ASSET_CODE = "assetcode";
    public static final  String ASSET_IMAGE="asset_image";
    public static final  String ASSET_NAME="asset_name";
    public static final  String WARRANTY="warranty";
    public static final  String WARRANTY_DURATION="warranty_duration";
    public static final  String MODEL="model";
    public static final  String SERIAL="serial";
    public static final  String CONTRACT="contract";
    public static final  String ASSET_STATUS="asset_status";
    public static final String ASSET_STATUS_ID = "asset_status_id";
    public static final  String MANUFACTURER="manufacturer";
    public static final  String YR_OF_MANUFACTURE="yr_of_manufacture";
    public static final  String NEXTSERVICEDATE="nextservicedate";
    public static final  String CONTANCT_PERSON="contanct_person";
    public static final  String CUSTOMER_ID="customer_id";
    public static final String CONTACT_PERSON_POSITION = "contact_person_position";
    public static final  String DEPARTMENT="department";
    public static final  String ROOMSIZESTATUS="roomsizestatus";
    public static final String ROOM_EXPLANATION = "room_explanation";
    public static final  String ROOM_MEETS_SPECIFICATION="room_meets_specification";
    public static final  String ENGINEER_ID="engineer_id";
    public static final  String TRAINEES="trainees";
    public static final  String TRAINEE_POSITION="trainee_position";
    public static final  String ENGINEER_REMARKS="engineer_remarks";
    public static final  String INSTALLATION_DATE="installation_date";
    public static final  String RECIEVERS_NAME="receiver_name";
    public static final  String RECEIVER_DESIGNATION="receiver_designation";
    public static final  String RECEIVER_COMMENTS="receiver_comments";
    public static final  String ACCESSORIES="accessories";
    public static final String TABLE_ITEMS_V1 = "assetsv1";
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_STATUS = "statuses";
    public static final ArrayList<AccessoriesModel> accessoriesModels = null;


    //    private int KEYID;
//    public static final String issuse_asset_id;
//    private String asset_code;
//    private String asset_name;
//
//    private String date;
//    private String nextduedervice;
//    private String travel_hours;
//    private String labour_hours;
//    private String failure_desc;
//    private String failure_soln;
//    private String parts;
//    private String issue_status;
//    private String safety;
//    private String engineer_comment;
//    private String engineer_id;
//    private String customer_comment;
//    private String customer_id;
//    private String customer_name;
//    private String work_ticket;
//    private int status;
    //Issue
    public static final  String ASSET_ID="asset_id";
    // public static final  String ASSET_CODE="asset_id";
    public static final  String DATE="date";
    public static final  String NEXTDUEDERVICE="nextduedervice";
    public static final  String TRAVEL_HOURS="travel_hours";
    public static final  String LABOUR_HOURS="labour_hours";
    public static final  String FAILURE_DESC="failure_desc";
    public static final  String FAILURE_SOLN="failure_soln";
    public static final  String PARTS_CHANGED="parts_changed";
    public static final String PARTS = "parts";
    public static final String ISSUE_STATUS = "issue_status";
    public static final  String SAFETY="safety";
    public static final  String ENGINEER_COMMENT="engineer_comment";
    public static final  String CUSTOMER_COMMENT="customer_comment";
    public static final String WORK_TICKET = "work_ticket";


    public static final String issuse_asset_id = "assetid";
    public static final String issuse_asset_code = "assetcode";
    public static final String issuse_asset_name = "assetname";

    public static final String issuse_date = "issuedate";
    public static final String issuse_nextduedervice = "nextdue";
    public static final String issuse_travel_hours = "travelhours";
    public static final String issuse_labour_hours = "labourhours";
    public static final String issuse_failure_desc = "failuredesc";
    public static final String issuse_failure_soln = "failuresoln";
    public static final String issuse_parts = "issueparts";
    public static final String issuse_issue_status = "issuestatus";
    public static final String issuse_safety = "safeter";
    public static final String issuse_engineer_comment = "engineercomment";
    public static final String issuse_engineer_id = "engid";
    public static final String issuse_engineer_name = "engname";
    public static final String issuse_customer_comment = "custcomment";
    public static final String issuse_customer_id = "custid";
    public static final String issuse_customer_name = "customername";
    public static final String issuse_work_ticket = "workticket";
    public static final String issue_status = "isstatus";
    //ENGINEER
    public static final String id = "eng_id";
    public static final String TABLE_ENG_V1 = "table_eng_v1";
    public static final String name = "eng_name";
    public static final String email = "eng_email";
    public static final String phone = "eng_phone";
    public static final String first_name = "eng_f_name";
    public static final String last_name = "eng_l_name";
    public static final String speciality = "eng_speciality";
    public static final String location = "eng_location";
    public static final String engagement = "eng_engagement";
    public static final String national_id = "eng_national_id";
    public static final String current_work_card_id = "eng_work_card";
    public static final String current_asset_id = "eng_current_asset_id";
    public static final String current_issue = "eng_current_issue";
    public static final String current_location = "eng_current_location";


    //CLIENTS
    public static final String cust_id = "customer_id";
    public static final String cust_name = "customer_name";
    public static final String cust_email = "customer_email";
    public static final String cust_address = "customer_address";
    public static final String cust_telephone = "customer_telephone";
    public static final String cust_physical_address = "customer_physical";

    //STATUS
    public static final String status = "status_name";
    public static final String status_id = "status_id";
    public static final String status_color = "status_color";






}
