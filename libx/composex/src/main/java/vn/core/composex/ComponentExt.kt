package vn.core.composex

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal const val DOB_FORMAT = "dd/MM/yyyy"
internal const val EMPTY_STRING = ""

fun Long?.convertMillisToDate(): String {
    if (this == null) return EMPTY_STRING
    val formatter = SimpleDateFormat(DOB_FORMAT, Locale.getDefault())
    return formatter.format(Date(this))
}
