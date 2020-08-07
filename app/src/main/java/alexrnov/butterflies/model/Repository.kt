package alexrnov.butterflies.model

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.*
import java.nio.file.Paths
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

// @Inject lets Dagger know how to create instances of this object
// Scope this class to a component using @Singleton scope (i.e. ApplicationGraph)
@Singleton
class Repository @Inject constructor(val context: Context) {

  /** invoke from PageViewModel class */
  fun loadList(pageIndex: Int): List<String> {

    val assetManager: AssetManager = context.assets
    val arrayItems: Array<out String>? = assetManager.list("data/tab${pageIndex + 1}")
    val listItems: MutableList<String> = arrayItems?.toMutableList()?: ArrayList()

    listItems.sortWith(Comparator { s1: String, s2: String ->
      val n1 = s1.removeRange(0, 4).toInt()
      val n2 = s2.removeRange(0, 4).toInt()
      n1.compareTo(n2)
    })

    val returnList:MutableList<ButterflyData> = ArrayList()

    listItems.forEach { item ->

      var input = assetManager.open("data/tab${pageIndex + 1}/$item/title_port.txt")
      val titlePort = loadText(input)

      input = assetManager.open("data/tab${pageIndex + 1}/$item/title_land.txt")
      val titleLand = loadText(input)

      input = assetManager.open("data/tab${pageIndex + 1}/$item/small_image.jpg")
      val smallImage: Drawable = Drawable.createFromStream(input, null)

      returnList.add(ButterflyData(titlePort, titleLand, smallImage))
    }

    returnList.forEach { item ->
      Log.i("P", "titlePort = " + item.titlePort)
      Log.i("P", "titleLand = " + item.titleLand)
      Log.i("P", "smallImage = " + item.smallImage)
      Log.i("P", "----------------------------")
    }



    val input = context.assets.open("data/tab${pageIndex + 1}/item1/description.txt")

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
      Log.i("P", "result = " + result.toString())
    } catch (e: IOException) {
      //e.printStackTrace();
    }


    val list: MutableList<String> = ArrayList()

    if (pageIndex == 0) {
      list.add("150")
      list.add("1")
      list.add("2")
      list.add("3")
      list.add("4")
      list.add("5")
      list.add("6")
    } else if (pageIndex == 1) {
      list.add("7")
      list.add("8")
      list.add("9")
      list.add("10")
      list.add("11")
      list.add("12")
      list.add("13")
    } else if (pageIndex == 2) {
      list.add("14")
      list.add("15")
      list.add("16")
      list.add("17")
      list.add("18")
      list.add("19")
      list.add("20")
    } else if (pageIndex == 3) {
      list.add("21")
      list.add("22")
      list.add("23")
      list.add("24")
      list.add("25")
      list.add("26")
      list.add("27")
    } else if (pageIndex == 4) {
      list.add("28")
      list.add("29")
      list.add("30")
      list.add("31")
      list.add("32")
      list.add("33")
      list.add("34")
    } else if (pageIndex == 5) {
      list.add("35")
      list.add("36")
      list.add("37")
      list.add("38")
      list.add("39")
      list.add("40")
      list.add("41")
    } else if (pageIndex == 6) {
      list.add("42")
      list.add("43")
      list.add("44")
      list.add("45")
      list.add("46")
      list.add("47")
      list.add("48")
    } else {
      list.add("49")
      list.add("50")
      list.add("51")
      list.add("52")
      list.add("53")
      list.add("54")
      list.add("55")
    }
    return list
  }





  private fun loadText(input: InputStream): String {

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
      Log.i("P", "result = " + result.toString())
    } catch (e: IOException) {
      //e.printStackTrace();
    }
    return result.toString()

  }


}