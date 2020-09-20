package alexrnov.butterflies.model

import alexrnov.butterflies.pager.ButterflyData
import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import java.io.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

// @Inject lets Dagger know how to create instances of this object
// Scope this class to a component using @Singleton scope (i.e. ApplicationGraph)
@Singleton
class Repository @Inject constructor(val context: Context) {

  /** invoke from PageViewModel class */
  fun loadList(pageIndex: Int): List<ButterflyData> {

    val assetManager: AssetManager = context.assets
    val arrayItems: Array<out String>? = assetManager.list("data/tab${pageIndex + 1}")
    val listItems: MutableList<String> = arrayItems?.toMutableList()?: ArrayList()

    listItems.sortWith { s1: String, s2: String ->
      val n1 = s1.removeRange(0, 4).toInt()
      val n2 = s2.removeRange(0, 4).toInt()
      n1.compareTo(n2)
    }

    val returnList:MutableList<ButterflyData> = ArrayList()

    listItems.forEach { item ->

      var input = assetManager.open("data/tab${pageIndex + 1}/$item/title_port.txt")
      val titlePort = loadText(input)

      input = assetManager.open("data/tab${pageIndex + 1}/$item/title_land.txt")
      val titleLand = loadText(input)

      input = assetManager.open("data/tab${pageIndex + 1}/$item/small_image.jpg")
      val smallImage: Drawable = Drawable.createFromStream(input, null)

      val butterflyData = ButterflyData(titlePort, titleLand, smallImage,
              "data/tab${pageIndex + 1}/$item/big_image.jpg",
              "data/tab${pageIndex + 1}/$item/description.txt")
      returnList.add(butterflyData)
    }
    return returnList
  }

  fun loadText(input: InputStream): String {
    val bf: BufferedReader
    val result = StringBuilder()
    try {
      bf = BufferedReader(InputStreamReader(input))
      var line = bf.readLine()
      while (line != null) {
        result.append(line)
        result.append(System.getProperty("line.separator"))
        line = bf.readLine()
      }
    } catch (e: IOException) {
      //e.printStackTrace();
    }
    return result.toString()
  }

  fun loadImage(input: InputStream): Drawable {
    return Drawable.createFromStream(input, null)
  }
}