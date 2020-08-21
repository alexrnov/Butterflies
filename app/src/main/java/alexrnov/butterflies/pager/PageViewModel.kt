package alexrnov.butterflies.pager

import alexrnov.butterflies.model.Repository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject

// @Inject lets Dagger know how to create instances of this object
class PageViewModel @Inject constructor(
        private val repository: Repository
): ViewModel() {

  private val pageIndex = MutableLiveData<Int>()

  // will run only when the returned LiveData is observed. map - Returns a LiveData mapped from
  // the input source LiveData by applying mapFunction to each value set on source.
  val items = Transformations.map<Int, List<ButterflyData>>(pageIndex) { index: Int ->
    repository.loadList(index)
  }

  fun setIndex(index: Int) {
    pageIndex.value = index
  }

}