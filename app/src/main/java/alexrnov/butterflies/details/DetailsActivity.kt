package alexrnov.butterflies.details

import alexrnov.butterflies.AboutDialogFragment
import alexrnov.butterflies.Initialization
import alexrnov.butterflies.R
import alexrnov.butterflies.databinding.ActivityDetailsBinding
import alexrnov.butterflies.map.MapsActivity
import alexrnov.butterflies.model.ActivityComponent
import alexrnov.butterflies.model.TextStyleObserver
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import java.io.InputStream
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
  private lateinit var activityComponent: ActivityComponent
  @Inject lateinit var detailsViewModel: DetailsViewModel

  private var detailsText: TextView? = null
  private var bigImage: ImageView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    // creation of the login graph using the application graph
    activityComponent = (applicationContext as Initialization).applicationComponent
            .activityComponent()
            .create()
    // make Dagger instantiate @Inject fields in DetailsActivity
    activityComponent.inject(this)

    super.onCreate(savedInstanceState)

    // use data binding
    val binding: ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
    binding.lifecycleOwner = this
    binding.viewmodel = detailsViewModel
    //setContentView(R.layout.activity_details) // when the data binding is not using

    val toolbar = findViewById<Toolbar>(R.id.detailsAppToolbar)
    setSupportActionBar(toolbar)

    val actionBar = supportActionBar
    if (actionBar != null) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions
        // below android N we are using this flag to give a consistent behaviour
        actionBar.title = Html.fromHtml("<font color='#ffffff'>" +
                this.getString(R.string.app_name) + "</font>", Html.FROM_HTML_MODE_LEGACY)
      } else {
        @Suppress("DEPRECATION")
        actionBar.title = Html.fromHtml("<font color='#ffffff'>" +
                this.getString(R.string.app_name) + "</font>")
      }
    }

    bigImage = findViewById(R.id.big_image)
    detailsText = findViewById(R.id.description_text)

    /* Create the observer which updates the UI (when data binding not used) */
    //val imageObserver: Observer<Drawable> = Observer { bigImage?.setImageDrawable(it) }
    //detailsViewModel.getBigImage().observe(this, imageObserver)
    //val textObserver: Observer<String> = Observer { detailsText?.text = it ?: "" }
    //detailsViewModel.getDetailsText().observe(this, textObserver)

    val assetManager: AssetManager = baseContext.assets

    val linkImage = intent.getStringExtra("linkBigImage")
    linkImage?.let { detailsViewModel.loadBigImage(assetManager.open(it)) }

    val linkDescription = intent.getStringExtra("linkDescription")
    linkDescription?.let { detailsViewModel.loadDetailsText(assetManager.open(it)) }


    val observer = TextStyleObserver(applicationContext, lifecycle)
    detailsText?.let { observer.addView(it) }
    lifecycle.addObserver(observer)
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
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}