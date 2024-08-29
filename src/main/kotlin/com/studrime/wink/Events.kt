package com.studrime.wink

import com.sun.java.accessibility.util.AWTEventMonitor.addActionListener
import com.sun.java.accessibility.util.AWTEventMonitor.removeActionListener
import kotlinx.coroutines.launch
import java.awt.Adjustable
import java.awt.Component
import java.awt.Container
import java.awt.ItemSelectable
import java.awt.event.*
import java.time.Instant
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.JInternalFrame
import javax.swing.event.AncestorEvent
import javax.swing.event.AncestorListener
import javax.swing.event.InternalFrameAdapter
import javax.swing.event.InternalFrameEvent
import javax.swing.text.JTextComponent

typealias EventHandler<E> = suspend (E) -> Unit

val InputEvent.firedDateTime: Instant get() = Instant.ofEpochMilli(`when`)

fun Component.onMouseClicked(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            componentScope.launch {
                handle(e)
            }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMousePressed(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mousePressed(e: MouseEvent) {
            componentScope.launch {
                handle(e)
            }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMouseReleased(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mouseReleased(e: MouseEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMouseMoved(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mouseMoved(e: MouseEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMouseDragged(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mouseDragged(e: MouseEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMouseEntered(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mouseEntered(e: MouseEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMouseExited(handle: EventHandler<MouseEvent>): Disposable {
    val listener = object: MouseAdapter() {
        override fun mouseExited(e: MouseEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addMouseListener(listener)
    return Disposable { removeMouseListener(listener) }
}

fun Component.onMouseWheelMoved(handle: EventHandler<MouseWheelEvent>): Disposable {
    val listener = MouseWheelListener {
        componentScope.launch { handle(it) }
    }
    addMouseWheelListener(listener)
    return Disposable { removeMouseWheelListener(listener) }
}


fun Component.onKeyPressed(handle: EventHandler<KeyEvent>): Disposable {
    val listener = object: KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addKeyListener(listener)
    return Disposable { removeKeyListener(listener) }
}

fun Component.onKeyReleased(handle: EventHandler<KeyEvent>): Disposable {
    val listener = object: KeyAdapter() {
        override fun keyReleased(e: KeyEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addKeyListener(listener)
    return Disposable { removeKeyListener(listener) }
}

fun Component.onKeyTyped(handle: EventHandler<KeyEvent>): Disposable {
    val listener = object: KeyAdapter() {
        override fun keyTyped(e: KeyEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addKeyListener(listener)
    return Disposable { removeKeyListener(listener) }
}

fun Component.onFocusGained(handle: EventHandler<FocusEvent>): Disposable {
    val listener = object: FocusAdapter() {
        override fun focusGained(e: FocusEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addFocusListener(listener)
    return Disposable { removeFocusListener(listener) }
}

fun Component.onFocusLost(handle: EventHandler<FocusEvent>): Disposable {
    val listener = object: FocusAdapter() {
        override fun focusLost(e: FocusEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addFocusListener(listener)
    return Disposable { removeFocusListener(listener) }
}

fun Container.onComponentAdded(handle: EventHandler<ContainerEvent>): Disposable {
    val listener = object: ContainerAdapter() {
        override fun componentAdded(e: ContainerEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addContainerListener(listener)
    return Disposable { removeContainerListener(listener) }
}

fun Container.onComponentRemoved(handle: EventHandler<ContainerEvent>): Disposable {
    val listener = object: ContainerAdapter() {
        override fun componentRemoved(e: ContainerEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addContainerListener(listener)
    return Disposable { removeContainerListener(listener) }
}

fun Component.onComponentHidden(handle: EventHandler<ComponentEvent>): Disposable {
    val listener = object: ComponentAdapter() {
        override fun componentHidden(e: ComponentEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addComponentListener(listener)
    return Disposable { removeComponentListener(listener) }
}

fun Component.onComponentMoved(handle: EventHandler<ComponentEvent>): Disposable {
    val listener = object: ComponentAdapter() {
        override fun componentMoved(e: ComponentEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addComponentListener(listener)
    return Disposable { removeComponentListener(listener) }
}

fun Component.onComponentResized(handle: EventHandler<ComponentEvent>): Disposable {
    val listener = object: ComponentAdapter() {
        override fun componentResized(e: ComponentEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addComponentListener(listener)
    return Disposable { removeComponentListener(listener) }
}

fun Component.onComponentShown(handle: EventHandler<ComponentEvent>): Disposable {
    val listener = object: ComponentAdapter() {
        override fun componentShown(e: ComponentEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addComponentListener(listener)
    return Disposable { removeComponentListener(listener) }
}

fun <C> C.onAdjustmentValueChanged(handle: EventHandler<AdjustmentEvent>): Disposable where C: Component, C: Adjustable {
    val listener = AdjustmentListener {
        componentScope.launch { handle(it) }
    }
    addAdjustmentListener(listener)
    return Disposable { removeAdjustmentListener(listener) }
}

fun JComponent.onAncestorAdded(handle: EventHandler<AncestorEvent>): Disposable {
    val listener = object: AncestorListener {
        override fun ancestorAdded(e: AncestorEvent) {
            componentScope.launch { handle(e) }
        }
        override fun ancestorMoved(event: AncestorEvent?) {}
        override fun ancestorRemoved(event: AncestorEvent?) {}
    }
    addAncestorListener(listener)
    return Disposable { removeAncestorListener(listener) }
}

fun JComponent.onAncestorMoved(handle: EventHandler<AncestorEvent>): Disposable {
    val listener = object: AncestorListener {
        override fun ancestorMoved(e: AncestorEvent) {
            componentScope.launch { handle(e) }
        }
        override fun ancestorRemoved(e: AncestorEvent) {}
        override fun ancestorAdded(e: AncestorEvent) {}
    }
    addAncestorListener(listener)
    return Disposable { removeAncestorListener(listener) }
}

fun JComponent.onAncestorRemoved(handle: EventHandler<AncestorEvent>): Disposable {
    val listener = object: AncestorListener {
        override fun ancestorRemoved(e: AncestorEvent) {
            componentScope.launch { handle(e) }
        }
        override fun ancestorAdded(e: AncestorEvent) {}
        override fun ancestorMoved(e: AncestorEvent) {}
    }
    addAncestorListener(listener)
    return Disposable { removeAncestorListener(listener) }
}

fun Component.onCaretPositionChanged(handle: EventHandler<InputMethodEvent>): Disposable {
    val listener = object: InputMethodListener {
        override fun caretPositionChanged(event: InputMethodEvent) {
            componentScope.launch { handle(event) }
        }

        override fun inputMethodTextChanged(event: InputMethodEvent?) {

        }
    }
    addInputMethodListener(listener)
    return Disposable { removeInputMethodListener(listener) }
}

fun Component.onInputMethodTextChanged(handle: EventHandler<InputMethodEvent>): Disposable {
    val listener = object: InputMethodListener {
        override fun inputMethodTextChanged(event: InputMethodEvent) {
            componentScope.launch { handle(event) }
        }

        override fun caretPositionChanged(event: InputMethodEvent?) {

        }
    }
    addInputMethodListener(listener)
    return Disposable { removeInputMethodListener(listener) }
}

fun JInternalFrame.onInternalFrameActivated(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameActivated(event: InternalFrameEvent) {
            componentScope.launch { handle(event) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun JInternalFrame.onInternalFrameClosed(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameClosed(event: InternalFrameEvent) {
            componentScope.launch { handle(event) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun JInternalFrame.onInternalFrameClosing(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameClosing(event: InternalFrameEvent) {
            componentScope.launch { handle(event) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun JInternalFrame.onInternalFrameDeactivated(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameDeactivated(event: InternalFrameEvent) {
            componentScope.launch { handle(event) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun JInternalFrame.onInternalFrameDeiconified(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameDeiconified(event: InternalFrameEvent) {
            componentScope.launch { handle(event) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun JInternalFrame.onInternalFrameIconified(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameIconified(event: InternalFrameEvent) {
            componentScope.launch { handle(event) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun JInternalFrame.onInternalFrameOpend(handle: EventHandler<InternalFrameEvent>): Disposable {
    val listener = object: InternalFrameAdapter() {
        override fun internalFrameOpened(e: InternalFrameEvent) {
            componentScope.launch { handle(e) }
        }
    }
    addInternalFrameListener(listener)
    return Disposable { removeInternalFrameListener(listener) }
}

fun <C> C.onItemStateChanged(handle: EventHandler<ItemEvent>): Disposable where C: Component, C: ItemSelectable {
    val listener = ItemListener { componentScope.launch { handle(it) } }
    addItemListener(listener)
    return Disposable { removeItemListener(listener) }
}


fun AbstractButton.onAction(handle: EventHandler<ActionEvent>): Disposable {
    val listener = ActionListener {
        componentScope.launch {
            handle(it)
        }
    }

    addActionListener(listener)
    return Disposable {
        removeActionListener(listener)
    }
}

fun JTextComponent.onAction(handle: EventHandler<ActionEvent>): Disposable {
    val listener = ActionListener {
        componentScope.launch {
            handle(it)
        }
    }

    addActionListener(listener)
    return Disposable {
        removeActionListener(listener)
    }
}
