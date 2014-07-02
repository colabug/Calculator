package com.colabug.calc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class CalculatorActivity extends FragmentActivity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.main );
    }
}
