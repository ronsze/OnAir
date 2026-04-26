package kr.sdbk.network.mapper

import com.google.firebase.auth.FirebaseUser
import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.domain.model.user_data.User
import kr.sdbk.network.model.user_auth.AuthTokenDTO

object AuthMapper {
    fun FirebaseUser.toDomain() = User(
        uid = uid,
        email = email
    )

    fun AuthTokenDTO.toDomain() = AuthToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expireAt = System.currentTimeMillis() + expiresIn * 1000,
        tokenType = tokenType
    )
}