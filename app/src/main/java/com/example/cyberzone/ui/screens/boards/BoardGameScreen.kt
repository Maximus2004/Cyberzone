package com.example.cyberzone.ui.screens.boards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.cyberzone.R
import com.example.cyberzone.data.BoardGameData
import com.example.cyberzone.ui.screens.home.CurrentLoadingState
import com.example.cyberzone.ui.screens.home.HomeEvent
import com.example.cyberzone.ui.screens.home.HomeUiState
import com.example.cyberzone.ui.theme.CyberzoneTheme
import com.example.cyberzone.ui.theme.WhiteAlpha
import java.time.LocalDateTime

object BoardGameScreen {
    const val route = "BoardGameScreen"
}

@Composable
fun BoardGameScreen(
    currentLoadingState: CurrentLoadingState,
    onHomeEvent: (HomeEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 27.dp)
        ) {
            item {
                when (currentLoadingState) {
                    is CurrentLoadingState.Fail -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.game_not_found),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                    is CurrentLoadingState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is CurrentLoadingState.Success -> {
                        SearchField(modifier = Modifier.padding(vertical = 25.dp))
                        Image(
                            painterResource(id = R.drawable.uno_example),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                                .heightIn(max = 411.dp)
                        )
                        Row(modifier = Modifier.padding(vertical = 24.dp)) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceVariant,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            ) {
                                Text(
                                    text = "УНО-лайк",
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp,
                                        vertical = 4.dp
                                    ),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Box(
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(5.dp)
                                )
                            ) {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp,
                                        vertical = 4.dp
                                    )
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.people),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                    Text(
                                        text = "2-10",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Text(
                            text = currentLoadingState.homeUiState.currentBoard!!.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.scrim
                        )
                        Text(
                            text = "UNO обещает массу увлекательных моментов и неожиданных поворотов. Карты с функциями, такими как \"Плюс 4\", \"Смена цвета\" или \"Пропуск хода\", добавляют в игру элемент стратегии и влияют на действия игроков. Также в игре есть особая карта - \"UNO\", которая должна быть объявлена, когда остается всего одна карта в руке, иначе игрок наказывается.",
                            style = MaterialTheme.typography.displayMedium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(bottom = 140.dp)
                        )
                    }
                }
            }
        }
        if (currentLoadingState is CurrentLoadingState.Success) {
            Column(modifier = Modifier.padding(bottom = 30.dp)) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .heightIn(max = 50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp)
                ) {
                    Text(
                        text = stringResource(R.string.to_book),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.background
                    )
                }
                Button(
                    onClick = {
                        onHomeEvent(HomeEvent.OnTakeBoardGame(BoardGameData(currentLoadingState.homeUiState.currentBoard!!.id)))
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .heightIn(max = 50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp)
                ) {
                    Text(
                        text = stringResource(R.string.to_take),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun SearchField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 50.dp)
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 2.dp,
                brush = SolidColor(MaterialTheme.colorScheme.secondaryContainer)
            ),
        trailingIcon = {
            Box(
                modifier = Modifier
                    .size(height = 50.dp, width = 54.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.heightIn(max = 50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sorting),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.heightIn(max = 50.dp)) {
                Text(
                    text = stringResource(R.string.find_favourite_game),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                )
            }
        },
        singleLine = true
    )
}

@Preview
@Composable
fun PreviewBoardGameScreen() {
    CyberzoneTheme {
        BoardGameScreen(
            currentLoadingState = CurrentLoadingState.Success(
                HomeUiState(
                    currentBoard = BoardGame(
                        id = "sdcvfk-998sdn",
                        available = true,
                        createdAt = "",
                        updatedAt = "",
                        imageURI = "https://koshka.top/uploads/posts/2021-12/1640186628_26-koshka-top-p-izobrazhenie-leoparda-28.jpg",
                        leftCount = 2,
                        count = 5,
                        name = "Uno"
                    )
                )
            ),
            onHomeEvent = {}
        )
    }
}