package com.colabug.calc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class CalculatorActivity extends FragmentActivity
{
    private CalculatorStateFragment calculatorStateFragment;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.main );

        addCalculatorStateFragment();
    }

    private void addCalculatorStateFragment()
    {
        calculatorStateFragment = CalculatorStateFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                                   .add( calculatorStateFragment, "state" )
                                   .commit();
    }
}
