package com.tellingus.tellingme.presentation.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.presentation.ui.common.button.BUTTON_SIZE
import com.tellingus.tellingme.presentation.ui.common.button.FloatingButton
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.button.PrimaryLightButton
import com.tellingus.tellingme.presentation.ui.common.button.SecondaryButton
import com.tellingus.tellingme.presentation.ui.common.button.SingleBlackButton
import com.tellingus.tellingme.presentation.ui.common.button.SingleButton
import com.tellingus.tellingme.presentation.ui.common.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TellingmeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            size = BUTTON_SIZE.LARGE,
            text = "버튼버튼",
            onClick = { }
        )
        PrimaryLightButton(
            size = BUTTON_SIZE.LARGE,
            text = "버튼버튼",
            onClick = { }
        )
        SecondaryButton(
            size = BUTTON_SIZE.LARGE,
            text = "버튼버튼",
            onClick = { }
        )
        SingleButton(
            size = BUTTON_SIZE.LARGE,
            text = "버튼버튼",
            onClick = { }
        )
        SingleBlackButton(
            size = BUTTON_SIZE.LARGE,
            text = "버튼버튼",
            onClick = { }
        )
        SingleBlackButton(
            size = BUTTON_SIZE.LARGE,
            text = "버튼버튼",
            onClick = { }
        )
        FloatingButton {

        }
        TellingmeIconButton {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TellingmeTheme {
        Greeting()
    }
}