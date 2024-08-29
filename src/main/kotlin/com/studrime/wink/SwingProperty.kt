package com.studrime.wink

object SwingProperty {
    interface Apple {
        val laf: LaF
        interface LaF {
            var useScreenMenuBar: Boolean
        }
    }

    @JvmStatic
    val apple: Apple = object : Apple {
        override var laf: Apple.LaF = object : Apple.LaF {
            override var useScreenMenuBar: Boolean
                get() = System.getProperty("apple.laf.useScreenMenuBar")?.toBoolean() ?: false
                set(value) {
                    System.setProperty("apple.laf.useScreenMenuBar", value.toString())
                }
        }
    }
}