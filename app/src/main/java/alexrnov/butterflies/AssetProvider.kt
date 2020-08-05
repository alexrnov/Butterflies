package alexrnov.butterflies

import android.content.Context
import javax.inject.Inject

class AssetProvider @Inject constructor(private val context: Context) {
  fun getDescription() = context.assets.open("tab1/item1/description.txt")

}