package com.studrime.wink

import kotlinx.coroutines.cancel
import java.time.Instant
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JMenuBar
import javax.swing.SwingUtilities
import javax.swing.WindowConstants
import kotlin.system.exitProcess

typealias InitHook = Application.() -> Unit
typealias StartHook = Application.() -> JComponent
typealias ExitHook = Application.(Boolean) -> Unit

interface Application {
    val args: Arguments
    val window: JFrame
    val atLaunched: Instant
    val isApplicationThread: Boolean get() = SwingUtilities.isEventDispatchThread()

    data class Arguments(val args: Array<out String>) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Arguments

            return args.contentEquals(other.args)
        }

        override fun hashCode(): Int {
            return args.contentHashCode()
        }
    }

    fun invokeLater(f: () -> Unit) = SwingUtilities.invokeLater(f)

    fun exit(force: Boolean = false)

    /**
     * 登録済みのサービスを取得します。サービスが未登録の場合、`null`を返します。
     * @see registerService
     */
    fun <T: Any> getService(serviceClass: Class<in T>): T?

    /**
     * サービスを登録します。すでに別のサービスが登録されている場合、登録が上書きされます。
     */
    fun <T: Any> registerService(serviceClass: Class<in T>, instance: T)

    /**
     * サービスの登録を解除します。サービスが未登録の場合、何もしません。
     */
    fun <T: Any> unregisterService(serviceClass: Class<in T>)
}

fun Application.MenuBar(op: JMenuBar.() -> Unit) {
    window.jMenuBar = JMenuBar().apply(op)
}

fun Application(args: Array<out String>, onInit: InitHook = {}, onExit: ExitHook = {}, onStart: StartHook) {
    val myArgs = Application.Arguments(args.clone())
    val app = ApplicationImpl(myArgs, onInit, onExit, onStart, Instant.now())
    app.init()
    app.start()
}

private class ApplicationImpl(
    override val args: Application.Arguments,
    private val onInit: InitHook,
    private val onExit: ExitHook,
    private val onStart: StartHook,
    override val atLaunched: Instant
): Application {
    companion object {
    }

    override lateinit var window: JFrame
    private val serviceMap = mutableMapOf<Class<*>, Any>()


    fun init() {
        onInit()
        if (OS.current.isMac) {
            SwingProperty.apple.laf.useScreenMenuBar = true
        }
    }

    fun start() = invokeLater {
        window = JFrame()
        if (OS.current.isMac) {

        } else {
            window.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        }
        val component = onStart()
        window.contentPane += component
        window.isVisible = true
    }

    override fun exit(force: Boolean) {
        onExit(force)
        Wink.applicationScope.cancel("Application exit");
        exitProcess(0)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getService(serviceClass: Class<in T>): T? {
        return serviceMap[serviceClass]?.let { serviceClass.cast(it) } as T?
    }

    override fun <T : Any> registerService(serviceClass: Class<in T>, instance: T) {
        serviceMap[serviceClass] = instance
    }

    override fun <T : Any> unregisterService(serviceClass: Class<in T>) {
        serviceMap -= serviceClass
    }
}