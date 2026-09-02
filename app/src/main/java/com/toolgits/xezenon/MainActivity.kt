package com.toolgits.xezenon

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.toolgits.xezenon.boxhead.Braindroid

class MainActivity : android.app.Activity() {

    private lateinit var brain: Braindroid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        brain = Braindroid(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 32, 32, 32)

        val title = TextView(this)
        title.text = "XeZenOn 🤖"
        title.textSize = 28f

        val input = EditText(this)
        input.hint = "Digite uma mensagem..."

        val button = Button(this)
        button.text = "Enviar"

        val response = TextView(this)
        response.textSize = 18f

        button.setOnClickListener {
            response.text = brain.think(
                input.text.toString()
            )
        }

        layout.addView(title)
        layout.addView(input)
        layout.addView(button)
        layout.addView(response)

        setContentView(layout)
    }
}