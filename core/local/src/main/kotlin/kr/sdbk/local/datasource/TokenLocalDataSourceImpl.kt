package kr.sdbk.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kr.sdbk.data.datasource.local.TokenLocalDataSource
import kr.sdbk.domain.model.user_auth.AuthToken

class TokenLocalDataSourceImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : TokenLocalDataSource {
    companion object {
        private const val FILE_NAME = "auth"
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = FILE_NAME,
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() }
    )

    override suspend fun setToken(token: AuthToken) {
        context.dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[AUTH_TOKEN_KEY] = Json.encodeToString(token)
            }
        }
    }

    override suspend fun getToken(): AuthToken? = context.dataStore.data.map { preferences ->
        preferences[AUTH_TOKEN_KEY]?.let { Json.decodeFromString<AuthToken>(it) }
    }.firstOrNull()

    override suspend fun clearToken() {
        context.dataStore.updateData { emptyPreferences() }
    }
}