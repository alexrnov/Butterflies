package alexrnov.butterflies

import alexrnov.butterflies.map.MapsActivity
import alexrnov.butterflies.model.ActivityComponent
import alexrnov.butterflies.pager.PageViewModel
import alexrnov.butterflies.pager.PagerAdapter
import alexrnov.butterflies.settings.SettingsActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  // You want Dagger to provide an instance of LoginViewModel from the graph
  // Because MainActivity injects LoginViewModel, Dagger builds a graph that knows how
  // to provide an instance of LoginViewModel, and recursively, of its dependencies.
  // Dagger knows how to do this because of the @Inject annotation on the classes' constructor
  // Fields that need to be injected by the login graph
  @Inject lateinit var pageViewModel: PageViewModel

  // Reference to the Login graph. Notice that the variable loginComponent is not
  // annotated with @Inject because you're not expecting that variable to be provided by Dagger.
  // LoginComponent is created in the activity's onCreate() method, and it'll get implicitly destroyed when the activity gets destroyed.
  lateinit var activityComponent: ActivityComponent

  // When using activities, inject Dagger in the activity's onCreate() method
  // before calling super.onCreate() to avoid issues with fragment restoration.
  override fun onCreate(savedInstanceState: Bundle?) {
    // creation of the login graph using the application graph
    activityComponent = (applicationContext as Initialization).applicationComponent.activityComponent().create()
    // make Dagger instantiate @Inject fields in MainActivity
    activityComponent.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // in case when used MaterialToolbar
    //val toolbar = findViewById<Toolbar>(R.id.topAppBar)
    //setSupportActionBar(toolbar)

    val toolbar = findViewById<Toolbar>(R.id.appToolbar)
    setSupportActionBar(toolbar)

    val pagerAdapter = PagerAdapter(this, supportFragmentManager)
    val viewPager = findViewById<ViewPager>(R.id.view_pager)
    viewPager.adapter = pagerAdapter
    val tabs = findViewById<TabLayout>(R.id.tabs)
    tabs.setupWithViewPager(viewPager)

    //getScreenSizeWithNavBar(this)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_layout, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_about -> {
        val aboutDialog = AboutDialogFragment()
        aboutDialog.show(this.supportFragmentManager, "tag")
        true
      }
      R.id.action_map -> {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        true
      }
      R.id.action_settings -> {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}