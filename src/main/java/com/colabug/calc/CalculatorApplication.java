package com.colabug.calc;

import android.app.Application;
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

    @Override
    public void onCreate()
    {
        super.onCreate();
        bus = new Bus();
    }
}
