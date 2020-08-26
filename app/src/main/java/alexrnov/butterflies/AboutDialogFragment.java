package alexrnov.butterflies;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

public class AboutDialogFragment extends DialogFragment {

  private View.OnClickListener clickListener = v -> {
    Dialog dialog = AboutDialogFragment.this.getDialog();
    if (dialog != null) {
      dialog.cancel();
    }
  };

  @NotNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    FragmentActivity activity = this.requireActivity();
    // this announcement causes a test error
    //AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AboutDialogStyle));

    //LayoutInflater inflater = activity.getLayoutInflater();
    //View v = inflater.inflate(R.layout.about_dialog, null);
    View v = View.inflate(getContext(), R.layout.about_dialog, null);
    builder.setView(v);
    /* // add action buttons when used standard alert dialog buttons
    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {}});
    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {}});
    */
    Button button = v.findViewById(R.id.close_dialog_button);
    if (button != null) button.setOnClickListener(clickListener);
    return builder.create();
  }
}