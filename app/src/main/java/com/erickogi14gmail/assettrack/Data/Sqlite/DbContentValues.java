package com.erickogi14gmail.assettrack.Data.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Models.Issues;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.Models.V1.CustomerModel;
import com.erickogi14gmail.assettrack.Data.Models.V1.EngineerModel;
import com.erickogi14gmail.assettrack.Data.Models.V1.StatusModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Eric on 1/17/2018.
 */

public class DbContentValues {

    public static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    public static String toJson(Object jsonObject) {
        return new Gson().toJson(jsonObject);
    }

    public ArrayList<AssetModel> getAssetsv1(Cursor cursor) {
        ArrayList<AssetModel> assets = new ArrayList<>();

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                AssetModel assets1 = new AssetModel();
                // assets1.set(cursor.getString(cursor.getColumnIndex(DbConstants.condition)));

                assets1.setKEYID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));
                assets1.setAsset_code(cursor.getString(cursor.getColumnIndex(DbConstants.ASSET_CODE)));
                assets1.setAsset_image(cursor.getString(cursor.getColumnIndex(DbConstants.ASSET_IMAGE)));
                assets1.setAsset_name(cursor.getString(cursor.getColumnIndex(DbConstants.ASSET_NAME)));
                assets1.setWarranty(cursor.getString(cursor.getColumnIndex(DbConstants.WARRANTY)));
                assets1.setWarranty_duration(cursor.getString(cursor.getColumnIndex(DbConstants.WARRANTY_DURATION)));
                assets1.setModel(cursor.getString(cursor.getColumnIndex(DbConstants.MODEL)));
                assets1.setSerial(cursor.getString(cursor.getColumnIndex(DbConstants.SERIAL)));
                assets1.setContract(cursor.getString(cursor.getColumnIndex(DbConstants.CONTRACT)));
                assets1.setAsset_status(cursor.getString(cursor.getColumnIndex(DbConstants.ASSET_STATUS)));
                assets1.setAsset_status_id(cursor.getString(cursor.getColumnIndex(DbConstants.ASSET_STATUS_ID)));
                assets1.setManufacturer(cursor.getString(cursor.getColumnIndex(DbConstants.MANUFACTURER)));
                assets1.setYr_of_manufacture(cursor.getString(cursor.getColumnIndex(DbConstants.YR_OF_MANUFACTURE)));
                assets1.setNextservicedate(cursor.getString(cursor.getColumnIndex(DbConstants.NEXTSERVICEDATE)));
                assets1.setContanct_person(cursor.getString(cursor.getColumnIndex(DbConstants.CONTANCT_PERSON)));
                assets1.setCustomer_id(cursor.getString(cursor.getColumnIndex(DbConstants.CUSTOMER_ID)));
                assets1.setContact_person_position(cursor.getString(cursor.getColumnIndex(DbConstants.CONTACT_PERSON_POSITION)));
                assets1.setDepartment(cursor.getString(cursor.getColumnIndex(DbConstants.DEPARTMENT)));
                assets1.setRoomsizestatus(cursor.getString(cursor.getColumnIndex(DbConstants.ROOMSIZESTATUS)));
                // assets1.setCondition(cursor.getString(cursor.getColumnIndex(ut(DbConstants.OOM_EXPLANATION = "room_explanation";
                assets1.setRoom_meets_specification(cursor.getString(cursor.getColumnIndex(DbConstants.ROOM_MEETS_SPECIFICATION)));
                assets1.setEngineer_id(cursor.getString(cursor.getColumnIndex(DbConstants.ENGINEER_ID)));
                assets1.setTrainees(cursor.getString(cursor.getColumnIndex(DbConstants.TRAINEES)));
                //assets1.setCondition(cursor.getString(cursor.getColumnIndex(t(DbConstants.TRAINEE_POSITION="trainee_position";
                assets1.setEngineer_remarks(cursor.getString(cursor.getColumnIndex(DbConstants.ENGINEER_REMARKS)));
                assets1.setInstallation_date(cursor.getString(cursor.getColumnIndex(DbConstants.INSTALLATION_DATE)));
                assets1.setReceiver_name(cursor.getString(cursor.getColumnIndex(DbConstants.RECIEVERS_NAME)));
                assets1.setCustomer_name(cursor.getString(cursor.getColumnIndex(DbConstants.RECIEVERS_NAME)));
                assets1.setReceiver_designation(cursor.getString(cursor.getColumnIndex(DbConstants.RECEIVER_DESIGNATION)));
                assets1.setReceiver_comments(cursor.getString(cursor.getColumnIndex(DbConstants.RECEIVER_COMMENTS)));
                assets1.setAccessories(cursor.getString(cursor.getColumnIndex(DbConstants.ACCESSORIES)));
                if (assets1.getAccessories() != null) {
                    assets1.setAccessoriesModels((ArrayList) fromJson(assets1.getAccessories(), new TypeToken<ArrayList<String>>() {
                    }.getType()));
                }

                assets.add(assets1);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return assets;
    }

    public interface MyInterfaceTitles {

        void onComplete(int titleid);
    }

    public ArrayList<Assets> getAssets(Cursor cursor){
        ArrayList<Assets> assets=new ArrayList<>();

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                Assets assets1=new Assets();
                assets1.setCondition(cursor.getString(cursor.getColumnIndex(DbConstants.condition)));
                assets1.setDate(cursor.getString(cursor.getColumnIndex(DbConstants.installatiodate)));
                assets1.setImage(cursor.getString(cursor.getColumnIndex(DbConstants.image)));
                // assets1.setImage(cursor.getString(cursor.getColumnIndex(DbConstants.image)));

                assets1.setKEY_ID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));
                assets1.setLast_maintenance(cursor.getString(cursor.getColumnIndex(DbConstants.last_maintenance)));
                assets1.setLast_maintenance_by(cursor.getString(cursor.getColumnIndex(DbConstants.last_maintenance_by)));
                assets1.setSerial(cursor.getString(cursor.getColumnIndex(DbConstants.serial)));
                assets1.setSite(cursor.getString(cursor.getColumnIndex(DbConstants.site)));
                assets1.setTag(cursor.getString(cursor.getColumnIndex(DbConstants.tag)));
                assets1.setType(cursor.getString(cursor.getColumnIndex(DbConstants.type)));

                assets1.setModel(cursor.getString(cursor.getColumnIndex(DbConstants.model)));
                assets1.setInstalledby(cursor.getString(cursor.getColumnIndex(DbConstants.installedby)));
                assets1.setContract(cursor.getString(cursor.getColumnIndex(DbConstants.contract)));

                assets.add(assets1);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return assets;
    }

    public ArrayList<Issues> getIssues(Cursor cursor){
        ArrayList<Issues> issuess=new ArrayList<>();
        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                Issues issues=new Issues();
                issues.setAsset_id(cursor.getString(cursor.getColumnIndex(DbConstants.KEY_ID)));
                issues.setComment(cursor.getString(cursor.getColumnIndex(DbConstants.comment)));

                issues.setDate(cursor.getString(cursor.getColumnIndex(DbConstants.date)));
                issues.setFix(cursor.getString(cursor.getColumnIndex(DbConstants.fix)));
                issues.setIssue(cursor.getString(cursor.getColumnIndex(DbConstants.issue)));
                issues.setMessage(cursor.getString(cursor.getColumnIndex(DbConstants.message)));
                issues.setPerson(cursor.getString(cursor.getColumnIndex(DbConstants.person)));


                issues.setParts_needed(cursor.getString(cursor.getColumnIndex(DbConstants.parts_needed)));
                issues.setParts_used(cursor.getString(cursor.getColumnIndex(DbConstants.parts_used)));
                issues.setNext_service(cursor.getString(cursor.getColumnIndex(DbConstants.next_service)));
                issues.setCustomer_coments(cursor.getString(cursor.getColumnIndex(DbConstants.cusomerremarks)));
                issues.setEngineers_comments(cursor.getString(cursor.getColumnIndex(DbConstants.engineersremarks)));
                issues.setTravel_hours(cursor.getString(cursor.getColumnIndex(DbConstants.travelhours)));
                issues.setLabour_hour(cursor.getString(cursor.getColumnIndex(DbConstants.labourhours)));

                issuess.add(issues);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return issuess;
    }

    public ArrayList<EngineerModel> getEngineer(Cursor cursor) {
        ArrayList<EngineerModel> engineerModels = new ArrayList<>();
        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                EngineerModel engineerModel = new EngineerModel();
                engineerModel.setKEYID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));
                engineerModel.setId(cursor.getString(cursor.getColumnIndex(DbConstants.id)));
                engineerModel.setName(cursor.getString(cursor.getColumnIndex(DbConstants.name)));

                engineerModel.setFirst_name(cursor.getString(cursor.getColumnIndex(DbConstants.first_name)));
                engineerModel.setLast_name(cursor.getString(cursor.getColumnIndex(DbConstants.last_name)));
                engineerModel.setEmail(cursor.getString(cursor.getColumnIndex(DbConstants.email)));
                engineerModel.setPhone(cursor.getString(cursor.getColumnIndex(DbConstants.phone)));
                engineerModel.setNational_id(cursor.getString(cursor.getColumnIndex(DbConstants.national_id)));


                engineerModel.setCurrent_asset_id(cursor.getString(cursor.getColumnIndex(DbConstants.current_asset_id)));
                engineerModel.setCurrent_issue(cursor.getString(cursor.getColumnIndex(DbConstants.current_issue)));
                engineerModel.setCurrent_location(cursor.getString(cursor.getColumnIndex(DbConstants.current_location)));
                engineerModel.setCurrent_work_card_id(cursor.getString(cursor.getColumnIndex(DbConstants.current_work_card_id)));
                engineerModel.setEngagement(cursor.getString(cursor.getColumnIndex(DbConstants.engagement)));
                engineerModel.setLocation(cursor.getString(cursor.getColumnIndex(DbConstants.location)));
                engineerModel.setSpeciality(cursor.getString(cursor.getColumnIndex(DbConstants.speciality)));
                // engineerModel.set(cursor.getString(cursor.getColumnIndex(DbConstants.speciality)));
                // engineerModel.setSpeciality(cursor.getString(cursor.getColumnIndex(DbConstants.speciality)));

                engineerModels.add(engineerModel);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return engineerModels;
    }

    public ArrayList<CustomerModel> getClients(Cursor cursor) {
        ArrayList<CustomerModel> customerModels = new ArrayList<>();
        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                CustomerModel customerModel = new CustomerModel();
                customerModel.setKEYID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));

                customerModel.setId(cursor.getString(cursor.getColumnIndex(DbConstants.cust_id)));
                customerModel.setName(cursor.getString(cursor.getColumnIndex(DbConstants.cust_name)));

                customerModel.setPhysical_address(cursor.getString(cursor.getColumnIndex(DbConstants.cust_physical_address)));
                // customerModel.set(cursor.getString(cursor.getColumnIndex(DbConstants.cust_email)));
                customerModel.setTelephone(cursor.getString(cursor.getColumnIndex(DbConstants.cust_telephone)));
                customerModel.setAddress(cursor.getString(cursor.getColumnIndex(DbConstants.cust_address)));

                customerModels.add(customerModel);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return customerModels;
    }

    public ArrayList<StatusModel> getStatus(Cursor cursor) {
        ArrayList<StatusModel> statusModels = new ArrayList<>();
        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                StatusModel statusModel = new StatusModel();
                statusModel.setKEYID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));

                statusModel.setId(cursor.getString(cursor.getColumnIndex(DbConstants.status_id)));
                statusModel.setStatus(cursor.getString(cursor.getColumnIndex(DbConstants.status)));

                statusModel.setColor(cursor.getString(cursor.getColumnIndex(DbConstants.status_color)));

                statusModels.add(statusModel);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return statusModels;
    }

    public boolean insert(AssetModel asset, Context context) {
        if (asset != null) {
            ContentValues cv = new ContentValues();


            cv.put(DbConstants.ASSET_CODE, asset.getAsset_code());
            cv.put(DbConstants.ASSET_IMAGE, asset.getAsset_image());
            cv.put(DbConstants.ASSET_NAME, asset.getAsset_name());
            cv.put(DbConstants.WARRANTY, asset.getWarranty());
            cv.put(DbConstants.WARRANTY_DURATION, asset.getWarranty_duration());
            cv.put(DbConstants.MODEL, asset.getModel());
            cv.put(DbConstants.SERIAL, asset.getSerial());
            cv.put(DbConstants.CONTRACT, asset.getContract());
            cv.put(DbConstants.ASSET_STATUS, asset.getAsset_status());
            cv.put(DbConstants.ASSET_STATUS_ID, asset.getAsset_status_id());
            cv.put(DbConstants.MANUFACTURER, asset.getManufacturer());
            cv.put(DbConstants.YR_OF_MANUFACTURE, asset.getYr_of_manufacture());
            cv.put(DbConstants.NEXTSERVICEDATE, asset.getNextservicedate());
            cv.put(DbConstants.CONTANCT_PERSON, asset.getContanct_person());
            cv.put(DbConstants.CUSTOMER_ID, asset.getCustomer_id());
            cv.put(DbConstants.CONTACT_PERSON_POSITION, asset.getContact_person_position());
            cv.put(DbConstants.DEPARTMENT, asset.getDepartment());
            cv.put(DbConstants.ROOMSIZESTATUS, asset.getRoomsizestatus());
            // cv.put(DbConstants.OOM_EXPLANATION = "room_explanation";
            cv.put(DbConstants.ROOM_MEETS_SPECIFICATION, asset.getRoom_meets_specification());
            cv.put(DbConstants.ENGINEER_ID, asset.getEngineer_id());
            cv.put(DbConstants.TRAINEES, asset.getTrainees());
            //cv.put(DbConstants.TRAINEE_POSITION="trainee_position";
            cv.put(DbConstants.ENGINEER_REMARKS, asset.getEngineer_remarks());
            cv.put(DbConstants.INSTALLATION_DATE, asset.getInstallation_date());
            cv.put(DbConstants.RECIEVERS_NAME, asset.getCustomer_name());
            //cv.put(DbConstants.RECEIVER_DESIGNATION, assets.);
            cv.put(DbConstants.RECEIVER_COMMENTS, asset.getReceiver_comments());
            cv.put(DbConstants.ACCESSORIES, asset.getAccessories());


            return new DbOperations(context).insert(DbConstants.TABLE_ITEMS_V1, cv);

        } else {
            return false;
        }

    }

    public boolean insert(EngineerModel eng, Context context) {
        if (eng != null) {
            ContentValues cv = new ContentValues();


            cv.put(DbConstants.id, eng.getId());
            cv.put(DbConstants.name, eng.getName());
            cv.put(DbConstants.email, eng.getEmail());
            cv.put(DbConstants.phone, eng.getPhone());
            cv.put(DbConstants.first_name, eng.getFirst_name());
            cv.put(DbConstants.last_name, eng.getLast_name());
            cv.put(DbConstants.speciality, eng.getSpeciality());
            cv.put(DbConstants.location, eng.getLocation());
            cv.put(DbConstants.engagement, eng.getEngagement());
            cv.put(DbConstants.national_id, eng.getNational_id());
            cv.put(DbConstants.current_work_card_id, eng.getCurrent_work_card_id());
            cv.put(DbConstants.current_asset_id, eng.getCurrent_asset_id());
            cv.put(DbConstants.current_issue, eng.getCurrent_issue());
            cv.put(DbConstants.current_location, eng.getCurrent_location());


            return new DbOperations(context).insert(DbConstants.TABLE_ENG_V1, cv);

        } else {
            return false;
        }

    }

    public boolean insert(CustomerModel cust, Context context) {
        if (cust != null) {
            ContentValues cv = new ContentValues();

            cv.put(DbConstants.cust_id, cust.getId());
            cv.put(DbConstants.cust_name, cust.getName());
            cv.put(DbConstants.cust_email, cust.getAddress());
            cv.put(DbConstants.cust_address, cust.getAddress());
            cv.put(DbConstants.cust_telephone, cust.getTelephone());
            //cv.put(DbConstants.cust_telephone, cust.getModel());
            cv.put(DbConstants.cust_physical_address, cust.getPhysical_address());


            return new DbOperations(context).insert(DbConstants.TABLE_CLIENT, cv);

        } else {
            return false;
        }

    }

    public boolean insert(StatusModel status, Context context) {
        if (status != null) {
            ContentValues cv = new ContentValues();

            cv.put(DbConstants.status_id, status.getId());
            cv.put(DbConstants.status, status.getStatus());
            cv.put(DbConstants.status_color, status.getColor());


            return new DbOperations(context).insert(DbConstants.TABLE_STATUS, cv);

        } else {
            return false;
        }

    }

    //    ArrayList<String> playersList= (ArrayList<String>) fromJson(playersString,
//            new TypeToken<ArrayList<String>>() {
//            }.getType());
    public interface MyInterface {
        void onComplete(boolean result);


    }




}
