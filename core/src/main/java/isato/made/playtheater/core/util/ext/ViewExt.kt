package isato.made.playtheater.core.util.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbarNotice(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}