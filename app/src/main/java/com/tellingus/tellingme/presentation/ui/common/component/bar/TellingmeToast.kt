package com.tellingus.tellingme.presentation.ui.common.component.bar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

class TellingmeToast(context: Context) : Toast(context) {
    @Composable
    fun showToast(
        modifier: Modifier = Modifier,
        text: String,
        icon: Int,
    ) {
        val context = LocalContext.current
        val density = LocalDensity.current
        val views = ComposeView(context)

        views.setContent {
            Box(
                modifier = modifier.padding(20.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xE65E718D)
                        )
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(icon),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = text,
                        style = TellingmeTheme.typography.body2Bold.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }

        views.setViewTreeLifecycleOwner(LocalLifecycleOwner.current)
        views.setViewTreeSavedStateRegistryOwner(LocalSavedStateRegistryOwner.current)
        views.setViewTreeViewModelStoreOwner(LocalViewModelStoreOwner.current)

        this.apply {
            view = views
            duration = LENGTH_SHORT
            setGravity(
                android.view.Gravity.FILL_HORIZONTAL or android.view.Gravity.BOTTOM,
                0,
                with(density) { 60.dp.toPx().toInt() }
            )
        }

        this.show()
    }
}
