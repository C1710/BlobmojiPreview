package de.c1710.blobmojipreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.c1710.filemojicompat_ui.views.picker.EmojiPackItemAdapter

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val recycler: RecyclerView = findViewById(R.id.emoji_selection)
        recycler.adapter = EmojiPackItemAdapter.get(this)

        /*supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()*/
    }
}