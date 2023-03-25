package com.emm.auth0app.presentation.composeutils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.log(color: Color = Color.Red, width: Dp = 1.dp) = then(Modifier.border(width = width, color = color))
fun Modifier.back(color: Color = Color.Red) = then(Modifier.background(color))