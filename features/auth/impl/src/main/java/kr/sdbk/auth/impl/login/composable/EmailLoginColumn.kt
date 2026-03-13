package kr.sdbk.auth.impl.login.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.sdbk.designsystem.component.VerticalSpacer

@Composable
internal fun EmailLoginColumn(
    email: String,
    password: String,
    onInputEmail: (String) -> Unit,
    onInputPassword: (String) -> Unit,
    onClickLogin: () -> Unit,
    onClickSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        TextField(
            value = email,
            onValueChange = onInputEmail,
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(16.dp)

        TextField(
            value = password,
            onValueChange = onInputPassword,
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(24.dp)

        Row {
            Button(
                onClick = onClickLogin,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(
                    text = "로그인"
                )
            }

            Button(
                onClick = onClickSignUp,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(
                    text = "회원가입"
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    EmailLoginColumn("", "", {}, {}, {}, {})
}