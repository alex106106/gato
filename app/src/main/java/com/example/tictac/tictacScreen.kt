package com.example.tictac

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacAppBar(singlePlayer: Boolean, onCheckedChange: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(singlePlayer) }
    TopAppBar(
        title = { Text(text = "Tic Tac Toe", color = Color.White) },
        actions = {
            Row(modifier = Modifier.padding(end = 16.dp)) {
                Text(text = if (checkedState.value) "Single Player" else "Multi Player")
                Spacer(modifier = Modifier.width(16.dp))
                Switch(checked = checkedState.value, onCheckedChange = {
                    checkedState.value = it
                    onCheckedChange(it)
                })
            }
        }
    )
}

@Composable
fun ResetButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(16.dp).height(50.dp)) {
        Text(
            text = "Restart",
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ButtonGrid(board: ArrayList<String>, onclick: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            TicTacToeButton(text = board[0]) { onclick(0) }
            TicTacToeButton(text = board[1]) { onclick(1) }
            TicTacToeButton(text = board[2]) { onclick(2) }
        }

        Row(horizontalArrangement = Arrangement.SpaceAround) {
            TicTacToeButton(text = board[3]) { onclick(3) }
            TicTacToeButton(text = board[4]) { onclick(4) }
            TicTacToeButton(text = board[5]) { onclick(5) }
        }
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            TicTacToeButton(text = board[6]) { onclick(6) }
            TicTacToeButton(text = board[7]) { onclick(7) }
            TicTacToeButton(text = board[8]) { onclick(8) }
        }
    }
}

@Composable
fun TicTacToeButton(text: String, onclick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        TextButton(
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, color = Color.Blue),
            onClick = onclick,
            enabled = text.isBlank()
        ) {
            Text(
                text = text,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                ),
                modifier = Modifier.padding(16.dp).size(40.dp).fillMaxHeight()
            )
        }
    }
}