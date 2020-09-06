package alexrnov.butterflies.model

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.preference.PreferenceManager

/**
 * Lifecycle-aware components perform actions in response to a change in the
 * lifecycle status of another component, such as activities and fragments.
 */
internal class TextStyleObserver(
        private val context: Context,
        private val lifecycle: Lifecycle) : LifecycleObserver {

  private lateinit var sharedPref: SharedPreferences
  private lateinit var textView: TextView

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun create() {
    sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
  }

  fun addView(textView: TextView) {
    Log.i("P", "invoke fun addView()")
    // check for a specific state. Since multiple states can interleave for a given point
    // of time, if we want to check for a specific state, we always use the isAtLeast method:
    // from the above illustration we start with the INITIALIZED state which represents
    // the stat where the lifecycle owner is constructed but still didn’t receive its onCreate() yet.
    if (lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
      this.textView = textView
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun resume() {
    val size = sharedPref.getString("font_size", "20")
    val color = sharedPref.getString("font_color", "#000000")
    textView.textSize = size!!.toFloat()
    textView.setTextColor(Color.parseColor(color))
  }
}