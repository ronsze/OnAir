package kr.sdbk.network.consts

object APIHeader {
    const val ACCESS_TOKEN_REQUIRED = "access_token_required"
    const val ACCESS_TOKEN_HEADER = "$ACCESS_TOKEN_REQUIRED:true"

    const val AUTHORIZATION = "Authorization"
    const val CLIENT_ID = "Client-Id"
    const val CLIENT_SECRET = "Client-Secret"
}