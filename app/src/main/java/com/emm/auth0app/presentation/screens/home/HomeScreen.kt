package com.emm.auth0app.presentation.screens.home

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emm.auth0app.R
import com.emm.auth0app.presentation.composeutils.UseEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = koinViewModel()
) {

    val state = vm.homeState.collectAsState()

    HomeScreen(
        state = state.value,
        logout = vm::logout,
        toLoginNavigate = {
            navController.navigate("login") {
                popUpTo("home") {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    logout: (Context) -> Unit,
    toLoginNavigate: () -> Unit
) {

    val context = LocalContext.current

    UseEffect(state) {
        when (state) {
            HomeState.Logout -> {
                toLoginNavigate.invoke()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home")
        if (state is HomeState.Success) {
            Text(text = state.email)
            Text(text = state.name)
            Text(text = state.familyName)
            Text(text = state.nickname)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.urlImg)
                    .error(R.drawable.ic_launcher_background)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .widthIn(50.dp)
                    .clip(RoundedCornerShape(50)),
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { logout(context) }) {
                Text(text = "LOGOUT")
            }
            Button(onClick = { }) {
                Text(text = "MODIFY")
            }
        }
        if (state is HomeState.Loading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun debugPlaceholder(@DrawableRes debugPreview: Int): Painter? {
    return if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState.Success(),
        logout = {},
        toLoginNavigate = {}
    )
}