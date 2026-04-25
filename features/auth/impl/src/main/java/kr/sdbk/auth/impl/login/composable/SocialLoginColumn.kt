package kr.sdbk.auth.impl.login.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kr.sdbk.domain.model.user_auth.SocialType

@Composable
internal fun SocialLoginColumn(
    onClickSocialLogin: (SocialType) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp)
    ) {
        SocialType.entries.forEach {
            SocialLoginButton(it, onClickSocialLogin)
        }
    }
}

@Composable
private fun SocialLoginButton(
    socialType: SocialType,
    onClick: (SocialType) -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        onClick = { onClick(socialType) },
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ) {
        Text(
            text = socialType.name
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SocialLoginColumn{}
}

@Preview
@Composable
private fun PreviewButton(
    @PreviewParameter(PreviewProvider::class) socialType: SocialType
) {
    SocialLoginButton(socialType) { }
}

private class PreviewProvider: PreviewParameterProvider<SocialType> {
    override val values: Sequence<SocialType> = sequence {
        SocialType.entries
    }
}