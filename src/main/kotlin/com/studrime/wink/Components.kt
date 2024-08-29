package com.studrime.wink

import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.OverlayLayout
import kotlin.coroutines.CoroutineContext

typealias ContainerOp<L> = JPanel.(L) -> Unit
typealias ComponentOp<C> = C.() -> Unit

val Component.componentScope: CoroutineScope
    get() = Wink.coroutineScopeMap.computeIfAbsent(this) {
        CoroutineScope(Wink.applicationScope.coroutineContext.job +  Dispatchers.Main)
    }

operator fun Container.plusAssign(component: Component) {
    add(component)
}

operator fun Container.plusAssign(pair: Pair<Component, Any>) {
    val (component, constraint) = pair
    add(component, constraint)
}

fun Container.addAll(vararg children: Component) {
    for (child in children) {
        add(child)
    }
}

fun BorderPane(
    north: Component? = null,
    east: Component? = null,
    south: Component? = null,
    west: Component? = null,
    center: Component? = null,
    op: ContainerOp<BorderLayout> = {}) = JPanel().apply {
    val layout = BorderLayout()
    this.layout = layout

    if (north != null) add(north, BorderLayout.NORTH)
    if (east != null) add(east, BorderLayout.EAST)
    if (south != null) add(south, BorderLayout.SOUTH)
    if (west != null) add(west, BorderLayout.WEST)
    if (center != null) add(center, BorderLayout.CENTER)

    op(layout)
}

fun FlowPane(vararg children: Component, op: ContainerOp<FlowLayout> = {}) = JPanel().apply {
    val layout = FlowLayout()
    this.layout = layout
    addAll(*children)
    op(layout)
}

fun VBox(vararg children: Component, op: ContainerOp<BoxLayout> = {}) = JPanel().apply {
    val layout = BoxLayout(this, BoxLayout.Y_AXIS)
    this.layout = layout
    addAll(*children)
    op(layout)
}

fun HBox(vararg children: Component, op: ContainerOp<BoxLayout> = {}) = JPanel().apply {
    val layout = BoxLayout(this, BoxLayout.X_AXIS)
    this.layout = layout
    addAll(*children)
    op(layout)
}

fun OverlayPane(vararg children: Component, op: ContainerOp<OverlayLayout> = {}) = JPanel().apply {
    val layout = OverlayLayout(this)
    this.layout = layout
    addAll(*children)
    op(layout)
}

interface ComponentScope {
    fun useComponentScope(context: CoroutineContext = Dispatchers.Main, f: suspend CoroutineScope.() -> Unit)
}

private class ComponentScopeImpl : ComponentScope {
    private val coroutineFunctions = mutableListOf<suspend CoroutineScope.() -> Unit>()

    override fun useComponentScope(context: CoroutineContext, f: suspend CoroutineScope.() -> Unit) {
        if (f == Dispatchers.Main) coroutineFunctions += f
        else coroutineFunctions += {
            launch(context, CoroutineStart.DEFAULT, f)
        }
    }

    fun invokeAllCoroutineFunctions(scope: CoroutineScope) {
        scope.launch {
            coroutineFunctions.forEach { it() }
        }
    }
}

fun <T: Component> Component(f: ComponentScope.() -> T): T {
    val scope = ComponentScopeImpl()
    val component = scope.f()
    scope.invokeAllCoroutineFunctions(component.componentScope)
    return component
}

fun JButton(text: String, op: ComponentOp<JButton> = {}) = JButton(text).apply {
    op()
}

fun JLabel(text: String, op: ComponentOp<JLabel> = {}) = JLabel(text).apply(op)