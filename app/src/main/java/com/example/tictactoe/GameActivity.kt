package com.example.tictactoe

import android.content.DialogInterface
import android.graphics.Color
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        restartGame()
    }

    fun onClick(v: View)
    {
        val myButton = v as Button

        if (v.text.toString().equals(""))
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
            changePlayer()
            myButton.isClickable = false
        }

    }

    fun changePlayer()
    {
        if (remainingSquares == 0)
        {
            tv_Info.text = "Game Over!"
        }

        else
        {
            oTurn = !oTurn

            val name = if (oTurn) "O" else "X"
            val color = if (oTurn) oColor else xColor

            tv_Info.text = "Player $name turn"
            tv_Info.setTextColor(color)
        }
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
        for (row in GameBoard)
        {
            val r = row as TableRow
            for (button in r)
            {
                val b = button as Button
                b.setBackgroundColor(neutralColor)
                b.text = ""
                b.isClickable = true
            }
        }
        remainingSquares = 9
        oTurn = true
        changePlayer()
    }

}