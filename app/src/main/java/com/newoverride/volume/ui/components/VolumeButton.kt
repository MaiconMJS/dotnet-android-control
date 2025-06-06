package com.newoverride.volume.ui.components

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.newoverride.volume.R
import com.newoverride.volume.dimens.Dimens

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VolumeButton(
    direction: String,
    onClick: () -> Unit,
    isPressedUP: MutableState<Boolean> = mutableStateOf(false),
    isPressedDOWN: MutableState<Boolean> = mutableStateOf(false)
) {

    val scaleUP by animateFloatAsState(
        targetValue = if (isPressedUP.value) 0.90f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "spring-press-scale"
    )

    val scaleDOWN by animateFloatAsState(
        targetValue = if (isPressedDOWN.value) 0.90f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "spring-press-scale"
    )

    if (direction == "UP") {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(Dimens.buttonVolumeSize)
                .scale(scaleUP)
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isPressedUP.value = true
                            onClick()
                            true
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            isPressedUP.value = true
                            onClick()
                            true
                        }

                        else -> false
                    }
                }
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_arrow_circle_up_24),
                contentDescription = stringResource(R.string.app_name)
            )
        }
    } else {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(Dimens.buttonVolumeSize)
                .scale(scaleDOWN)
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isPressedDOWN.value = true
                            onClick()
                            true
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            isPressedDOWN.value = true
                            onClick()
                            true
                        }

                        else -> false
                    }
                }
        ) {
            Image(
                painter = painterResource(R.drawable.outline_arrow_circle_down_24),
                contentDescription = stringResource(R.string.app_name)
            )
        }
    }
}