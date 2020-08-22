package alexrnov.butterflies.details

import alexrnov.butterflies.model.Repository
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.InputStream
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
        private val repository: Repository): ViewModel() {

  private val bigImage = MutableLiveData<Drawable>()
  private val detailsText = MutableLiveData<String>()

  fun getBigImage(): LiveData<Drawable> = bigImage
  fun getDetailsText(): LiveData<String> = detailsText

  fun loadBigImage(input: InputStream) {
    bigImage.value = repository.loadImage(input)
  }

  fun loadDetailsText(input: InputStream) {
    detailsText.value = repository.loadText(input)
  }
}