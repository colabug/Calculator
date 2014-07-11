package com.colabug.calc.fragments;

import android.support.v4.app.Fragment;

import com.colabug.calc.CalculatorApplication;
import com.colabug.calc.events.BaseEvent;
import com.squareup.otto.Bus;

/**
 * Base fragment
 *
 * @since 1.0
 */
public class BaseFragment extends Fragment
{
    public void postToBus( BaseEvent event )
    {
        CalculatorApplication.postToBus( event );
    }

    @Override
    public void onResume()
    {
        super.onResume();
        registerWithBus();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        unRegisterFromBus();
    }

    private void registerWithBus()
    {
        getBus().register( this );
    }

    private void unRegisterFromBus()
    {
        getBus().unregister( this );
    }

    protected Bus getBus()
    {
        return CalculatorApplication.getInstance().getBus();
    }
}
