package com.toolgits.xezenon

import android.app.Activity
import android.graphics.Color
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
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.WHITE)
        }

        val title = TextView(this).apply {
            text = getString(R.string.app_name) + " 🤖"
            textSize = 30f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(40, 40, 40))
            setPadding(0, 8, 0, 2)
        }

        val subtitle = TextView(this).apply {
            text = getString(R.string.subtitle)
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.GRAY)
            setPadding(0, 0, 0, 18)
        }

        conversation = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(8, 8, 8, 8)
        }

        scrollView = ScrollView(this).apply {
            isFillViewport = true
            addView(conversation)

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        }

        val input = EditText(this).apply {
            hint = getString(R.string.input_hint)
            setSingleLine(true)

            background = getDrawable(
                R.drawable.input_background
            )

            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val sendButton = Button(this).apply {
            text = getString(R.string.send)
            textSize = 14f
            setAllCaps(false)
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

            val message =
                input.text.toString().trim()

            if (message.isEmpty()) {
                return@setOnClickListener
            }

            addMessage(
                getString(R.string.user_label),
                message,
                false
            )

            val result =
                brain.think(message)

            addMessage(
                getString(R.string.xezenon_label),
                result,
                true
            )

            input.text.clear()

            scrollView.post {
                scrollView.fullScroll(
                    ScrollView.FOCUS_DOWN
                )
            }
        }

        input.setOnEditorActionListener { _, _, _ ->
            sendButton.performClick()
            true
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(scrollView)
        root.addView(inputLayout)

        setContentView(root)
    }

    private fun addMessage(
        sender: String,
        message: String,
        xezenon: Boolean
    ) {

        val messageText =
            TextView(this).apply {

                text =
                    "$sender\n$message"

                textSize = 17f

                setPadding(
                    16,
                    12,
                    16,
                    12
                )

                setTextColor(
                    if (xezenon) {
                        Color.rgb(
                            35,
                            35,
                            35
                        )
                    } else {
                        Color.WHITE
                    }
                )

                background =
                    getDrawable(
                        if (xezenon) {
                            R.drawable.message_xezenon
                        } else {
                            R.drawable.message_user
                        }
                    )
            }

        val params =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        params.gravity =
            if (xezenon) {
                Gravity.START
            } else {
                Gravity.END
            }

        params.setMargins(
            8,
            6,
            8,
            6
        )

        conversation.addView(
            messageText,
            params
        )
    }
}