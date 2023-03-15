package com.example.tictac

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.constant.GameUtils
import com.example.constant.GameUtils.PLAYER_0
import com.example.constant.GameUtils.PLAYER_X
import com.example.constant.GameUtils.isBoardFull
import com.example.constant.GameUtils.isGameWon

class MainViewModel : ViewModel() {

    var singlePlayer by mutableStateOf(true)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var winner by mutableStateOf("")
        private set

    var board by mutableStateOf(arrayListOf("", "", "", "", "", "", "", "", ""))
        private set

    private var currentPlayer = PLAYER_X

    fun play(move: Int) {
        if (isGameOver) return

        if (board[move] == "") {
            if (currentPlayer == PLAYER_X) {
                board = ArrayList(board.toMutableList().also {
                    it[move] = PLAYER_X
                })
                currentPlayer = PLAYER_0

                if (singlePlayer) {
                    if (!isBoardFull(board) && !isGameWon(board, PLAYER_X)) {
                        val nextMove = GameUtils.computerMove(board)

                        board = ArrayList(board.toMutableList().also {
                            it[nextMove] = PLAYER_0
                        })
                    }
                    currentPlayer = PLAYER_X
                }

            } else {
                board = ArrayList(board.toMutableList().also {
                    it[move] = PLAYER_0
                })
                currentPlayer = PLAYER_X

                if (singlePlayer) {
                    if (!isBoardFull(board) && !isGameWon(board, PLAYER_0)) {
                        val nextMove = GameUtils.computerMove(board)

                        board = ArrayList(board.toMutableList().also {
                            it[nextMove] = PLAYER_X
                        })
                    }
                    currentPlayer = PLAYER_0
                }
            }
        }


        isGameOver = isGameWon(board, PLAYER_X) || isGameWon(board, PLAYER_0) || isBoardFull(board)
        winner = GameUtils.gameResult(board, singlePlayer)

        Log.d(TAG, "$board")
    }

    fun reset() {
        isGameOver = false
        board = arrayListOf("", "", "", "", "", "", "", "", "")
    }

    fun updatePlayerMode(singlePlayer: Boolean) {
        reset()
        this.singlePlayer = singlePlayer
    }


    companion object {
        const val TAG = "MainViewModel"
    }
}