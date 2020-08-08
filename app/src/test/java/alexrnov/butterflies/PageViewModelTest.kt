package alexrnov.butterflies

import alexrnov.butterflies.model.ButterflyData
import alexrnov.butterflies.model.PageViewModel
import alexrnov.butterflies.model.Repository
import android.content.Context
import android.graphics.drawable.Drawable

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PageViewModelTest {

  @Mock
  private lateinit var repository: Repository

  @Mock
  private lateinit var draw: Drawable

  @Mock
  private lateinit var context: Context

  @Test
  fun f() {

    val list = mutableListOf<ButterflyData>()
    list.add(ButterflyData("port", "land", draw, "", ""))
    `when`(repository.loadList(anyInt())).thenReturn(list)
    assertThat(repository.loadList(4)).isEqualTo(list)

    // looper error (thread error)

    val pageViewModel = PageViewModel(repository)
    pageViewModel.setIndex(1)
    val list2 = pageViewModel.items
    assertThat(list.size).isEqualTo(list2.value!!.size)

  }
}