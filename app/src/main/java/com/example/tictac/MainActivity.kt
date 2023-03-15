package com.example.tictac

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictac.ui.theme.TictacTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TictacTheme {
                Scaffold(topBar = {
                    TicTacAppBar(mainViewModel.singlePlayer) {
                        mainViewModel.updatePlayerMode(it)
                    }
                }) {
                    Surface(color = MaterialTheme.colors.background) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            ButtonGrid(board = mainViewModel.board, mainViewModel::play)

                            if (mainViewModel.isGameOver) {
                                Box {
                                    Text(
                                        text = "Game is Over: ${mainViewModel.winner}",
                                        fontSize = 20.sp
                                    )
                                }
                            }

                            ResetButton(onClick = mainViewModel::reset)

                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TictacTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ButtonGrid(board = arrayListOf("X", "O", "X", "O", "O", "X", "", "X", "O")) {}
            ResetButton {}
        }
    }
}

