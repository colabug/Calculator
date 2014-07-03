package com.colabug.calc.fragments;

import android.support.v4.app.Fragment;
import com.colabug.calc.CalculatorApplication;
import com.colabug.calc.events.BaseEvent;

/**
 * Base fragment
 *
 * @since 1.0
 */
public class BaseFragment extends Fragment
{
    public void postToBus( BaseEvent event )
    {
        getApp().getBus().post( event );
    }

    protected CalculatorApplication getApp()
    {
        return (CalculatorApplication) getActivity().getApplication();
    }
}
