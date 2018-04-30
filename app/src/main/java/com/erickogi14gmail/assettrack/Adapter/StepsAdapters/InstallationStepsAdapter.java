package com.erickogi14gmail.assettrack.Adapter.StepsAdapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.erickogi14gmail.assettrack.Views.V1.InstallationSteps.InstallationStepFour;
import com.erickogi14gmail.assettrack.Views.V1.InstallationSteps.InstallationStepOne;
import com.erickogi14gmail.assettrack.Views.V1.InstallationSteps.InstallationStepThree;
import com.erickogi14gmail.assettrack.Views.V1.InstallationSteps.InstallationStepTwo;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class InstallationStepsAdapter extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";

    public InstallationStepsAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                final InstallationStepOne step1 = new InstallationStepOne();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);

                step1.setArguments(b1);
                return step1;
            case 1:
                final InstallationStepTwo step2 = new InstallationStepTwo();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);

                step2.setArguments(b2);
                return step2;
            case 2:
                final InstallationStepThree step3 = new InstallationStepThree();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);


                return step3;

            case 3:
                final InstallationStepFour step4 = new InstallationStepFour();
                Bundle b4 = new Bundle();
                b4.putInt(CURRENT_STEP_POSITION_KEY, position);


                return step4;




        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position) {
            case 0:
                return new StepViewModel.Builder(context)

                        .setTitle("Asset Details ") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Customer Details") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Location Details") //can be a CharSequence instead
                        .create();
            case 3:
                return new StepViewModel.Builder(context)
                        .setTitle("Final Step") //can be a CharSequence instead
                        .create();


        }
        return null;
    }


}
