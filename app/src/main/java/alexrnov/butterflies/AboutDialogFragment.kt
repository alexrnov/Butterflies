package alexrnov.butterflies

import alexrnov.butterflies.Initialization.Companion.checkFirstButtonDialog
import android.app.Dialog
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class AboutDialogFragment : DialogFragment() {
  private var descriptionButton: Button? = null
  private var ecologyButton: Button? = null
  private var textView: TextView? = null

  private val backClickListener = View.OnClickListener {
    val dialog = this@AboutDialogFragment.dialog
    dialog?.cancel()
  }

  // define a potentially long running operation via the following observable
  private val descriptionObservable = Observable.create { emitter: ObservableEmitter<String?> ->
    val assetManager: AssetManager? = this.context?.assets
    val input: InputStream? = assetManager?.open("about/description.txt")
    val s: String = input?.let { loadText(it) } ?: "" // load text with delay
    emitter.onNext(s)
    emitter.onComplete()
  }

  // define a potentially long running operation via the following observable
  private val ecologyObservable = Observable.create { emitter: ObservableEmitter<String?> ->
    val assetManager: AssetManager? = this.context?.assets
    val input: InputStream? = assetManager?.open("about/ecology.txt")
    val s: String = input?.let { loadText(it) } ?: "" // load text with delay
    emitter.onNext(s)
    emitter.onComplete()
  }

  private val compositeDisposable = CompositeDisposable()

  private val descriptionClickListener = View.OnClickListener {
    // subscribe to this observable. This triggers its execution and provide the subscribe
    // with the required information. mainThread() - the subscriber observes in the main
    // thread. Schedulers.io() - observable is called outside the main thread
    val subscribe: Disposable = descriptionObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { text: String? ->
              textView?.text = text // update interface
              descriptionButton?.setBackgroundResource(R.drawable.button_check)
              descriptionButton?.setTextColor(ContextCompat.getColor(
                      requireContext(), R.color.selectTabTitle))
              ecologyButton?.setBackgroundResource(R.drawable.button_default)
              ecologyButton?.setTextColor(ContextCompat.getColor(
                      requireContext(), R.color.defaultTabTitle))
              checkFirstButtonDialog = true
            }
    compositeDisposable.add(subscribe)
  }

  private val ecologyClickListener = View.OnClickListener {
    val subscribe: Disposable = ecologyObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { text: String? ->
              textView?.text = text // update interface
              ecologyButton?.setBackgroundResource(R.drawable.button_check)
              ecologyButton?.setTextColor(ContextCompat.getColor(
                      requireContext(), R.color.selectTabTitle))
              descriptionButton?.setBackgroundResource(R.drawable.button_default)
              descriptionButton?.setTextColor(ContextCompat.getColor(
                      requireContext(), R.color.defaultTabTitle))
              checkFirstButtonDialog = false
            }
    compositeDisposable.add(subscribe)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val fragmentActivity = requireActivity()
    val v = View.inflate(context, R.layout.fragment_about_dialog, null)
    val dialog = Dialog(fragmentActivity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(v)

    val wm = fragmentActivity.getSystemService(Context.WINDOW_SERVICE) as WindowManager // for activity use context instead of getActivity()

    /*
    val display = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
      fragmentActivity.display
    } else {
      wm.defaultDisplay // deprecated in API 30
    }
    */
    val width: Int
    val height: Int
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      val windowMetrics = wm.currentWindowMetrics
      val windowInsets: WindowInsets = windowMetrics.windowInsets

      val insets = windowInsets.getInsetsIgnoringVisibility(
              WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout())
      val insetsWidth = insets.right + insets.left
      val insetsHeight = insets.top + insets.bottom

      val b = windowMetrics.bounds
      val legacySize = Size(b.width() - insetsWidth,
              b.height() - insetsHeight)

      width = legacySize.width - 50
      height = legacySize.height - 50
    } else {
      val size = Point()
      val display = wm.defaultDisplay // deprecated in API 30
      display?.getSize(size)
      width = size.x - 50
      height = size.y - 50
    }

    //Log.i("P", "size.x = " + size.x + "size.y = " + size.y)

    //val width = size.x - 50
    //val height = size.y - 50
    val lp = WindowManager.LayoutParams()
    lp.copyFrom(dialog.window?.attributes)
    lp.width = width
    lp.height = height
    dialog.show()
    dialog.window?.attributes = lp




    val closeButton = v.findViewById<Button>(R.id.close_dialog_button)
    closeButton?.setOnClickListener(backClickListener)

    descriptionButton = v.findViewById(R.id.description_button)
    descriptionButton?.setOnClickListener(descriptionClickListener)

    ecologyButton = v.findViewById(R.id.ecology_button)
    ecologyButton?.setOnClickListener(ecologyClickListener)

    textView = v.findViewById(R.id.information_textView)

    /* define which a button to click when creating a dialog (open, rotate) */
    if (checkFirstButtonDialog) descriptionButton?.performClick()
    else ecologyButton?.performClick()

    return dialog
  }

  private fun loadText(input: InputStream): String {
    val result = StringBuilder()
    try {
      val bf = BufferedReader(InputStreamReader(input))
      var line = bf.readLine()
      while (line != null) {
        result.append(line)
        result.append(System.getProperty("line.separator"))
        line = bf.readLine()
      }
    } catch (e: IOException) {}
    return result.toString()
  }

  override fun onDestroy() {
    super.onDestroy()
    // to prevent a possible (temporary) memory leak (used onDestroy() or onStop() methods)
    if (!compositeDisposable.isDisposed) {
      // dispose the subscription when not interested in the emitted data any more
      compositeDisposable.dispose()
    }
  }

  override fun onResume() {
    super.onResume()


  }
}