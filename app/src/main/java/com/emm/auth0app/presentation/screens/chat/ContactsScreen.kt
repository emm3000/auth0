package com.emm.auth0app.presentation.screens.chat

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.End
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emm.auth0app.R
import com.emm.auth0app.presentation.composeutils.back
import com.emm.auth0app.presentation.composeutils.log
import com.emm.auth0app.presentation.theme.backgroundColor
import com.google.android.material.appbar.CollapsingToolbarLayout

private const val EXAMPLE_URL = "https://wallpaperaccess.com/full/1452310.jpg"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier,
            ) {
                item {
                    UserHeader()
                }
                stickyHeader {
                    SearchComponent()
                }
                item {
                    Text(text = "Chatrooms", fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    CarrouselComponent()
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items((0..20).toList()) {
                    ChatItem()
                }

            }
        }
    }
}

@Composable
private fun ChatItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = Modifier
                .size(45.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (imageRef, badgeBox) = createRefs()

                CoilImage(
                    Modifier
                        .constrainAs(imageRef) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .size(45.dp)
                        .clip(RoundedCornerShape(50))
                )
                Box(
                    modifier = Modifier
                        .constrainAs(badgeBox) {
                            start.linkTo(imageRef.start, margin = (-10).dp)
                            top.linkTo(imageRef.top, margin = (-10).dp)
                        }
                        .padding(end = 20.dp)
                        .size(25.dp)
                        .clip(CircleShape)
                        .back(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "+99",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }

        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Maciej Kowalski Maciej KowalskiMaciej Kowalski Maciej Kowalski cqwc cqw c",
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "macieg.kowalski@email.com",
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
        ) {
            Text(text = "08:43", color = Color.White)
        }
    }
}

@Composable
private fun CarrouselComponent() {

    val maxWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyRow(
        state = rememberLazyListState(),
        modifier = Modifier.requiredWidth(maxWidth),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 15.dp)
    ) {
        items((0..20).toList()) {
            CarrouselItem()

        }
    }
}

@Composable
private fun CarrouselItem() {
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
            modifier = Modifier
                .fillMaxSize()
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

@Composable
private fun SearchComponent() {

    val (text, setText) = remember {
        mutableStateOf("")
    }

    val maxWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .requiredWidth(maxWidth)
            .background(backgroundColor)
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
                .size(45.dp)
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

//@Preview(showBackground = true)
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}

@Preview(showBackground = false)
@Composable
fun ChatItemPreview() {
    ChatItem()
}