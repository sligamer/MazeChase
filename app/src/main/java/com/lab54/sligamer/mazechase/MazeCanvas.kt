package com.lab54.sligamer.mazechase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import java.util.*

/**
 * Created by Justin Freres on 3/24/2018.
 * Lab 5-4 Maze Chase Game
 * Plugin Support with kotlin_version = '1.2.21'
 */
class MazeCanvas : View {

    // MAZE DIMENSIONS
    var COLS: Int = 9  // IN ORDER TO FIX ON NEWER PHONE COLS 9
    var ROWS: Int = 9
    var N_Cells: Int = COLS * ROWS
    var SIZE: Int = 100
    var OFFSET: Int = 100

    // ARRAY OF MAZE CELLS
    var board: Array<MazeCell?>

    private var paint: Paint?

    constructor(context: Context) : super(context) {

        // TASK 1: DECLARE A MAZE ARRAY OF SIZE N_CELLS TO HOLD THE CELLS
        val size = N_Cells

        board = arrayOfNulls<MazeCell>(size)


        // TASK 2: INSTANTIATE CELL OBJECTS FOR EACH CELL IN THE MAZE
        var cellId = 0

        for (r in 0 until ROWS) {
            for (c in 0 until COLS) {
                // STEP 1: GENERATE A MAZE CELL WITH THE X, Y AND CELL ID
                var x = c * SIZE + OFFSET
                var y = r * SIZE + OFFSET
                var cell = MazeCell(x, y, cellId)

                // STEP 2: PLACE THE CELL IN THE MAZE
                board[cellId] = cell
                cellId++
            }
        }

        // TASK 3: SET THE PAINT FOR THE MAZE
        paint = Paint()
        paint!!.color = Color.BLACK
        paint!!.strokeWidth = 2.0f


        // TASK 4: USE A BACKTRACKER METHOD TO BREAK DOWN THE WALLS
        backtrackMaze()
    }

    override fun onDraw(canvas: Canvas) {
        // TASK 1: FILL THE CANVAS WITH WHITE PAINT
        canvas.drawRGB(255, 255, 255)

        // TASK 2: DRAW THE LINES FOR EVERY CELL
        var i = 0
        while (i < N_Cells) {

            var x = board[i]!!.x

            var y = board[i]!!.y

            when {
                board[i]!!.north -> canvas.drawLine(x.toFloat(), y.toFloat(), x + SIZE.toFloat(), y.toFloat(), paint)
            }

            when {
                board[i]!!.south -> canvas.drawLine(x.toFloat(), y + SIZE.toFloat(), x + SIZE.toFloat(), y + SIZE.toFloat(), paint)
            }

            when {
                board[i]!!.east -> canvas.drawLine(x + SIZE.toFloat(), y.toFloat(), x + SIZE.toFloat(), y + SIZE.toFloat(), paint)
            }

            when {
                board[i]!!.west -> canvas.drawLine(x.toFloat(), y.toFloat(), x.toFloat(), y + SIZE.toFloat(), paint)
            }

            i++
        }
    }

    private fun backtrackMaze() {

        //TASK 1: CREATE THE BACKTRACKER VARIABLES AND INIT
        var stack = Stack<Int>()
        var top: Int

        // TASK 2: VISIT THE FIRST CELL AND PUSH IT ONTO THE STACK
        var visitedCells: Int = 1  // COUNTS HOW MANY CELLS HAVE BEEN VISITED
        var cellID: Int = 0 // THE FIRST CELL IN THE MAZE
        board[cellID]!!.visited = true
        stack.push(cellID)


        // TASK 3: BACKTRACK UNTIL ALL THE CELLS HAVE BEEN VISITED
        while (visitedCells < N_Cells) {
            // STEP 1: WHICH WALLS CAN BE TAKEN DOWN AND FOR A GIVEN CELL?
            var possibleWalls: String? = ""

            when {
                board[cellID]!!.north && cellID >= COLS -> when { !board[cellID - COLS]!!.visited ->
                    possibleWalls += 'N'
                }
            }

            when {
                board[cellID]!!.west && cellID % COLS != 0 -> when { !board[cellID - 1]!!.visited ->
                    possibleWalls += 'W'
                }
            }

            when {
                board[cellID]!!.east && cellID % COLS != COLS - 1 -> when { !board[cellID + 1]!!.visited ->
                    possibleWalls += 'E'
                }
            }

            when {
                board[cellID]!!.south && cellID < COLS * ROWS - COLS -> when { !board[cellID + COLS]!!.visited ->
                    possibleWalls += 'S'
                }
            }

            // STEP 2: RANDOMLY  SELECT A RANDOM WALL FROM AVAILABLE WALLS
            when {
                possibleWalls!!.length > 0 -> {
                    val index: Int = (Math.random() * possibleWalls.length).toInt()
                    //val index: Int = Math.round(randomInt.toDouble()).toInt()
                    val randomWall: Char = possibleWalls.elementAt(index)

                    when (randomWall) {
                        'N' -> {
                            board[cellID]!!.north = false
                            board[cellID - COLS]!!.south = false
                            cellID -= COLS
                        }

                        'S' -> {
                            board[cellID]!!.south = false
                            board[cellID + COLS]!!.north = false
                            cellID += COLS
                        }

                        'E' -> {
                            board[cellID]!!.east = false
                            board[cellID + 1]!!.west = false
                            cellID++
                        }

                        'W' -> {
                            board[cellID]!!.west = false
                            board[cellID - 1]!!.east = false
                            cellID--
                        }

                    }
                    board[cellID]!!.visited = true
                    stack.push(cellID)
                    visitedCells++

                }
                // IF THERE ARE NO WALL TO BUST DOWN
                // BACKTRACK BY GRABBING THE TOP OF THE STACK
                else -> {
                    top = stack.pop()
                    when (top) {
                        cellID -> {
                            cellID = stack.pop()
                            stack.push(cellID)
                        }
                    }
                }
            }
        }
    }
}


