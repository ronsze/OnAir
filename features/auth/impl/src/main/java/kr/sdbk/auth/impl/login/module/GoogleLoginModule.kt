package kr.sdbk.auth.impl.login.module

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kr.sdbk.auth.impl.BuildConfig
import java.security.SecureRandom
import java.util.Base64

class GoogleLoginModule : LoginModule {
    override suspend fun proceed(context: Context): Result<String> =
        googleLoginWithCredentialManager(context)

    private suspend fun googleLoginWithCredentialManager(context: Context): Result<String> {
        val signInWithGoogleOption = GetSignInWithGoogleOption
            .Builder(serverClientId = BuildConfig.googleWebClientId)
            .setNonce(generateSecureRandomNonce())
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        return signInWithGoogle(request, context)
    }

    private suspend fun signInWithGoogle(request: GetCredentialRequest, context: Context): Result<String> {
        val credentialManager = CredentialManager.create(context)

        return runCatching {
            val credential = credentialManager.getCredential(context, request).credential
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                GoogleIdTokenCredential.createFrom(credential.data).idToken
            } else {
                throw GoogleIdTokenParsingException()
            }
        }
    }

    fun generateSecureRandomNonce(byteLength: Int = 32): String {
        val randomBytes = ByteArray(byteLength)
        SecureRandom.getInstanceStrong().nextBytes(randomBytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes)
    }
}