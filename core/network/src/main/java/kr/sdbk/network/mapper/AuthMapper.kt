package kr.sdbk.network.mapper

import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.domain.model.user_data.User
import kr.sdbk.network.model.user_auth.AuthTokenDTO
import kr.sdbk.network.model.user_auth.UserDTO

object AuthMapper {
    fun AuthTokenDTO.toDomain() = AuthToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expireAt = System.currentTimeMillis() + expiresIn * 1000,
        tokenType = tokenType
    )

    fun UserDTO.toDomain() = User(
        channelId = channelId,
        channelName = channelName
    )
}