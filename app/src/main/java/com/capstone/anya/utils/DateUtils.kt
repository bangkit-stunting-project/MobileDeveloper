package com.capstone.anya.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant.parse
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(currentDateString: String, targetTimeZone: String): String {
    val instant = parse(currentDateString)
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        .withZone(ZoneId.of(targetTimeZone))
    return formatter.format(instant)
}