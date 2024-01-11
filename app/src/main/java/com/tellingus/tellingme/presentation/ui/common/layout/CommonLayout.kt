package com.tellingus.tellingme.presentation.ui.common.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(header: @Composable (() -> Unit)? = null, content: @Composable () -> Unit) {
    Scaffold(
        topBar = { header?.let { header() } },
        content = {
            Box(
                modifier = Modifier
                    .padding(top = if (header != null) 48.dp else 0.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                content()
            }
        }
    )
}