package com.studrime.wink

fun interface Disposable {
    fun dispose()
}

class CompositeDisposable : Disposable {
    private val disposables = mutableListOf<Disposable>()

    private var myDisposed = false
    val isDisposed: Boolean get() = myDisposed

    override fun dispose() {
        check(!isDisposed)

        disposables.forEach { it.dispose() }
        disposables.clear()
        myDisposed = true
    }

    fun add(disposable: Disposable) {
        check(!isDisposed)
        disposables.add(disposable)
    }

    fun remove(disposable: Disposable) {
        check(!isDisposed)
        disposables.remove(disposable)
    }

    operator fun plusAssign(disposable: Disposable) {
        add(disposable)
    }

    operator fun minusAssign(disposable: Disposable) {
        remove(disposable)
    }
}