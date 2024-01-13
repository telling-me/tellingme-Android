package com.tellingus.tellingme.presentation.ui.common.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Background100

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    header: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
    background: Color = Background100,
) {
    Scaffold(
        modifier = Modifier.background(background),
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