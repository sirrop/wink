package com.studrime.wink

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.awt.Component
import java.util.WeakHashMap

internal object Wink {
    val applicationScope = CoroutineScope(Dispatchers.Default)
    val coroutineScopeMap: WeakHashMap<Component, CoroutineScope> = WeakHashMap()
}