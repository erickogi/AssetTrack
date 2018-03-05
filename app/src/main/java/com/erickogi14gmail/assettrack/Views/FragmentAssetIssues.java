package com.erickogi14gmail.assettrack.Views;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickogi14gmail.assettrack.Adapter.TimeLine.OrderStatus;
import com.erickogi14gmail.assettrack.Adapter.TimeLine.Orientation;
import com.erickogi14gmail.assettrack.Adapter.TimeLine.TimeLineAdapter;
import com.erickogi14gmail.assettrack.Adapter.TimeLine.TimeLineModel;
import com.erickogi14gmail.assettrack.Data.Models.Issues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.Constants;
import com.erickogi14gmail.assettrack.Utills.DateTimeUtils;
import com.erickogi14gmail.assettrack.Utills.MyToast;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eric on 2/28/2018.
 */

public class FragmentAssetIssues extends Fragment implements DatePickerDialog.OnDateSetListener {

    private RecyclerView recyclerView;
    private TimeLineAdapter timeLineAdapter;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Orientation mOrientation;
    private boolean mWithLinePadding;
    private FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asset_issues,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDialog();
            }
        });
        mOrientation = Orientation.VERTICAL;
        mWithLinePadding = false;
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getLinearLayoutManager());
        recyclerView.setHasFixedSize(true);


        initData();
    }

    private void initData(){
        List<TimeLineModel> timeLineModels=getTimeline2();
        timeLineAdapter=new TimeLineAdapter(timeLineModels, mOrientation, true, new OnclickRecyclerListener() {
            @Override
            public void onClickListener(int position) {
                startDialog(timeLineModels.get(position));
            }

            @Override
            public void onLongClickListener(int position) {

            }

            @Override
            public void onClickListener(int adapterPosition, ImageView imageView) {

            }
        });
        recyclerView.setAdapter(timeLineAdapter);
    }
    private List<TimeLineModel> getTimeline2() {
        DbOperations dbOperations=new DbOperations(getContext());
        List<TimeLineModel> timeLineModels=new LinkedList<>();
        DbContentValues dbContentValues=new DbContentValues();
        ArrayList<Issues> issues=new ArrayList<>();
        Log.d("constantidd",Constants.id);
        Cursor cursor=dbOperations.select(DbConstants.TABLE_ISSUE,DbConstants.asset_id, Constants.id);

        if(cursor!=null){
            issues=dbContentValues.getIssues(cursor);
            if(issues!=null&&issues.size()>0){
                for(Issues issues1:issues){
                  TimeLineModel timeLineModel=new TimeLineModel();
                  timeLineModel.setPerson(issues1.getPerson());
                  timeLineModel.setComment(issues1.getComment());
                  timeLineModel.setFix(issues1.getFix());
                  timeLineModel.setIssue(issues1.getIssue());
                  timeLineModel.setStatus(OrderStatus.COMPLETED);
                  timeLineModel.setDate(issues1.getDate());
                  timeLineModel.semMessage(issues1.getMessage());


                  timeLineModels.add(timeLineModel);
                }
            }
        }

        return timeLineModels;
    };

    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        }
    }
    private List<TimeLineModel> getTimeline(){
        List<TimeLineModel> timeLineModels=new LinkedList<>();
        TimeLineModel t=new TimeLineModel();
        t.setDate("2011-2-12");
        t.semMessage("System installation");
        t.setStatus(OrderStatus.COMPLETED);
        t.setIssue(" None ");
        t.setFix(" None");
        t.setComment("System Installed Successfully");
        t.setPerson(" Eric Kogi (0012)");
        t.setStatus(OrderStatus.COMPLETED);


        TimeLineModel t1=new TimeLineModel();
        t1.setDate("2011-6-12");
        t1.semMessage("Routine System Maintenance");
        t1.setStatus(OrderStatus.COMPLETED);
        t1.setIssue(" None ");
        t1.setFix(" None");
        t1.setComment("System was in good condition");
        t1.setPerson(" Eric Kogi (0012)");

        TimeLineModel t2=new TimeLineModel();
        t2.setDate("2011-8-1");
        t2.semMessage("System Servicing");
        t2.setStatus(OrderStatus.COMPLETED);
        t2.setIssue(" None ");
        t2.setFix(" None");
        t2.setComment("System Was in Good condition");
        t2.setPerson(" Eric Kogi (0012)");

        TimeLineModel t3=new TimeLineModel();
        t3.setDate("2012-1-2");
        t3.semMessage("Fix on board Computer");
        t3.setStatus(OrderStatus.COMPLETED);
        t3.setIssue(" On Board Computer was over heating ");
        t3.setFix(" Replaced the heat sink");
        t3.setComment("System Now in good condition");
        t3.setPerson(" Eric Kogi (0012)");

        TimeLineModel t4=new TimeLineModel();
        t4.setDate("2012-5-8");
        t4.semMessage("Routing Maintenance");
        t4.setStatus(OrderStatus.COMPLETED);
        t4.setIssue(" None ");
        t4.setFix(" None");
        t4.setComment("System Was in good condition");
        t4.setPerson(" Eric Kogi (0012)");

        TimeLineModel t5=new TimeLineModel();
        t5.setDate("2013-7-5");
        t5.semMessage("System Servicing");
        t5.setStatus(OrderStatus.COMPLETED);
        t5.setIssue(" None ");
        t5.setFix(" None");
        t5.setComment("System Was in Good condition");
        t5.setPerson(" Eric Kogi (0012)");

        TimeLineModel t6=new TimeLineModel();
        t6.setDate("2014-2-3");
        t6.semMessage("Boot loader replacement");
        t6.setStatus(OrderStatus.COMPLETED);
        t6.setIssue(" The bootloader system had reached its service limit ");
        t6.setFix(" I replaced the bootloader ( ID 334R434) ");
        t6.setComment("System now  in Good condition");
        t6.setPerson(" Eric Kogi (0012)");

        TimeLineModel t7=new TimeLineModel();
        t7.setDate("2015-2-12");
        t7.semMessage("System Servicing");
        t7.setStatus(OrderStatus.COMPLETED);
        t2.setIssue(" None ");
        t2.setFix(" None");
        t2.setComment("System Was in Good condition");
        t2.setPerson(" Eric Kogi (0012)");

        timeLineModels.add(t7);
        timeLineModels.add(t6);
        timeLineModels.add(t5);
        timeLineModels.add(t4);
        timeLineModels.add(t3);
        timeLineModels.add(t2);
        timeLineModels.add(t1);
        timeLineModels.add(t);




        return timeLineModels;
    }

    private void startDialog(TimeLineModel timeLineModel) {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_new_issue, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("Issue Details");
        //alertDialogBuilderUserInput.setIcon(R.drawable.ic_add_black_24dp);

        EditText edtIssue,edtIssueDesc,edtFix,edtComment;
        TextView txtDate,txtBy;

        edtIssue=mView.findViewById(R.id.edt_issue_title);
        edtIssueDesc=mView.findViewById(R.id.edt_issue_desc);
        edtFix=mView.findViewById(R.id.edt_fix);
        edtComment=mView.findViewById(R.id.edt_comment);

        txtBy=mView.findViewById(R.id.txt_by);
        txtDate=mView.findViewById(R.id.txt_date);

        txtDate.setText(timeLineModel.getDate());
        txtBy.setText(timeLineModel.getPerson());
        edtComment.setText(timeLineModel.getComment());
        edtFix.setText(timeLineModel.getFix());
        edtIssue.setText(timeLineModel.getMessage());
        edtIssueDesc.setText(timeLineModel.getIssue());



        edtComment.setEnabled(false);

        edtIssueDesc.setEnabled(false);
        edtFix.setEnabled(false);
        edtIssue.setEnabled(false);








        // final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
//                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogBox, int id) {
//                        // ToDo get user input here
//
//                        dialogBox.dismiss();
//
//                    }
//                })

                .setNegativeButton("Okay",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

    }

    private void startDialog() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_new_issue, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("New  Issue");
        alertDialogBuilderUserInput.setIcon(R.drawable.ic_add_black_24dp);
        TextView txtDate,txtBy;
        txtBy=mView.findViewById(R.id.txt_by);
        txtDate=mView.findViewById(R.id.txt_date);

        txtBy.setText("Eric Kogi");
        txtDate.setText(DateTimeUtils.getToday());


        // final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here

                        // dialogBox.dismiss();

                    }
                })

                .setNegativeButton("Dismiss",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
        Button theButton = alertDialogAndroid.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new CustomListener(alertDialogAndroid));

    }
    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;


        public CustomListener(Dialog dialog) {
            this.dialog = dialog;

        }

        @Override
        public void onClick(View v) {
            EditText edtIssue,edtIssueDesc,edtFix,edtComment,edtSpareUsed,edtSpareNeeded;

            TextView txtDate,txtBy;

            edtIssue=dialog.findViewById(R.id.edt_issue_title);
            edtIssueDesc=dialog.findViewById(R.id.edt_issue_desc);
            edtFix=dialog.findViewById(R.id.edt_fix);
            edtComment=dialog.findViewById(R.id.edt_comment);
            edtSpareNeeded=dialog.findViewById(R.id.edt_spare_need);
            edtSpareUsed=dialog.findViewById(R.id.edt_spare_used);

            txtBy=dialog.findViewById(R.id.txt_by);
            txtDate=dialog.findViewById(R.id.txt_date);

            if(edtIssue.getText().toString().isEmpty()){
                edtIssue.setError("Required");
                return;
            }
//            if(edtComment.getText().toString().isEmpty()){
//                edtComment.setError("Required");
//                return;
//            }
            if(edtFix.getText().toString().isEmpty()){
                edtFix.setError("Required");
                return;
            }

            if(edtIssueDesc.getText().toString().isEmpty()){
                edtIssue.setError("Required");
                return;
            }

            if(edtSpareNeeded.getText().toString().isEmpty()){
                edtSpareNeeded.setError("Required");
                return;
            }
            if(edtSpareUsed.getText().toString().isEmpty()){
                edtSpareUsed.setError("Required");
                return;
            }

            Issues issues=new Issues();
            issues.setAsset_id(Constants.id);
            issues.setComment("");
            issues.setDate(DateTimeUtils.getToday());
            issues.setFix(edtFix.getText().toString());
            issues.setIssue(edtIssueDesc.getText().toString());
            issues.setMessage(edtIssue.getText().toString());
            issues.setPerson("Eric Kogi");
            issues.setParts_needed(edtSpareNeeded.getText().toString());
            issues.setParts_used(edtSpareUsed.getText().toString());


            dialog.dismiss();

            showDatePicker(issues);
            //contentValues.put(DbConstants.);



        }


    }








    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = (dayOfMonth + "/" + (++monthOfYear) + "/" + year);
        // txtDate.setText(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(calendar.getTime());

        this.issues.setNext_service(strDate);
        update(issues);
    }

    private  Issues issues;
    void showDatePicker(Issues issues) {

        this.issues=issues;
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(FragmentAssetIssues.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        dpd.setThemeDark(true);
        dpd.setTitle("Next Service Date");
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.showYearPickerFirst(true);
        //dpd.setMaxDate(now);//Date(now);
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);

        dpd.show(getActivity().getFragmentManager(), "DatePicker");


    }

    private void dial(){
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_new_issue);
      EditText   edtIssue=dialog.findViewById(R.id.edt_issue_title);
     TextView   txtBy=dialog.findViewById(R.id.txt_by);

     txtBy.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(edtIssue.getText().toString().isEmpty()){
                 MyToast.toast("Fill issue",getContext(),R.drawable.ic_error_outline_black_24dp,Constants.TOAST_LONG);
             }else {
                 dialog.dismiss();
             }
         }
     });


    }




    private  void  update(Issues issues){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbConstants.asset_id,issues.getAsset_id());
        contentValues.put(DbConstants.comment,issues.getComment());
        contentValues.put(DbConstants.date,issues.getDate());
        contentValues.put(DbConstants.fix,issues.getFix());
        contentValues.put(DbConstants.issue,issues.getIssue());
        contentValues.put(DbConstants.message,issues.getMessage());
        contentValues.put(DbConstants.person,issues.getPerson());
        contentValues.put(DbConstants.parts_used,issues.getParts_used());
        contentValues.put(DbConstants.parts_needed,issues.getParts_needed());

        DbOperations dbOperations=new DbOperations(getContext());

        if(dbOperations.insert(DbConstants.TABLE_ISSUE,contentValues)){

            initData();
        }else {
            MyToast.toast("Issue not inserted",getContext(),R.drawable.ic_error_outline_black_24dp,Constants.TOAST_LONG);
        }
    }
}
