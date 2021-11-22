package isato.made.playtheater.util.ext

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import isato.made.playtheater.ui.MainActivity

fun Fragment.setupActionBar(toolbar: Toolbar) {
    activity?.let { activity ->
        when (activity) {
            is MainActivity -> activity.setupActionBar(toolbar)
            else -> throw Throwable("Unknown Activity class: ${activity.javaClass.simpleName}")
        }
    }
}