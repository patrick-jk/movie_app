package com.tokenlab.challenge.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.tokenlab.challenge.R

fun Context.createProgressDialog(): AlertDialog {
    val padding = resources.getDimensionPixelOffset(R.dimen.padding)
    val progressBar = ProgressBar(this).apply {
        setPadding(padding, padding, padding, padding)
    }
    return MaterialAlertDialogBuilder(this).setView(progressBar)
        .setCancelable(false).create()
}

fun Context.createErrorDialog(message: String, onClick: () -> Unit) {
    AlertDialog.Builder(this).setMessage(message)
        .setPositiveButton(android.R.string.ok) { _, _ -> onClick() }
        .create()
        .show()
}

fun Context.createErrorSnackbar(
    message: String,
    view: View,
    fullErrorMessage: String?,
    onClick: () -> Unit
): Snackbar {
    return Snackbar.make(this, view, message, Snackbar.LENGTH_LONG)
        .setAction(R.string.txt_error_see_details) {
            this.createErrorDialog(fullErrorMessage ?: this.getString(R.string.error_no_details)) {
                onClick()
            }
        }
}