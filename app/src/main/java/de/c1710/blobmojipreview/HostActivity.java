/*
 * Copyright (C) 2018 Constantin A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.c1710.blobmojipreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.widget.EmojiTextView;

public class HostActivity extends AppCompatActivity {

    private static final String TAG = HostActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        //Get Intent data
        Intent intent = getIntent();
        String action = intent.getAction();
        String type   = intent.getType();
        String content = intent.getStringExtra(Intent.EXTRA_TEXT);

        // Create dialog content
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);

        // Ensure everything is correct
        if(Intent.ACTION_SEND.equals(action) && type != null && type.startsWith("text") && content != null) {

            // Start creating the dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    // set content
                    .setView(dialogView)
                    // OK will exit
                    .setPositiveButton(
                            R.string.close,
                            (DialogInterface dialog, int width) -> {
                                dialog.dismiss();
                                this.onBackPressed();
                            })
                    .setOnCancelListener((DialogInterface dInterface) -> {this.onBackPressed();});
            // Create & show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            // We'll need to insert the text the other app sent to us into the dialog
            EmojiTextView textView = dialogView.findViewById(R.id.dialog_text);
            try {
                textView.setText(content);
                textView.setMovementMethod(new ScrollingMovementMethod());
            } catch(NullPointerException ex) {
                Log.e(TAG, "Could not set text", ex);
            }
        }
        else {
            // Something went wrong with the Intent.
            // As Intent filters are used, it is extremely unlikely this will ever be shown.
            Toast.makeText(this, ":(", Toast.LENGTH_SHORT).show();
        }
    }
}
