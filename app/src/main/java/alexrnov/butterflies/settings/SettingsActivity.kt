package alexrnov.butterflies.settings

import alexrnov.butterflies.R
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity(),
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)

    val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
    toolbar.setTitle(R.string.app_name)
    toolbar.setTitleTextColor(Color.parseColor("#ffffff"))
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

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