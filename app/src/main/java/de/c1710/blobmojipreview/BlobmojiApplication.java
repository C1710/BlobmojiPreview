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
import android.net.Uri;
import android.util.Log;


import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import de.c1710.filemojicompat_defaults.DefaultEmojiPackList;
import de.c1710.filemojicompat_ui.helpers.EmojiPackHelper;
import de.c1710.filemojicompat_ui.helpers.EmojiPackList;
import de.c1710.filemojicompat_ui.packs.AssetEmojiPack;
import de.c1710.filemojicompat_ui.packs.DownloadableEmojiPack;
import de.c1710.filemojicompat_ui.structures.EmojiPack;
import de.c1710.filemojicompat_ui.versions.Version;

public class BlobmojiApplication extends Application {
    static final int[] BLOBMOJI_VERSION = {14, 0};

    @Override
    public void onCreate() {
        super.onCreate();

        ArrayList<EmojiPack> packs = DefaultEmojiPackList.get(getApplicationContext());
        EmojiPack blobmojiPride = new AssetEmojiPack(
                "BlobmojiCompat.ttf",
                "Blobmoji Pride",
                "For a more colorful experience",
                ResourcesCompat.getDrawable(getResources(), R.drawable.ic_two_hearts, getTheme()),
                new Version(BLOBMOJI_VERSION),
                Uri.parse("https://github.com/c1710/blobmoji"),
                Uri.parse("https://github.com/C1710/blobmoji/blob/main/LICENSE"),
                "An emoji set based on the one found in earlier Android versions, but enhanced and " +
                        "updated for modern Emoji standards"
        );

        packs.add(blobmojiPride);

        EmojiPackHelper.init(this, packs);
    }

}
