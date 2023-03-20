package com.emm.auth0app.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.End
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emm.auth0app.R
import com.emm.auth0app.presentation.composeutils.back
import com.emm.auth0app.presentation.composeutils.log
import com.emm.auth0app.presentation.theme.backgroundColor

private const val EXAMPLE_URL = "https://wallpaperaccess.com/full/1452310.jpg"

@Composable
fun ContactsScreen() {

    val maxWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(15.dp)
    ) {
        UserHeader()
        SearchComponent()
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Chatrooms", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                state = rememberLazyListState(),
                modifier = Modifier.requiredWidth(maxWidth),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 15.dp)
            ) {
                items((0..20).toList()) {
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        CoilImage(
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                                    ),
                                )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(7.dp)
                        ) {
                            Text(
                                text = "Choa Parker",
                                fontSize = 17.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "x",
                                tint = Color.White,
                                modifier = Modifier
                                    .align(Alignment.End)
                            )
                        }

                    }

                }
            }
        }
    }
}

@Composable
private fun SearchComponent() {

    val (text, setText) = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = setText,
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(text = "Search ...", color = Color.LightGray)
            },
            label = {
                Text(text = "Search ...", color = Color.LightGray)
            },
            textStyle = TextStyle(
                color = Color.White
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .requiredSize(45.dp)
                .clip(RoundedCornerShape(10))
                .background(Color(0xFF03A9F1))
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(bounded = false),
                    onClick = {}
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "X",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun UserHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CoilImage(
            Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = "Martina White",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun CoilImage(
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(EXAMPLE_URL)
            .error(R.drawable.ic_launcher_background)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}