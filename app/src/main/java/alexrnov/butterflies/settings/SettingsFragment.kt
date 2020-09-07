package alexrnov.butterflies.settings

import alexrnov.butterflies.R
import alexrnov.butterflies.showSnackbar
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences, rootKey)

    val fontSize: ListPreference? = findPreference("font_size") as ListPreference?

    if (fontSize != null) {
      if (fontSize.value == null) {
        // to ensure we don't get a null value set first value by default
        fontSize.setValueIndex(0)
      }

      fontSize.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
        //preference.summary = newValue.toString() // change summary under preference name
        val text = when(newValue) {
          "12" -> getString(R.string.very_min_size)
          "16" -> getString(R.string.min_size)
          "18" -> getString(R.string.middle_size)
          "22" -> getString(R.string.big_size)
          else -> getString(R.string.very_big_size)
        }
        showSnackbar(this.requireView(), getString(R.string.font_size) + ": " + text)
        true
      }

      val colorSize: ListPreference? = findPreference("font_color") as ListPreference?
      colorSize?.let {
        // to ensure we don't get a null value set first value by default
        if (it.value == null) it.setValueIndex(0)

        it.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
          val text = when(newValue) {
            "#393939" -> getString(R.string.gray_color)
            "#000000" -> getString(R.string.black_color)
            "#673d24" -> getString(R.string.brown_color)
            "#2076b0" -> getString(R.string.blue_color)
            else -> getString(R.string.green_color)
          }
          showSnackbar(this.requireView(), getString(R.string.font_color) + ": $text")
          true
        }
      }
    }
    /* // click by preferences
    findPreference<Preference>("font_size")!!.onPreferenceClickListener = Preference.OnPreferenceClickListener {
      Log.i("P", "preference click")
      false
    }
    */
  }

  /* // click by preferences
  override fun onPreferenceTreeClick(preference: Preference?): Boolean {
    Log.i("P", "preference tree click")
    return super.onPreferenceTreeClick(preference)
  }
  */
}