package com.paulacr.composecomponents

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CalendarIcon(
    size: Dp,
    day: String,
    month: String,
    calendarColors: DefaultCalendarColors = DefaultCalendarColors()
) {
    val sizeF = size.value

    val startX = 0f
    val startY = 0f
    val cornerRadius = (sizeF / 8).run {
        CornerRadius(this, this)
    }

    Canvas(modifier = Modifier
        .size(width = size, height = size)
        .wrapContentSize(), onDraw = {

        with(cornerRadius) {
            baseRectangle(
                sizeF,
                startX,
                startY,
                this,
                backgroundColor = calendarColors.backgroundColor
            )
            topRectangle(
                startX,
                startY,
                sizeF,
                this,
                color = calendarColors.topRectangleDetailColor
            )
        }
        with(sizeF) {
            circle(this, startY, this / 4f)
            circle(this, startY, centerX = this / 1.4f)
        }

        ovalPiece(sizeF, startY, cornerRadius, topLeftX = 4.4f)
        ovalPiece(sizeF, startY, cornerRadius, topLeftX = 1.45f)

        drawText(
            text = day,
            textSize = sizeF / 2,
            textColor = calendarColors.contentDayColor,
            positionX = sizeF / 5,
            positionY = sizeF - sizeF * 0.2f
        )

        drawText(
            text = month,
            textSize = sizeF / 6,
            textColor = calendarColors.contentMonthColor,
            positionX = sizeF / 3,
            positionY = sizeF - sizeF * 0.75f
        )
    })
}



private fun DrawScope.drawText(
    text: String,
    textSize: Float,
    textColor: Color,
    positionX: Float,
    positionY: Float
) {
    val paint = Paint().apply {
        this.textSize = textSize
        this.color = textColor.hashCode()
        this.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    drawIntoCanvas {
        it.nativeCanvas.drawText(
            text,
            positionX,
            positionY,
            paint
        )
    }
}

private fun DrawScope.ovalPiece(
    sizeF: Float,
    startY: Float,
    cornerRadius: CornerRadius,
    topLeftX: Float
) {
    drawRoundRect(
        color = Color.Gray,
        size = Size(sizeF * 0.05f, sizeF * 0.12f),
        topLeft = Offset(x = sizeF / topLeftX, y = startY - sizeF * 0.02f),
        cornerRadius = cornerRadius
    )
}

private fun DrawScope.circle(
    sizeF: Float,
    startY: Float,
    centerX: Float
) {
    drawCircle(
        color = Color.DarkGray,
        radius = sizeF * 0.04f,
        center = Offset(x = centerX, y = startY + sizeF * 0.08f)
    )
}

private fun DrawScope.topRectangle(
    startX: Float,
    startY: Float,
    sizeF: Float,
    cornerRadius: CornerRadius,
    color: Color
) {
    val path = Path().apply {
        addRoundRect(
            RoundRect(
                rect = Rect(
                    offset = Offset(x = startX, y = startY),
                    size = Size(sizeF, sizeF * 0.3f),
                ),
                topLeft = cornerRadius,
                topRight = cornerRadius,
            )
        )
    }
    drawPath(path, color = color)
}

private fun DrawScope.baseRectangle(
    sizeF: Float,
    startX: Float,
    startY: Float,
    cornerRadius: CornerRadius,
    backgroundColor: Color
) {
    drawRoundRect(
        color = backgroundColor,
        size = Size(sizeF, sizeF),
        topLeft = Offset(x = startX, y = startY),
        cornerRadius = cornerRadius
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFEEE)
@Composable
fun IconPreview() {
    Column(Modifier.wrapContentSize().padding(30.dp).background(Color.Gray)) {
        CalendarIcon(300.dp, day = "12", month = "July")
    }
}