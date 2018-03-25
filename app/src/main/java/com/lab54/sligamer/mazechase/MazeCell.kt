package com.lab54.sligamer.mazechase

/**
 * Created by Justin Freres on 3/24/2018.
 * Lab 5-4 Maze Chase Game
 * Plugin Support with kotlin_version = '1.2.21'
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