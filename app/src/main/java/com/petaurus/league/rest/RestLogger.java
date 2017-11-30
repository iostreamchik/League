package com.petaurus.league.rest;

import android.support.annotation.NonNull;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.internal.platform.Platform.INFO;

public class RestLogger implements HttpLoggingInterceptor.Logger {

    private static RestLogger instance;

    public static RestLogger getInstance() {
        if (instance == null)
            instance = new RestLogger();
        return instance;
    }

    @Override
    public void log(@NonNull String message) {
        Platform.get().log(INFO, message, null);
    }
}
