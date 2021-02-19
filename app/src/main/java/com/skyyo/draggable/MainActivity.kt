package com.skyyo.draggable

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.skyyo.draggable.cards.CardsScreen
import com.skyyo.draggable.cards.CardsScreenViewModel

import com.skyyo.draggable.theme.theme.AppTheme

class MainActivity : AppCompatActivity() {
    private val cardsViewModel by viewModels<CardsScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CardsScreen(cardsViewModel)
                }
            }
        }
    }
}

