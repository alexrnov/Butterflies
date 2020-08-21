package alexrnov.butterflies.details

import alexrnov.butterflies.model.Repository
import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
        private val repository: Repository): ViewModel() {

  fun f() {
    Log.i("P", "fun f()")
  }

}