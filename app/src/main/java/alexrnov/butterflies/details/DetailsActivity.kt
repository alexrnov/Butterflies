package alexrnov.butterflies.details

import alexrnov.butterflies.Initialization
import alexrnov.butterflies.MainActivity
import alexrnov.butterflies.R
import alexrnov.butterflies.model.ActivityComponent
import alexrnov.butterflies.model.Repository
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

  @Inject lateinit var repository: Repository

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

    val linkImage =intent.getStringExtra("linkBigImage")
    val linkDescription = intent.getStringExtra("linkDescription")

    repository.print()
    Log.i("P", "linkImage = " + linkImage)
    Log.i("P", "limkDescription = " + linkDescription)
  }
}