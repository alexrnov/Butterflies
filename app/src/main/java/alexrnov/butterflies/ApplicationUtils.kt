package alexrnov.butterflies

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

/**
 * View snackbar
 * [view] - root view;
 * [message] - message text.
 */
fun showSnackbar(view: View, message: CharSequence) {
  val snackbar = Snackbar.make(view, message, 2000)
  snackbar.setAction("OK") { snackbar.dismiss() } // when you click on the button, the snackbar just hides
  snackbar.setActionTextColor(Color.parseColor("#95d7ff")) // button color
  val snackbarView = snackbar.view
  snackbarView.setBackgroundColor(Color.parseColor("#656565")) // background color
  snackbarView.setPadding(0, 0, 0, 0)
  // set the width of the snackbar to the screen - this is necessary, since on the tablet the snackbar by default occupies only part of the screen
  val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams
  //val params = snackbarView.layoutParams as FrameLayout.LayoutParams
  // another option is to expand the snackbar
  //snackbarView.getLayoutParams().width = AppBarLayout.LayoutParams.MATCH_PARENT;
  params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
  params.width = FrameLayout.LayoutParams.MATCH_PARENT
  snackbarView.layoutParams = params

  // invoke for android support library
  //val textView = snackbarView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
  // invoke for library androidx
  val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
  textView.setTextColor(Color.parseColor("#95d7ff")) // message color
  snackbar.show()
}

/** Get screen size with the navigation bar */
fun getScreenSizeWithNavBar(activity: FragmentActivity): Pair<Float, Float> {
  val context = activity.applicationContext
  val orientation = context.resources.configuration.orientation

  val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
  val w: Int
  val h: Int
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    val windowMetrics = wm.currentWindowMetrics
    val windowInsets: WindowInsets = windowMetrics.windowInsets

    val insets = windowInsets.getInsetsIgnoringVisibility(
            WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout())
    val insetsWidth = insets.right + insets.left
    val insetsHeight = insets.top + insets.bottom

    val b = windowMetrics.bounds
    w = b.width() - insetsWidth
    h = b.height() - insetsHeight
  } else {
    val size = Point()
    val display = wm.defaultDisplay // deprecated in API 30
    display?.getSize(size) // deprecated in API 30
    w = size.x
    h = size.y
  }

  val width = getWidth(w, h, orientation)
  val height = getHeight(w, h, orientation)

  val displayMetrics = activity.resources.displayMetrics
  // logical density of the display. This is a pixel density independent scaling factor (added in API Level 1)
  val density: Float = displayMetrics.density
  val dpWidth: Float = width / density
  val dpHeight: Float = height / density
  //println("density = $density, width = $width, height = $height, dpWidth = $dpWidth, dpHeight = $dpHeight")
  return Pair(dpWidth, dpHeight)
}

private fun getWidth(x: Int, y: Int, orientation: Int): Int {
  return if (orientation == Configuration.ORIENTATION_PORTRAIT) x else y
}

private fun getHeight(x: Int, y: Int, orientation: Int): Int {
  return if (orientation == Configuration.ORIENTATION_PORTRAIT) y else x
}