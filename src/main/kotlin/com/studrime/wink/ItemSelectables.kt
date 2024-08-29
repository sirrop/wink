package com.studrime.wink

import kotlinx.coroutines.launch
import java.awt.ItemSelectable
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.JComponent

fun ItemSelectable.onItemStateChanged(handle: EventHandler<ItemEvent>): Disposable {
    if (this is JComponent) {
        val listener = ItemListener {
            componentScope.launch { handle(it) }
        }
        addItemListener(listener)
        return Disposable { removeItemListener(listener) }
    } else {
        throw IllegalStateException()
    }
}