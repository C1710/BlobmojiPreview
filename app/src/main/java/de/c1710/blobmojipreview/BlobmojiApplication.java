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

import androidx.emoji.text.EmojiCompat;

import java.io.File;

import de.c1710.filemojicompat.FileEmojiCompatConfig;
import de.c1710.filemojicompat.ReplaceStrategy;


public class BlobmojiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EmojiCompat.Config config = FileEmojiCompatConfig.init(getApplicationContext(), (File) null, "BlobmojiCompat.ttf")
                .setReplaceAll(ReplaceStrategy.ALWAYS);
        EmojiCompat.init(config);
    }

}
