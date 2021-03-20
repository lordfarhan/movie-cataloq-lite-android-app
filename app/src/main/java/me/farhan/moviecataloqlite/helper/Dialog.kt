package me.farhan.moviecataloqlite.helper

import android.content.Context
import com.crowdfire.cfalertdialog.CFAlertDialog
import me.farhan.moviecataloqlite.R

/**
 * @author farhan
 * created at at 9:18 on 03/03/21.
 */
fun successDialog(context: Context): CFAlertDialog.Builder {
    return CFAlertDialog.Builder(context)
        .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
        .setTitle(context.resources.getString(R.string.success_title))
        .setMessage(context.resources.getString(R.string.success_message))
        .addButton(
            context.resources.getString(R.string.action_ok),
            1,
            1,
            CFAlertDialog.CFAlertActionStyle.POSITIVE,
            CFAlertDialog.CFAlertActionAlignment.JUSTIFIED
        ) { dialog, which ->
            dialog.dismiss()
        }
}

fun errorDialog(context: Context, message: String? = ""): CFAlertDialog.Builder {
    return CFAlertDialog.Builder(context)
        .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
        .setTitle(context.resources.getString(R.string.error_title))
        .setMessage(String.format(context.resources.getString(R.string.error_message), message))
        .addButton(
            context.resources.getString(R.string.action_try_again),
            1,
            1,
            CFAlertDialog.CFAlertActionStyle.NEGATIVE,
            CFAlertDialog.CFAlertActionAlignment.JUSTIFIED
        ) { dialog, which ->
            dialog.dismiss()
        }
}