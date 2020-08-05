package alexrnov.butterflies

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.*
import java.util.*
import javax.inject.Inject

class PageViewModel : ViewModel() {

  @NonNull
  private val repository = Repository()

  private val pageIndex = MutableLiveData<Int>()

  // will run only when the returned LiveData is observed. map - Returns a LiveData mapped from
  // the input source LiveData by applying mapFunction to each value set on source.
  val items = Transformations.map<Int, List<String>>(pageIndex) { index: Int ->
    repository.loadList(index)
  }

  fun setIndex(index: Int) {
    pageIndex.value = index
  }
}