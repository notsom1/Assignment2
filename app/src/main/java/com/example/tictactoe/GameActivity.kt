package com.example.tictactoe

import android.content.DialogInterface
import android.graphics.Color
import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.core.view.iterator
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    var oTurn = true; //true = O and false = X
    val oColor = Color.BLUE
    val xColor = Color.RED
    val neutralColor = 0xCACACA
    var remainingSquares = 9
    val squares = ArrayList<Button>()
    var playerName = ""
    var playerColor = 0
    var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        cacheButtons()
        restartGame()
    }

    fun cacheButtons()
    {
        for (row in GameBoard)
        {
            val r = row as TableRow
            for (button in r)
            {
                val b = button as Button
                squares.add(b)
            }
        }
    }

    fun onClick(v: View)
    {
        val myButton = v as Button

        if (v.text == "")
        {
            if (oTurn)
            {
                myButton.text = "O"
                myButton.setBackgroundColor(oColor)
            }
            else
            {
                myButton.text = "X"
                myButton.setBackgroundColor(xColor)
            }

            remainingSquares--
            myButton.isClickable = false

            checkWinner()

            if (!gameOver)
            {
                changePlayer()
            }

        }

    }

    fun changePlayer()
    {
        oTurn = !oTurn

        updateNameAndColor()
        tv_Info.text = "Player $playerName turn"
        tv_Info.setTextColor(playerColor)
    }

    fun checkWinner()
    {
        var foundWinner = false

        // Check 012 345 678
        for (i in 0..6 step 3)
        {
            if (squares[i].text == squares[i + 1].text && squares[i + 1].text == squares[i + 2].text && squares[i].text != "")
            {
                foundWinner = true
                break
            }
        }

        if (!foundWinner)
        {
            // Check 036 147 258
            for (i in 0..2)
            {
                if (squares[i].text == squares[i + 3].text && squares[i + 3].text == squares[i + 6].text && squares[i].text != "")
                {
                    foundWinner = true
                    break
                }
            }
        }

        if (!foundWinner)
        {
            // Check 048
            if (squares[0].text == squares[4].text && squares[4].text == squares[8].text && squares[0].text != "")
            {
                foundWinner = true
            }

            // Check 246
            else if (squares[2].text == squares[4].text && squares[4].text == squares[6].text && squares[2].text != "")
            {
                foundWinner = true
            }
        }

        if (remainingSquares == 0 || foundWinner)
        {
            gameOver = true
        }

        if (gameOver)
        {
            endGame(foundWinner)
        }

    }

    fun updateNameAndColor()
    {
        playerName = if (oTurn) "O" else "X"
        playerColor = if (oTurn) oColor else xColor
    }

    fun endGame(foundWinner:Boolean)
    {
        for (square in squares)
        {
            square.isClickable = false
        }

        if (foundWinner)
        {
            tv_Info.text = "Congratulations!\nPlayer \"" + playerName + "\" wins!"
        }
        else
        {
            tv_Info.text = "Draw!\nClick Restart to play again"
        }
    }

    fun onRestart(v:View)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quit")
        builder.setMessage("Restart game?")
        builder.setIcon(R.drawable.ic_launcher_background)

        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            restartGame()
        })

        builder.setNeutralButton("No", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        builder.create().show()
    }

    fun restartGame()
    {
        for (square in squares)
        {
            square.setBackgroundColor(neutralColor)
            square.text = ""
            square.isClickable = true
        }

        remainingSquares = 9
        gameOver = false
        oTurn = true
        changePlayer()
    }

    fun onQuit (v:View)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quit")
        builder.setMessage("Leave session?")
        builder.setIcon(R.drawable.ic_launcher_background)

        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            finish()
        })

        builder.setNeutralButton("No", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        builder.create().show()
    }
}