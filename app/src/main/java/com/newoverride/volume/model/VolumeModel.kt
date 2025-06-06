package com.newoverride.volume.model

import androidx.compose.runtime.MutableState

data class VolumeModel(
    val isPressedUP: MutableState<Boolean>,
    val isPressedDOWN: MutableState<Boolean>,
    val onClickUP: () -> Unit,
    val onClickDOWN: () -> Unit,
)