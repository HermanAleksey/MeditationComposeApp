package com.example.charts.pie_chart

import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

internal fun isPointInsideCircle(
    pointX: Float,
    pointY: Float,
    circleX: Float,
    circleY: Float,
    circleRadius: Float,
): Boolean {
    return (pointX - circleX).pow(2) + (pointY - circleY).pow(2) <= circleRadius.pow(2)
}

internal fun calculateSegmentLength(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
}

/**
 * Calculate angle between segments [segmentA] and [segmentB],
 * where [segmentC] is segment, lies against the calculated angle
 *
 * formula: (a^2*b^2/c^2)/2*a*b
 * */
internal fun calculateAngleBetweenSegments(
    segmentALength: Float,
    segmentBLength: Float,
    segmentCLength: Float,
): Float {
    val upperPart = segmentALength.pow(2) + segmentBLength.pow(2) - segmentCLength.pow(2)
    val bottomPart = (2 * segmentALength * segmentBLength)
    val angleCos = upperPart / bottomPart
    return (acos(angleCos) * 180 / PI).toFloat()
}