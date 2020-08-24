package alexrnov.butterflies

import alexrnov.butterflies.model.Repository
import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import com.google.common.truth.Truth.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.InputStream

@RunWith(MockitoJUnitRunner::class)
class PageViewModelTest {

  private lateinit var repository: Repository

  @Mock
  private lateinit var draw: Drawable

  @Mock
  private lateinit var context: Context

  @Mock
  private lateinit var assetManager: AssetManager

  @Mock
  private lateinit var inputStream: InputStream

  @Test
  fun f() {

    //val arrayItems: Array<out String>? = arrayOf("item1", "item2", "item3")
    /*
    `when`(assetManager.list(anyString())).thenReturn(arrayItems)

    val inputStream =
    `when`(assetManager.open(anyString())).thenReturn(inputStream)
    `when`(context.assets).thenReturn(assetManager)

    // You don't need Dagger to create an instance of Repository
    // You can pass a fake or mock Context
    repository = Repository(context)

    val list5 = repository.loadList(0)

    println("list5 = " + list5.size)
    val list = mutableListOf<ButterflyData>()
    list.add(ButterflyData("port", "land", draw, "", ""))


     */
    //assertThat(repository.loadList(4)).isEqualTo(list)

    // looper error (thread error)

    // val pageViewModel = PageViewModel(repository)
    // pageViewModel.setIndex(1)
    // val list2 = pageViewModel.items
    // assertThat(list.size).isEqualTo(list2.value!!.size)
  }
}