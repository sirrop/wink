package com.studrime.wink

internal enum class OS {
    LINUX, WINDOWS, MACOS, UNKNOWN;

    companion object {
        val current: OS
        get() = run {
            val osName = System.getProperty("os.name").lowercase()
            if ("win" in osName) WINDOWS
            else if ("linux" in osName) LINUX
            else if ("mac" in osName) MACOS
            else UNKNOWN
        }
    }

    val isMac: Boolean get() = this == MACOS

    val isWindows: Boolean get() = this == WINDOWS

    val isLinux: Boolean get() = this == LINUX
}