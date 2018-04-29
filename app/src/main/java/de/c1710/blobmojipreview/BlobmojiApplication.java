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

import android.app.Application;
import android.content.res.AssetManager;
import android.support.text.emoji.EmojiCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.c1710.filemojicompat.FileEmojiCompatConfig;


public class BlobmojiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // This is the font file which is used
        File emojiFont = new File(getApplicationContext()
                .getExternalFilesDir(null),
                "EmojiCompat.ttf");
        if(!emojiFont.exists()) {
            // Copy to external file
            AssetManager assetManager = getAssets();
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("NotoColorEmojiCompat.ttf");
                out = new FileOutputStream(emojiFont);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, length);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(in != null) {
                        in.close();
                    }
                    if(out != null) {
                        out.close();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
        EmojiCompat.Config config =
                new FileEmojiCompatConfig(getApplicationContext(),
                        emojiFont
                        )
                .setReplaceAll(true);
        EmojiCompat.init(config);
    }

}
