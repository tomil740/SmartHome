package core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.compose.DarkColors
import com.example.compose.LightColors

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = MaterialTheme.typography,
        content = content
    )
}