package com.toolgits.xezenon

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.toolgits.xezenon.boxhead.Braindroid

class MainActivity : Activity() {

    private lateinit var brain: Braindroid
    private lateinit var conversation: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        brain = Braindroid(this)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(this).apply {
            text = "XeZenOn 🤖"
            textSize = 30f
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 8)
        }

        val subtitle = TextView(this).apply {
            text = "A lightweight semi-AI"
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 20)
        }

        conversation = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(8, 8, 8, 8)
        }

        scrollView = ScrollView(this).apply {
            addView(
                conversation,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        }

        val input = EditText(this).apply {
            hint = "Type a message..."
            singleLine = true
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val sendButton = Button(this).apply {
            text = "Send"
        }

        val inputLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(0, 12, 0, 0)
        }

        inputLayout.addView(input)

        inputLayout.addView(
            sendButton,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        sendButton.setOnClickListener {
            val message = input.text.toString().trim()

            if (message.isEmpty()) {
                return@setOnClickListener
            }

            addMessage("You", message)

            val result = brain.think(message)

            addMessage("XeZenOn", result)

            input.text.clear()

            scrollView.post {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(scrollView)
        root.addView(inputLayout)

        setContentView(root)
    }

    private fun addMessage(
        sender: String,
        message: String
    ) {
        val textView = TextView(this).apply {
            text = "$sender: $message"
            textSize = 18f
            setPadding(12, 10, 12, 10)
        }

        conversation.addView(
            textView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
    }
}