/*
 * PlayTheater.core
 * ViewExt.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.util.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbarNotice(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}