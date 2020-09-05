package alexrnov.butterflies.settings

import alexrnov.butterflies.R
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsInfoFragment : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences_about_app, rootKey)
  }
}