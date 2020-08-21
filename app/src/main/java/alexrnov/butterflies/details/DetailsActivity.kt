package alexrnov.butterflies.details

import alexrnov.butterflies.Initialization
import alexrnov.butterflies.MainActivity
import alexrnov.butterflies.R
import alexrnov.butterflies.map.MapsActivity
import alexrnov.butterflies.model.ActivityComponent
import alexrnov.butterflies.model.Repository
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
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

  @Inject lateinit var repository: Repository

  @Inject lateinit var detailsViewModel: DetailsViewModel

  private lateinit var activityComponent: ActivityComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    // creation of the login graph using the application graph
    activityComponent = (applicationContext as Initialization).applicationComponent
            .activityComponent()
            .create()
    // make Dagger instantiate @Inject fields in DetailsActivity
    activityComponent.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

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

    val linkImage =intent.getStringExtra("linkBigImage")
    val linkDescription = intent.getStringExtra("linkDescription")

    val assetManager: AssetManager = baseContext.assets

    val bigImage: ImageView = findViewById(R.id.big_image)

    var input = assetManager.open(linkImage)
    val smallImage: Drawable = Drawable.createFromStream(input, null)

    input = assetManager.open(linkDescription)
    val text = repository.loadText(input)
    val textView: TextView = findViewById(R.id.description_text)
    textView.text = text

    bigImage.setImageDrawable(smallImage)

    repository.print()
    Log.i("P", "linkImage = " + linkImage)
    Log.i("P", "limkDescription = " + linkDescription)
    detailsViewModel.f()
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