package com.mohammedbouzaid.languagesettingsample.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import com.mohammedbouzaid.languagesettingsample.MainActivity;
import com.mohammedbouzaid.languagesettingsample.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static String LANG_PREF_KEY = "language_list";

    private SharedPreferences sp;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.settings,s);

        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Preference preference = findPreference(key);
                if (key.equals(LANG_PREF_KEY)) {
                    String langStr = sp.getString(key,"");
                    preference.setSummary(langStr);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getActivity().finish();
                    startActivity(intent);
                }
            }
        };

        sp.registerOnSharedPreferenceChangeListener(listener);
        findPreference(LANG_PREF_KEY).setSummary(sp.getString(LANG_PREF_KEY , ""));
    }

}
