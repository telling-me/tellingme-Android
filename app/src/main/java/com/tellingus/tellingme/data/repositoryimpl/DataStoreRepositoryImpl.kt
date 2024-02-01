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
            it[KEY_USER_SOCIAL_ID] = socialId
        }
    }

    override suspend fun getUserSocialId(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map {
                it[KEY_USER_SOCIAL_ID] ?: ""
            }
    }

    companion object {
        val KEY_USER_SOCIAL_ID = stringPreferencesKey("key_user_social_id")

    }
}
