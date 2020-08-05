package alexrnov.butterflies

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository {

  @Inject
  lateinit var context: Context

  /** invoke from ViewModel class */
  fun loadList(pageIndex: Int): List<String> {

    context = Initialization.contextComponent.inject()

    Log.i("P", "con repository = " + context.packageName)

    val input = context.assets.open("tab1/item1/description.txt")


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
      list.add("0")
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
    } else {
      list.add("14")
      list.add("15")
      list.add("16")
      list.add("17")
      list.add("18")
      list.add("19")
      list.add("20")
    }
    return list
  }
}