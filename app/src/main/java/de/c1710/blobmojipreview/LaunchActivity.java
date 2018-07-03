package de.c1710.blobmojipreview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.text.emoji.widget.EmojiEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;


public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = LaunchActivity.class.getCanonicalName();
    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // Create dialog content
        dialogView = LayoutInflater.from(this).inflate(R.layout.layout_edit_dialog, null);

        // Start creating the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                // set content
                .setView(dialogView)
                // OK will exit
                .setPositiveButton(
                        R.string.close,
                        (DialogInterface dialog, int width) -> {
                            this.onBackPressed();
                        })
                .setOnCancelListener((DialogInterface dInterface) -> {this.onBackPressed();})
                .setNeutralButton(R.string.delete, null)
        .setNegativeButton(R.string.opensource, (DialogInterface dFace, int width) -> {
            startActivity(new Intent(this, OssLicensesMenuActivity.class));
        });

        // Create & show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Since clicking on a button usually dismisses the dialog, the onClick-Method has to be overridden
        Button deleteButton = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        deleteButton.setOnClickListener((View v) -> {
            EditText editText = dialogView.findViewById(R.id.dialog_edittext);
            editText.setText("");
            editText.requestFocus();
        });

        // So, the plan is to paste the clipboard by default
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        CharSequence content;
        // It might be possible that we don't have any clipboard data.
        if (clipboardManager != null) {
            ClipData clipData = clipboardManager.getPrimaryClip();
            if (clipData.getItemCount() >= 1) {
                // Yay! There's content!
                content = clipData.getItemAt(0).coerceToText(this);
            } else {
                // Since there is no content inside the clipboard, we'll just default to nothing.
                content = "";
            }
        } else {
            // Since there is no content inside the clipboard, we'll just default to nothing.
            content = "";
        }

        // We'll need to insert the text we got from the clipboard into the dialog
        EmojiEditText editText = dialogView.findViewById(R.id.dialog_edittext);
        try {
            editText.setText(content);
            editText.setMovementMethod(new ScrollingMovementMethod());
        } catch(NullPointerException ex) {
            Log.e(TAG, "Could not set text", ex);
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        // Restarting the app should cause an update on the text shown inside the dialog
        // So, the plan is to paste the clipboard by default
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        CharSequence content = "";
        // It might be possible that we don't have any clipboard data.
        try {
            if (clipboardManager != null) {
                ClipData clipData = clipboardManager.getPrimaryClip();
                if (clipData.getItemCount() >= 1) {
                    // Yay! There's content!
                    content = clipData.getItemAt(0).coerceToText(this);
                } else {
                    // Since there is no content inside the clipboard, we'll just default to nothing.
                    content = "";
                }
            } else {
                // Since there is no content inside the clipboard, we'll just default to nothing.
                content = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmojiEditText editText = dialogView.findViewById(R.id.dialog_edittext);
        editText.setText(content);
    }
}
