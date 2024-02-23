package io.monstarlab.mosaic.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SliderTrack(
    progress: Float,
    colors: SliderColors,
    modifier: Modifier = Modifier,
    disabledRange: ClosedFloatingPointRange<Float> = 0f..0f,
) {

    check(progress in 0f..1f) { "Invalid progress value should be between 0 and 1" }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(SliderDefaults.TrackHeight)
    ) {
        val activeRectWidth = size.width * progress
        drawRect(
            color = colors.active,
            topLeft = Offset.Zero,
            size = Size(activeRectWidth, size.height)
        )

        drawRect(
            color = colors.inactive,
            topLeft = Offset(activeRectWidth, 0f),
            size = Size(size.width - activeRectWidth, size.height)
        )

        if (!disabledRange.isEmpty()) {
            val disabledStart = size.width * disabledRange.start
            val disabledEnd = size.width * disabledRange.endInclusive
            drawRect(
                color = colors.disabled,
                topLeft = Offset(size.width * disabledRange.start, 0f),
                size = Size(disabledEnd - disabledStart, size.height)
            )
        }
    }
}


@Preview
@Composable
private fun PreviewSliderTrack() {
    SliderTrack(
        progress = 0.5f,
        colors = SliderColors(Color.Red),
        disabledRange = 0.8f..1f
    )
}