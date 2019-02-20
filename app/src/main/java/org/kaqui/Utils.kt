package org.kaqui

import android.graphics.PathMeasure
import android.graphics.PointF
import org.kaqui.model.BAD_WEIGHT
import org.kaqui.model.GOOD_WEIGHT
import java.util.*
import kotlin.math.pow

fun getBackgroundFromScore(score: Double) =
        when (score) {
            in 0.0f..BAD_WEIGHT -> R.drawable.round_red
            in BAD_WEIGHT..GOOD_WEIGHT -> R.drawable.round_yellow
            in GOOD_WEIGHT..1.0f -> R.drawable.round_green
            else -> R.drawable.round_red
        }

fun <T> MutableList<T>.shuffle() {
    val rg = Random()
    for (i in this.size - 1 downTo 1) {
        val target = rg.nextInt(i)
        val tmp = this[i]
        this[i] = this[target]
        this[target] = tmp
    }
}

fun <T> pickRandom(list: List<T>, sample: Int, avoid: Set<T> = setOf()): List<T> {
    if (sample > list.size - avoid.size)
        throw RuntimeException("can't get a sample of size $sample on list of size ${list.size - avoid.size}")

    val chosen = mutableSetOf<T>()
    while (chosen.size < sample) {
        val r = list[(Math.random() * list.size).toInt()]
        if (r !in avoid)
            chosen.add(r)
    }
    return chosen.toList()
}

fun PointF.squaredDistanceTo(o: PointF): Float = (this.x - o.x).pow(2) + (this.y - o.y).pow(2)

fun PathMeasure.getPoint(position: Float): PointF {
    val out = floatArrayOf(0f, 0f)
    getPosTan(position, out, null)
    return PointF(out[0], out[1])
}
