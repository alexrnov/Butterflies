package alexrnov.butterflies

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment

class AboutDialogFragment : DialogFragment() {
  private var descriptionButton: Button? = null
  private var ecologyButton: Button? = null

  private val backClickListener = View.OnClickListener { v: View? ->
    val dialog = this@AboutDialogFragment.dialog
    dialog?.cancel()
  }

  private val descriptionClickListener = View.OnClickListener { v: View? ->
    Log.i("P", "description click")
    descriptionButton?.setBackgroundResource(R.drawable.button_check)
    ecologyButton?.setBackgroundResource(R.drawable.button_default)
  }

  private val ecologyClickListener = View.OnClickListener { v: View? ->
    Log.i("P", "ecology click")
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

    descriptionButton = v.findViewById<Button>(R.id.description_button)
    descriptionButton?.setOnClickListener(descriptionClickListener)

    ecologyButton = v.findViewById<Button>(R.id.ecology_button)
    ecologyButton?.setOnClickListener(ecologyClickListener)

    return builder.create()
  }
}