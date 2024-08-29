package com.studrime.wink

import java.awt.event.ActionListener
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

fun JMenuBar.Menu(text: String, op: JMenu.() -> Unit) {
    add(JMenu(text).apply(op))
}

fun JMenu.Menu(text: String, op: JMenu.() -> Unit) {
    add(JMenu(text).apply(op))
}

fun JMenu.MenuItem(text: String, onAction: ActionListener) {
    add(JMenuItem(text).apply {
        addActionListener(onAction)
    })
}

fun JMenu.Separator() {
    addSeparator()
}