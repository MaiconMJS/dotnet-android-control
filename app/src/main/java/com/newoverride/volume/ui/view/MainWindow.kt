package com.newoverride.volume.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.newoverride.volume.R
import com.newoverride.volume.dimens.Dimens
import com.newoverride.volume.hooks.volumeHook
import com.newoverride.volume.ui.components.VolumeButton
import com.newoverride.volume.ui.theme.VolumeTheme

@Composable
fun MainWindow() {

    val volumeHook = volumeHook()

    VolumeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.background_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    VolumeButton(
                        direction = "UP",
                        onClick = { volumeHook.onClickUP() },
                        isPressedUP = volumeHook.isPressedUP
                    )
                    Spacer(modifier = Modifier.padding(Dimens.volumeButtonSpacer))
                    VolumeButton(
                        direction = "DOWN",
                        onClick = { volumeHook.onClickDOWN() },
                        isPressedDOWN = volumeHook.isPressedDOWN
                    )
                }
            }
        }
    }
}