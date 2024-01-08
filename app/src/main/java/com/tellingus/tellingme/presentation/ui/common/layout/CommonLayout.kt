package com.tellingus.tellingme.presentation.ui.common.layout

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.tellingus.tellingme.presentation.ui.common.header.Header

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(header: @Composable () -> Unit = { Header() }, content: @Composable () -> Unit) {
    Scaffold(topBar = { header() }, content = { content() })
}