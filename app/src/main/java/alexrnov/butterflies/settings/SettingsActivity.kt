package alexrnov.butterflies.settings

import alexrnov.butterflies.R
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity(),
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_activity)

    val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
    setSupportActionBar(toolbar)

    supportActionBar?.let {

      it.setDisplayHomeAsUpEnabled(true) // enable the Up button

      val s = "<font color='#ffffff'>" + this.getString(R.string.app_name) + "</font>"
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        it.title = Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY)
      } else {
        @Suppress("DEPRECATION")
        it.title = Html.fromHtml(s)
      }
    }

    supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_settings, SettingsFragment())
            .commit()
  }

  // invoke when sub-settings is taps
  override fun onPreferenceStartFragment(caller: PreferenceFragmentCompat, pref: Preference): Boolean {
    val args = pref.extras
    val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment)
    fragment.arguments = args
    fragment.setTargetFragment(caller, 0)
    supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_settings, fragment)
            .addToBackStack(null)
            .commit()
    return true
  }

  // In order for the home button press to return to the previous activity.
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if (id == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}