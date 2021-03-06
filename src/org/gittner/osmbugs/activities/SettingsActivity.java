package org.gittner.osmbugs.activities;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import org.gittner.osmbugs.R;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceScreen;

public class SettingsActivity extends SherlockPreferenceActivity implements OnPreferenceClickListener{

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        findPreference("pref_keepright_reset").setOnPreferenceClickListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        super.onPreferenceTreeClick(preferenceScreen, preference);

        if(preference != null) {
            if (preference instanceof PreferenceScreen) {
                if(((PreferenceScreen) preference).getDialog() != null) {
                    ((PreferenceScreen) preference)
                    .getDialog()
                    .getWindow()
                    .getDecorView()
                    .setBackgroundDrawable(this.getWindow()
                            .getDecorView()
                            .getBackground()
                            .getConstantState()
                            .newDrawable());
                }
            }
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onPreferenceClick(Preference preference) {
        if(preference.getKey().equals("pref_keepright_reset")){
            /* Reset all Keepright Preferences to their Original State */
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_20")).setChecked(false);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_30")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_40")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_50")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_60")).setChecked(false);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_70")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_90")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_100")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_110")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_120")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_130")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_150")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_160")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_170")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_180")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_191")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_192")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_193")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_194")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_195")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_196")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_197")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_198")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_201")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_202")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_203")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_204")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_205")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_206")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_207")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_208")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_210")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_220")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_231")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_232")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_270")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_281")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_282")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_283")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_284")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_285")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_291")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_292")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_293")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_294")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_300")).setChecked(false);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_311")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_312")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_313")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_320")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_350")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_360")).setChecked(false);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_370")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_380")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_390")).setChecked(false);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_401")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_402")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_411")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_412")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_413")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_show_tmpign")).setChecked(true);
            ((CheckBoxPreference) findPreference("pref_keepright_enabled_show_ign")).setChecked(true);
            return true;
        }

        return false;
    }

}
