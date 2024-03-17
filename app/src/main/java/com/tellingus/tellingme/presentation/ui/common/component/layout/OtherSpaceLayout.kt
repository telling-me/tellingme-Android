package com.tellingus.tellingme.presentation.ui.common.component.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Background100

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtherSpaceLayout(
    header: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
    background: Color = Background100,
    isScrollable: Boolean = true
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = background,
        topBar = { header?.let { header() } },
        content = {
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
                    .let {
                        if (isScrollable) {
                            it.verticalScroll(
                                rememberScrollState()
                            )
                        } else {
                            it
                        }
                    }
            ) {
                content()
            }
        }
    )
}