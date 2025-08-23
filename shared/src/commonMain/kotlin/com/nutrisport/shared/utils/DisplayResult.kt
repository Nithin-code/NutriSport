package com.nutrisport.shared.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun <T> RequestState<T>.DisplayResult(
    modifier: Modifier,
    onIdle: (@Composable () -> Unit)? = null,
    onLoading: (@Composable () -> Unit)? = null,
    onError: (@Composable (String) -> Unit)? = null,
    onSuccess: (@Composable (T) -> Unit),
    backgroundColor: Color? = null
) {

    AnimatedContent(
        modifier = Modifier.background(color = backgroundColor ?: Color.Unspecified),
        targetState = this
    ) { state ->

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            when (state) {
                is RequestState.Error -> {
                    onError?.invoke(state.getErrorMessage())
                }

                RequestState.Idle -> {
                    onIdle?.invoke()
                }

                RequestState.Loading -> {
                    onLoading?.invoke()
                }

                is RequestState.Success -> {
                    onSuccess.invoke(state.getSuccessData())
                }
            }

        }

    }

}