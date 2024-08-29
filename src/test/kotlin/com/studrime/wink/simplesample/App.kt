package com.studrime.wink.simplesample

import com.studrime.wink.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun main(vararg args: String) = SystemLookAndFeel {
    Application(args) {
        window.apply {
            title = "Sample Application"
            size = 800 x 600
            centerOnScreen()
        }

        MenuBar {
            Menu("ファイル") {
                MenuItem("設定") {
                    println("設定")
                }
                Separator()
                MenuItem("終了") {
                    exit(false)
                }
            }
            Menu("編集") {
                MenuItem("Undo") {
                    println("Undo")
                }
                MenuItem("Redo") {
                    println("Redo")
                }
            }
        }

        val count = MutableStateFlow(0)

        VBox(
            JLabel("count: ${count.value}") {
                count.onEach { text = "count: $it" }.launchIn(componentScope)
            },
            JButton("Click me") {
                onAction { count.value++ }
            }
        )
    }
}
