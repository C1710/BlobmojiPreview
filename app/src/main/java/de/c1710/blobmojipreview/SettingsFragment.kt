package de.c1710.blobmojipreview

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import de.c1710.filemojicompat_ui.views.picker.preference.EmojiPickerDialogFragment
import de.c1710.filemojicompat_ui.views.picker.preference.EmojiPickerPreference

class SettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        val emojiPicker = EmojiPickerPreference(requireActivity())
        emojiPicker.title = "Emoji Style"

        screen.addPreference(emojiPicker)

        preferenceScreen = screen
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {
        if (preference is EmojiPickerPreference<*>) {
            val callChangeListener = { value: String -> preference.callChangeListener(value) }
            val dialog = EmojiPickerDialogFragment.newInstance(preference.importer, callChangeListener = callChangeListener)
            dialog.setTargetFragment(this, 0)
            dialog.show(parentFragmentManager, "androidx.preference.PreferenceFragment.DIALOG")
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

}