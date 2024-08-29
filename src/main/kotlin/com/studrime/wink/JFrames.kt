package com.studrime.wink

import kotlinx.coroutines.launch
import java.awt.Window
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JMenu

fun JFrame.centerOnScreen() {
    setLocationRelativeTo(null)
}

var JFrame.fullscreen: Boolean
    get() = graphicsConfiguration.device.fullScreenWindow == this
    set(value) {
        if (value) {
            graphicsConfiguration.device.fullScreenWindow = this
        } else {
            graphicsConfiguration.device.fullScreenWindow = null
        }
    }

fun JFrame.JMenuBar(vararg menus: JMenu) {
    jMenuBar = javax.swing.JMenuBar().apply {
        for (menu in menus) {
            add(menu)
        }
    }
}

fun Window.onWindowActivated(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowActivated(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun Window.onWindowDeactivated(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowDeactivated(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun Window.onWindowClosed(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowClosed(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun Window.onWindowClosing(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun Window.onWindowDeiconified(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowDeiconified(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun Window.onWindowIconified(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowIconified(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun Window.onWindowOpened(handle: EventHandler<WindowEvent>): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowOpened(e: WindowEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}