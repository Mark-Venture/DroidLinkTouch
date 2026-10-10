package com.program;

import com.program.r2_touch_android.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.util.Log;

public class UserSettingActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{
 
	private Preference resetDialogPreference;  	
	private Intent startIntent;
	
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.settings);
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        initSummary(getPreferenceScreen());
        
        //Initialize the preference object by obtaining a handle to the ResetDefDiagPref object as a Preference  
        this.resetDialogPreference = getPreferenceScreen().findPreference("resetDialog");  
  
        //Store the Intent that started this Activity at this.startIntent.  
        this.startIntent = getIntent(); 
        
        if (this.resetDialogPreference != null) {
            this.resetDialogPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener()   
            {  
                @Override  
                public boolean onPreferenceChange(Preference preference, Object newValue)   
                {  
                    //Both enter and exit animations are set to zero, so no transition animation is applied  
                    overridePendingTransition(0, 0);  
                    //Call this line, just to make sure that the system doesn't apply an animation  
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);  
                    //Close this Activity  
                    finish();  
                    //Again, don't set an animation for the transition  
                    overridePendingTransition(0, 0);  
                    //Start the activity by calling the Intent that have started this same Activity  
                    startActivity(startIntent);  
                    //Return false, so that nothing happens to the preference values  
                    return false;  
                }  
            });
        }

        Preference syncPref = findPreference("syncDroidLinkPref");
        if (syncPref != null) {
            syncPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UserSettingActivity.this);
                    String ip = prefs.getString("prefUserAddress", "192.168.4.1");
                    android.widget.Toast.makeText(UserSettingActivity.this, "Syncing from http://" + ip + "/load...", android.widget.Toast.LENGTH_SHORT).show();
                    DroidLinkSync.syncFromDevice(UserSettingActivity.this, ip, new DroidLinkSync.SyncCallback() {
                        @Override
                        public void onSuccess(final String summary) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    android.widget.Toast.makeText(UserSettingActivity.this, summary, android.widget.Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onError(final String errorMessage) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    android.widget.Toast.makeText(UserSettingActivity.this, "Sync Failed: " + errorMessage, android.widget.Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                    return true;
                }
            });
        }  
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
            String key) {
        updatePrefSummary(findPreference(key));
    }
    
    private void initSummary(Preference p) {
        if (p instanceof PreferenceGroup) {
            PreferenceGroup pGrp = (PreferenceGroup) p;
            for (int i = 0; i < pGrp.getPreferenceCount(); i++) {
                initSummary(pGrp.getPreference(i));
            }
        } else {
            updatePrefSummary(p);
        }
    }

    private void updatePrefSummary(Preference p) {
        if (p instanceof ListPreference) {
            ListPreference listPref = (ListPreference) p;
            p.setSummary(listPref.getEntry());
        }
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p;
            if (p.getTitle().toString().contains("assword"))
            {
                p.setSummary("******");
            } else {
                p.setSummary(editTextPref.getText());
            }
        }
        if (p instanceof MultiSelectListPreference) {
            try {
                EditTextPreference editTextPref = (EditTextPreference) p;
                p.setSummary(editTextPref.getText());
            } catch (java.lang.ClassCastException e) {
                Log.e("updatePrefSummary", "Class Casting Exception");
            }
        }
    }
    
}