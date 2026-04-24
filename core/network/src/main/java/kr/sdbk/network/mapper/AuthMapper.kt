package kr.sdbk.network.mapper

import com.google.firebase.auth.FirebaseUser
import kr.sdbk.domain.model.User

object AuthMapper {
    fun FirebaseUser.toDomain() = User(
        uid = uid,
        email = email
    )
}