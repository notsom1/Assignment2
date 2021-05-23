package com.example.tictactoe

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartGame(v: View)
    {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun onQuit (v:View)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quit")
        builder.setMessage("Are you sure you want to quit?")
        builder.setIcon(R.drawable.ic_launcher_background)

        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            finish()
            exitProcess(0)
        })

        builder.setNeutralButton("No", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        val alertDialog = builder.create()
        alertDialog.show()
    }
}