package alexrnov.butterflies.details

import alexrnov.butterflies.model.Repository
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
        private val repository: Repository): ViewModel() {

  private val bigImage = MutableLiveData<Drawable>()
  private val detailsText = MutableLiveData<String>()

  fun getBigImage(): LiveData<Drawable> = bigImage
  fun getDetailsText(): LiveData<String> = detailsText

  fun loadBigImage(input: InputStream) {
    viewModelScope.launch {
      withContext(Dispatchers.Default) {
        bigImage.postValue(repository.loadImage(input))
      }
    }
  }

  fun loadDetailsText(input: InputStream) {
    viewModelScope.launch {
      loadText(input)
    }
  }

  private suspend fun loadText(input: InputStream) = withContext(Dispatchers.Default) {
    val text = repository.loadText(input)
    detailsText.postValue(text)
  }
}