package alexrnov.butterflies

import android.app.Dialog
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.util.NotificationLite.disposable
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
    try {
      val assetManager: AssetManager? = this.context?.assets
      val input: InputStream? = assetManager?.open("about/description.txt")
      val s: String = input?.let { loadText(it) } ?: "" // load text with delay
      emitter.onNext(s)
      emitter.onComplete()
    } catch (e: IOException) {
      emitter.onNext("error")
      emitter.onComplete()
      //emitter.onError(e)
    }
  }

  var ecologyObservable = Observable.just { emitter: ObservableEmitter<String?> ->
    try {
      val assetManager: AssetManager? = this.context?.assets
      val input: InputStream? = assetManager?.open("about/ecology.txt")
      val s: String = input?.let { loadText(it) } ?: "" // load text with delay
      emitter.onNext(s)
      emitter.onComplete()
    } catch (e: IOException) {
      emitter.onNext("error")
      emitter.onComplete()
      //emitter.onError(e)
    }
  }

  private val compositeDisposable = CompositeDisposable()

  private val descriptionClickListener = View.OnClickListener { v: View? ->
    // subscribe to this observable. This triggers its execution and provide the subscribe
    // with the required information. mainThread() - the subscriber observes in the main
    // thread. Schedulers.io() - observable is called outside the main thread
    val subscribe: Disposable = descriptionObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { text: String? ->
              textView?.text = text // update interface
              descriptionButton?.setBackgroundResource(R.drawable.button_check)
              ecologyButton?.setBackgroundResource(R.drawable.button_default)
            }

    compositeDisposable.add(subscribe)
  }

  private val ecologyClickListener = View.OnClickListener { v: View? ->
    /*
    val subscribe: Disposable = ecologyObservable.subscribe { text: String? ->
      textView?.text = text // update interface
      ecologyButton?.setBackgroundResource(R.drawable.button_check)
      descriptionButton?.setBackgroundResource(R.drawable.button_default)
    }

    val disposable5 = ecologyObservable.subscribe { colors ->
      textView?.setStrings(colors)
    }

     */
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val activity = requireActivity()
    // this announcement causes a test error
    val builder = AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AboutDialogStyle))

    val v = View.inflate(context, R.layout.fragment_about_dialog, null)
    builder.setView(v)

    val closeButton = v.findViewById<Button>(R.id.close_dialog_button)
    closeButton?.setOnClickListener(backClickListener)

    descriptionButton = v.findViewById(R.id.description_button)
    descriptionButton?.setOnClickListener(descriptionClickListener)

    ecologyButton = v.findViewById(R.id.ecology_button)
    ecologyButton?.setOnClickListener(ecologyClickListener)

    textView = v.findViewById(R.id.information_textView)
    return builder.create()
  }

  @Throws(IOException::class)
  private fun loadText(input: InputStream): String {
    val result = StringBuilder()
    val bf = BufferedReader(InputStreamReader(input))
    var line = bf.readLine()
    while (line != null) {
      result.append(line)
      result.append(System.getProperty("line.separator"))
      line = bf.readLine()
    }
    return result.toString()
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.i("P", "onDestroy() invoke")
    // to prevent a possible (temporary) memory leak (used onDestroy() or onStop() methods)
    if (!compositeDisposable.isDisposed) {
      // dispose the subscription when not interested in the emitted data any more
      compositeDisposable.dispose()
    }
  }
}