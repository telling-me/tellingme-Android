package com.tellingus.tellingme.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

/** 생명주기에 따라 Flow를 수집할 수 있도록 하는 함수 **/
@Composable
inline fun <reified T> Flow<T>.collectWithLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            this@collectWithLifecycle.collectLatest { action(it) }
        }
    }
}