package alexrnov.butterflies.details

import alexrnov.butterflies.model.Repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.InputStream
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
        private val repository: Repository): ViewModel() {

  private val detailsText = MutableLiveData<String>()

  fun getDetailsText(): LiveData<String> {
    return detailsText
  }

  fun loadDetailsText(input: InputStream) {
    detailsText.value = repository.loadText(input)
  }

}