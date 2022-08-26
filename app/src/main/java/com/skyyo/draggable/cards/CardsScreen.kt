package com.skyyo.draggable.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skyyo.draggable.CardModel
import com.skyyo.draggable.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val ACTION_ITEM_SIZE = 56
const val CARD_HEIGHT = 56
const val CARD_OFFSET = 168f // we have 3 icons in a row, so that's 56 * 3

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalCoroutinesApi
@Composable
fun CardsScreen(viewModel: CardsScreenViewModel) {
    val cards by viewModel.cards.collectAsStateWithLifecycle()
    val revealedCardIds by viewModel.revealedCardIdsList.collectAsStateWithLifecycle()

    Scaffold(backgroundColor = Color.White) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(cards, CardModel::id) { card ->
                Box(Modifier.fillMaxWidth()) {
                    ActionsRow(
                        actionIconSize = ACTION_ITEM_SIZE.dp,
                        onDelete = {},
                        onEdit = {},
                        onFavorite = {}
                    )
                    //for advanced cases use DraggableCardComplex
                    DraggableCard(
                        card = card,
                        isRevealed = revealedCardIds.contains(card.id),
                        cardHeight = CARD_HEIGHT.dp,
                        cardOffset = CARD_OFFSET.dp(),
                        onExpand = { viewModel.onItemExpanded(card.id) },
                        onCollapse = { viewModel.onItemCollapsed(card.id) },
                    )
                }
            }
        }
    }
}


