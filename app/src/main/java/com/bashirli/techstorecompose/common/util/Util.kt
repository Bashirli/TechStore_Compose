package com.bashirli.techstorecompose.common.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.bashirli.techstorecompose.R
import okhttp3.ResponseBody
import org.json.JSONObject

val fontFamily = FontFamily(
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_regular, FontWeight.Normal),

    )

fun findExceptionMessage(errorBody: ResponseBody?): String {
    if (errorBody != null) {
        val errorObj = JSONObject(errorBody.charStream().readText())
        val errorMessage = errorObj.getString("status_message")
        return errorMessage
    } else {
        return "Error"
    }
}