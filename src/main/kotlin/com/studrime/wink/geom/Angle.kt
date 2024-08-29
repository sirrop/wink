package com.studrime.wink.geom

import kotlin.math.PI

interface Angle {
    fun toRadian(): Radian
    fun toDegree(): Degree
}

val Number.rad: Radian get() = Radian(toDouble())
val Number.deg: Degree get() = Degree(toDouble())
val Number.grad: Grade get() = Grade(toDouble())
val Number.turn: Turn get() = Turn(toDouble())

@JvmInline
value class Radian(val value: Double) : Angle {
    override fun toRadian() = this
    override fun toDegree() = (value * 180 / PI).deg
}

@JvmInline
value class Degree(val value: Double) : Angle {
    override fun toRadian() = (value * PI / 180).rad
    override fun toDegree() = this
}

@JvmInline
value class Grade(val value: Double) : Angle {
    override fun toDegree() = (value * 360 / 400).deg
    override fun toRadian() = (value * PI / 200).rad
}

@JvmInline
value class Turn(val value: Double) : Angle {
    override fun toRadian() = (2 * PI * value).rad
    override fun toDegree() = (360 * value).deg
}