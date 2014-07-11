package com.colabug.calc;

import android.app.Application;

import com.colabug.calc.events.BaseEvent;
import com.squareup.otto.Bus;

/**
 * @since 1.0
 */
public class CalculatorApplication extends Application
{
    private static CalculatorApplication instance = new CalculatorApplication();

    private Bus bus;

    public static CalculatorApplication getInstance()
    {
        return instance;
    }

    public Bus getBus()
    {
        return bus;
    }

    public static void postToBus( BaseEvent event)
    {
        getInstance().getBus().post( event );
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance.bus = new Bus();
    }
}
