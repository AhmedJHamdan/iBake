package com.example.ahmed.ibake;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.ibake.Recipes.Steps;

public class StepActivity extends AppCompatActivity {

    private static final String TAG_STEP_FRAGMENT = "stepFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        StepFragment stepFragment = (StepFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_STEP_FRAGMENT);

        if(null == stepFragment) {

            stepFragment = new StepFragment();
            stepFragment.setStep((Steps) getIntent().getSerializableExtra(StepFragment.KEY_STEP_OBJECT));

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fl_step, stepFragment, TAG_STEP_FRAGMENT);
            fragmentTransaction.commit();
        }
    }
}
