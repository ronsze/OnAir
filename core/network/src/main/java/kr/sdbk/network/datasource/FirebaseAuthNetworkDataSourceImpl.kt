package kr.sdbk.network.datasource

import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.domain.model.SocialType
import kr.sdbk.domain.model.User
import kr.sdbk.network.api.AuthApi
import kr.sdbk.network.di.ChzzkApi
import kr.sdbk.network.mapper.AuthMapper.toDomain
import kr.sdbk.network.model.RequestCustomTokenBody
import retrofit2.Retrofit

internal class FirebaseAuthNetworkDataSourceImpl @Inject constructor(
    @ChzzkApi retrofit: Retrofit
) : AuthNetworkDataSource {
    private val api = retrofit.create(AuthApi::class.java)

    private val auth = Firebase.auth

    override fun getCurrentUser(): User? = auth.currentUser?.toDomain()

    override suspend fun loginWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun loginWithSocial(token: String, socialType: SocialType) {
        when (socialType) {
            SocialType.GOOGLE -> loginWithCredential(token)
            else -> loginWithCustomToken(token, socialType)
        }
    }

    private suspend fun loginWithCredential(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).await()
    }

    private suspend fun loginWithCustomToken(accessToken: String, socialType: SocialType) {
        val requestBody = RequestCustomTokenBody(accessToken, socialType.name.lowercase())
        val customToken = api.getFirebaseCustomToken(requestBody).token
        auth.signInWithCustomToken(customToken).await()
    }

    override suspend fun signUpWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun deleteAccount() {
        val currentUser = auth.currentUser!!
        currentUser.delete()
    }
}