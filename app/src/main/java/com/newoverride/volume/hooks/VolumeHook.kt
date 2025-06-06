package com.newoverride.volume.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.newoverride.volume.model.VolumeModel
import com.newoverride.volume.services.UDPSender
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun volumeHook(): VolumeModel {
    val isPressedUP = remember { mutableStateOf(false) }
    val isPressedDOWN = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var volume = 50

    fun animateButton(active: MutableState<Boolean>) {
        active.value = true
        coroutineScope.launch {
            delay(100)
            active.value = false
        }
    }

    fun onClickUP() {
        animateButton(isPressedUP)
        volume++
        UDPSender.sendCommand("${volume}+")
    }

    fun onClickDOWN() {
        animateButton(isPressedDOWN)
        volume--
        UDPSender.sendCommand("${volume}-")
    }

    return VolumeModel(
        isPressedUP = isPressedUP,
        isPressedDOWN = isPressedDOWN,
        onClickUP = { onClickUP() },
        onClickDOWN = { onClickDOWN() }
    )
}