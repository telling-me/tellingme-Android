package com.tellingus.tellingme.presentation.ui.feature

import androidx.lifecycle.ViewModel
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import com.tellingus.tellingme.presentation.ui.feature.TellingMeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class TellingMeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {



}