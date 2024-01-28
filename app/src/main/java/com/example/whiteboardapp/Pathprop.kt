package com.example.whiteboardapp

import android.graphics.Path


class Pathprop(pathclr:Int,pathstroke:Int,pathpath:Path) {
    public var ptcolor:Int
    public var ptstroke:Int
    public var ptpath:Path

    init {
        this.ptcolor=pathclr
        this.ptstroke=pathstroke
        this.ptpath=pathpath
    }
}