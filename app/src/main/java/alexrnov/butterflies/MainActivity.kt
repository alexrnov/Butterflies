package alexrnov.butterflies

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
  // // Fields that need to be injected by the login graph
  @Inject lateinit var loginViewModel: LoginViewModel

  // Reference to the Login graph. Notice that the variable loginComponent is not
  // annotated with @Inject because you're not expecting that variable to be provided by Dagger.
  // LoginComponent is created in the activity's onCreate() method, and it'll get implicitly destroyed when the activity gets destroyed.
  lateinit var loginComponent: LoginComponent
  //So to inform Dagger 2 which member variable I want it to create
  //@Inject lateinit var magicInfo: MagicInfo

  override fun onCreate(savedInstanceState: Bundle?) {
    // Creation of the login graph using the application graph
    loginComponent = (applicationContext as Initialization).appComponent.loginComponent().create()

    // Make Dagger instantiate @Inject fields in LoginActivity
    loginComponent.inject(this)

    /*When using activities, inject Dagger in the activity's onCreate() method
    before calling super.onCreate() to avoid issues with fragment restoration.

    When using fragments, inject Dagger in the fragment's onAttach() method.
    In this case, it can be done before or after calling super.onAttach().
    */
    // Make Dagger instantiate @Inject fields in LoginActivity
    //(applicationContext as Initialization).appComponent.inject(this)

    // Now loginViewModel is available
    //Log.i("P", "loginViewModel.userRepository.s1 = " + loginViewModel.userRepository.localDataSource.s1)
    //Log.i("P", "loginViewModel.userRepository.s2 = " + loginViewModel.userRepository.remoteDataSource.s2)
    // Now loginViewModel is available

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // This Static Class will provide you the Builder to build MagicBox
    //DaggerMagicComponent.create().inject(this)

    //Log.i("P", "info.text = " + magicInfo.text)

    /*
    val repositoryComponent: RepositoryComponent = DaggerRepositoryComponent.create()
    val userRepository: UserRepository = repositoryComponent.repository()
    val userRepository2: UserRepository = repositoryComponent.repository()


    Log.i("P",  "s1 = " + userRepository.s1.s + " s2 = " + userRepository.s2.s)

    if (userRepository == userRepository2) {
      Log.i("P", "repositories is equals")
    } else {
      Log.i("P", "repositories is not equals")
    }
    */
    // in case when used MaterialToolbar
    //val toolbar = findViewById<Toolbar>(R.id.topAppBar)
    //setSupportActionBar(toolbar)

    val toolbar = findViewById<Toolbar>(R.id.appToolbar)
    setSupportActionBar(toolbar)

    val subgenusPagerAdapter = SubgenusPagerAdapter(this, supportFragmentManager)
    val viewPager = findViewById<ViewPager>(R.id.view_pager)
    viewPager.adapter = subgenusPagerAdapter
    val tabs = findViewById<TabLayout>(R.id.tabs)
    tabs.setupWithViewPager(viewPager)

  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_layout, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_about -> {
        Log.i("P", "about")
        true
      }
      R.id.action_map -> {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        true
      }
      R.id.action_settings -> {
        Log.i("P", "settings")
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}


