package com.example.constant


object GameUtils {
    const val PLAYER_X = "X"
    const val PLAYER_0 = "0"
    fun isBoardFull(board: ArrayList<String>): Boolean{
        for (i in board){
            if (i != PLAYER_X && i != PLAYER_0)
                return false
        }
    return true
    }
    fun copyBoard(board: ArrayList<String>): ArrayList<String>{
        val newBoard = arrayListOf("", "", "", "", "", "", "", "", "")
        for (i in 0 until board.count()){
            newBoard[i] = board[i]
        }
    return newBoard
    }
    fun chooseRandomMove(board: ArrayList<String>, moves: ArrayList<Int>): Int{
        val possibleMoves = arrayListOf<Int>()
    for (i in moves){
        if (board[i] == "") possibleMoves.add(i)
    }
    return if (possibleMoves.isEmpty()){
        -1
    }else{
        val index = java.util.Random().nextInt(possibleMoves.count())
        possibleMoves[index]
    }
    }
    fun computerMove(board: ArrayList<String>): Int{
        for (i in 0 until board.count()){
            val copy = copyBoard(board)
            if (copy[i] == "") copy[i] = PLAYER_0
            if (isGameWon(copy, PLAYER_X)) return i
        }
        val move = chooseRandomMove(board, arrayListOf(0,2,6,8))
        if (move != -1) return move
        if (board[4] == "") return 4
        return chooseRandomMove(board, arrayListOf(1,3,5,7))
    }

    fun isGameWon(board: ArrayList<String>, player: String): Boolean =
        if (board[0] == player && board[1] == player && board[2] == player) true
        else if (board[3] == player && board[4] == player && board[5] == player) true
        else if (board[6] == player && board[7] == player && board[8] == player) true

        else if (board[0] == player && board[3] == player && board[6] == player) true
        else if (board[1] == player && board[4] == player && board[7] == player) true
        else if (board[2] == player && board[5] == player && board[8] == player) true

        else if (board[2] == player && board[4] == player && board[6] == player) true
        else board[0] == player && board[4] == player && board[8] == player

    fun gameResult(board: ArrayList<String>, singleMode: Boolean): String{
        when{
            isGameWon(board, PLAYER_X) -> return "${if (singleMode) "YOU" else "PLAYER X"} Won"
            isGameWon(board, PLAYER_0) -> return "${if (singleMode) "COMPUTER" else "PLAYER O"} Won"
            isBoardFull(board) -> return "It is Tie"        }
        return "Tie"
    }
}