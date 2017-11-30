package com.petaurus.league.repository.prefs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceImpl implements Preference {

    private static final String PREFERENCE_MAIN = "com.petaurus.league";

    private static final String ID_PREFS = "league.id";

    private final SharedPreferences sharedPreferences;

    public PreferenceImpl(Application application) {
        this.sharedPreferences = application.getSharedPreferences(PREFERENCE_MAIN, Context.MODE_PRIVATE);
    }

    @Override
    public long getCurrentLeagueId() {
        return sharedPreferences.getLong(ID_PREFS, 1);
    }

    @Override
    @SuppressLint("ApplySharedPref")
    public void writeCurrentLeagueId(long id) {
        sharedPreferences.edit().putLong(ID_PREFS, id).commit();
    }
}
