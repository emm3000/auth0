package com.emm.auth0app.presentation.composeutils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectScope

@Composable
fun UseEffect(
    vararg keys: Any?,
    effect: DisposableEffectScope.() -> Unit
) {
    DisposableEffect(*keys) {
        effect()
        onDispose { }
    }
}