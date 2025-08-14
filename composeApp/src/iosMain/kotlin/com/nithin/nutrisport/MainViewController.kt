package com.nithin.nutrisport

import androidx.compose.ui.window.ComposeUIViewController
import com.nithin.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initializeKoin()
    }
) {
    App()
}