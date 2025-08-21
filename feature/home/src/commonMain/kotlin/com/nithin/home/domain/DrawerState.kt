package com.nithin.home.domain

enum class DrawerState {
    OPENED,
    CLOSED
}

fun DrawerState.isOpened() : Boolean{
    return this == DrawerState.OPENED
}

fun DrawerState.MakeOpposite() : DrawerState{
    return if (this == DrawerState.OPENED) {
        DrawerState.CLOSED
    }else{
        DrawerState.OPENED
    }
}