package com.erickogi14gmail.assettrack.Views.V1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.erickogi14gmail.assettrack.Adapter.StepsAdapters.InstallationStepsAdapter;
import com.erickogi14gmail.assettrack.R;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class Installation extends AppCompatActivity implements StepperLayout.StepperListener  {
    private StepperLayout mStepperLayout;
    private InstallationStepsAdapter mStepperAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_installation);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);



        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperAdapter = new InstallationStepsAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(this);
        mStepperLayout.setOffscreenPageLimit(4);
    }

    @Override
    public void onCompleted(View completeButton) {

        finish();
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}
