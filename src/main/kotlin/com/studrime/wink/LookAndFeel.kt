package com.studrime.wink

import javax.swing.UIManager

fun SystemLookAndFeel(op: () -> Unit) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    op.invoke()
}