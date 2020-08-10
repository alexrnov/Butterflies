package alexrnov.butterflies.details

import alexrnov.butterflies.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    val linkImage =intent.getStringExtra("linkBigImage")
    val linkDescription = intent.getStringExtra("linkDescription")

    Log.i("P", "linkImage = " + linkImage)
    Log.i("P", "limkDescription = " + linkDescription)
  }
}