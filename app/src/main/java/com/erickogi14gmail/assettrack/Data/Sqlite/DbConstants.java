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



    //Asset
    public static final  String ASSET_CODE="asset=code";
    public static final  String ASSET_IMAGE="asset_image";
    public static final  String ASSET_NAME="asset_name";
    public static final  String WARRANTY="warranty";
    public static final  String WARRANTY_DURATION="warranty_duration";
    public static final  String MODEL="model";
    public static final  String SERIAL="serial";
    public static final  String CONTRACT="contract";
    public static final  String ASSET_STATUS="asset_status";
    public static final  String MANUFACTURER="manufacturer";
    public static final  String YR_OF_MANUFACTURE="yr_of_manufacture";
    public static final  String NEXTSERVICEDATE="nextservicedate";
    public static final  String CONTANCT_PERSON="contanct_person";
    public static final  String CUSTOMER_ID="customer_id";
    public static final  String CONTACT_PERSON_POSITION="     contact_person_position";
    public static final  String DEPARTMENT="department";
    public static final  String ROOMSIZESTATUS="roomsizestatus";
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
    private ArrayList<AccessoriesModel> accessoriesModels;



    //Issue
    public static final  String ASSET_ID="asset_id";
    public static final  String DATE="date";
    public static final  String NEXTDUEDERVICE="nextduedervice";
    public static final  String TRAVEL_HOURS="travel_hours";
    public static final  String LABOUR_HOURS="labour_hours";
    public static final  String FAILURE_DESC="failure_desc";
    public static final  String FAILURE_SOLN="failure_soln";
    public static final  String PARTS_CHANGED="parts_changed";
    public static final  String PARTS_NEEDED="parts_needed";
    public static final  String ISSUES_STATUS="issues_status";
    public static final  String SAFETY="safety";
    public static final  String ENGINEER_COMMENT="engineer_comment";
    public static final  String CUSTOMER_COMMENT="customer_comment";
    public static final  String WORK_TICKETS="work_tickets";





}
