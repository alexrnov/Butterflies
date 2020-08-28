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

  private val serverDownloadObservable = Observable.create { emitter: ObservableEmitter<String?> ->
    // simulate delay
    val assetManager: AssetManager? = this.context?.assets
    val input: InputStream? = assetManager?.open("about/description.txt")
    val s: String = input?.let { loadText(it) } ?: ""
    emitter.onNext(s)
    emitter.onComplete()
  }

  private val disposable = CompositeDisposable()

  private val descriptionClickListener = View.OnClickListener { v: View? ->
    descriptionButton?.setBackgroundResource(R.drawable.button_check)
    ecologyButton?.setBackgroundResource(R.drawable.button_default)

    val subscribe: Disposable = serverDownloadObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { text ->
              textView?.text = text // update interface
            }

    disposable.add(subscribe)
  }

  private val ecologyClickListener = View.OnClickListener { v: View? ->
    ecologyButton?.setBackgroundResource(R.drawable.button_check)
    descriptionButton?.setBackgroundResource(R.drawable.button_default)
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

  private fun loadText(input: InputStream): String {
    Log.i("P", "loadText = P")
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
}