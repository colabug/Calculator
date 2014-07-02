package com.colabug.calc;

import android.support.v4.app.Fragment;

/**
 * Base fragment
 *
 * @since 1.0
 */
public class BaseFragment extends Fragment
{
    public void postToBus( BaseViewEvent event )
    {
        getApp().getBus().post( event );
    }

    protected CalculatorApplication getApp()
    {
        return (CalculatorApplication) getActivity().getApplication();
    }
}
