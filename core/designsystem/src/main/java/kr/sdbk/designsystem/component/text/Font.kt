package kr.sdbk.designsystem.component.text

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kr.sdbk.designsystem.R

object Font {
    val robotoFont = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_semi_bold, FontWeight.SemiBold),
        Font(R.font.roboto_extra_bold, FontWeight.ExtraBold),
        Font(R.font.roboto_black, FontWeight.Black)
    )
}