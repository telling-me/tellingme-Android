package com.tellingus.tellingme.data.repositoryimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): DataStoreRepository {
    override suspend fun setUserSocialId(socialId: String) {
        dataStore.edit {
            it[USER_SOCIAL_ID] = socialId
        }
    }

    override suspend fun getUserSocialId(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map {
                it[USER_SOCIAL_ID] ?: ""
            }
    }

    override suspend fun setJwtTokens(accessToken: String, refreshToken: String) {
        dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun getAccessToken(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map {
                it[ACCESS_TOKEN] ?: ""
            }
    }

    override suspend fun getRefreshToken(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map {
                it[REFRESH_TOKEN] ?: ""
            }
    }

    override suspend fun deleteTokens() {
        dataStore.edit {
            it.remove(ACCESS_TOKEN)
            it.remove(REFRESH_TOKEN)
        }
    }

    companion object {
        val USER_SOCIAL_ID = stringPreferencesKey("user_social_id")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}
