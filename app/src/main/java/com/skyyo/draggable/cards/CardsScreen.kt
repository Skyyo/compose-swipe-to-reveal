package com.skyyo.draggable.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skyyo.draggable.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi


const val ACTION_ITEM_SIZE = 56
const val CARD_HEIGHT = 56
const val CARD_OFFSET = 168f // we have 3 icons in a row, so that's 56 * 3

@ExperimentalCoroutinesApi
@Composable
fun CardsScreen(viewModel: CardsScreenViewModel) {
    val cards = viewModel.cards.collectAsState()
    val revealedCardIds = viewModel.revealedCardIdsList.collectAsState()
    Scaffold(backgroundColor = Color.White) {
        LazyColumn {
            itemsIndexed(cards.value) { _, card ->
                Box(Modifier.fillMaxWidth()) {
                    ActionsRow(
                        actionIconSize = ACTION_ITEM_SIZE.dp,
                        onDelete = {},
                        onEdit = {},
                        onFavorite = {}
                    )
                    DraggableCard(
                        card = card,
                        isRevealed = revealedCardIds.value.contains(card.id),
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


