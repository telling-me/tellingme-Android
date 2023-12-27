package com.tellingus.tellingme.presentation.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        Text(text = "텔링미", style = TellingmeTheme.typography.display1Bold)
        Text(text = "텔링미", style = TellingmeTheme.typography.display2Bold)
        Text(text = "텔링미", style = TellingmeTheme.typography.head1Bold)
        Text(text = "텔링미", style = TellingmeTheme.typography.head1Regular)
        Text(text = "텔링미", style = TellingmeTheme.typography.head3Bold)
        Text(text = "텔링미", style = TellingmeTheme.typography.head3Regular)
        Text(text = "텔링미", style = TellingmeTheme.typography.body1Bold)
        Text(text = "텔링미", style = TellingmeTheme.typography.body2Regular)
        Text(text = "텔링미", style = TellingmeTheme.typography.caption1Bold)
        Text(text = "텔링미", style = TellingmeTheme.typography.caption2Regular)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TellingmeTheme {
        Greeting()
    }
}