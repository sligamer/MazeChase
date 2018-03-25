package com.lab54.sligamer.mazechase

/**
 * Created by sligamer on 3/22/2018.
 */
class MazeCell {

    var x: Int = 0
    var y: Int = 0
    var id: Int = 0
    var visited: Boolean = false
    var north: Boolean = false
    var south: Boolean = false
    var east: Boolean = false
    var west: Boolean = false

    constructor(xPos: Int, yPos: Int, cellId: Int){
        x = xPos
        y = yPos
        visited = false
        north = true
        south = true
        east = true
        west = true
    }
}