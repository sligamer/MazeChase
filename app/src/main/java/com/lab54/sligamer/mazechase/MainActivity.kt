package com.lab54.sligamer.mazechase

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    //DECLARE PRIVATE VARIABLES
    private lateinit var relativeLayout: ConstraintLayout

    private lateinit var pig: ImageView
    private var xPos: Float = 0F
    private var yPos: Float = 0F
    private var maze: MazeCanvas? = null
    private var cellId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xPos = 10F
        yPos = 10F
        cellId = 22

        // CONSTRUCT THE MAZE AND ADD IT TO THE RELATIVE LAYOUT
        maze = MazeCanvas(this)
        relativeLayout = findViewById(R.id.constraintLayout)
        relativeLayout.addView(maze, 0)

        // CREATE A LAYOUT INFLATER
        // kotlin takes care of the layoutInflater

        // SET THE BACKGROUND OF THE IMAGEVIEW TO THE PIG ANIMATION
        pig = layoutInflater.inflate(R.layout.pig_view, null) as ImageView
        pig.setBackgroundResource(R.drawable.pig_animation)

        // CREATE AN ANIMATION DRAWABLE OBJECT BASED ON THIS BACKGROUND
        var pigAnimate = pig.background as AnimationDrawable
        pigAnimate.start()

        pig.x = xPos
        pig.y = yPos
        pig.scaleX = .15f
        pig.scaleY = .15f

        relativeLayout.addView(pig,1)

    }

    fun goUp(view: View)
    {
        when {
            !maze!!.board[cellId]!!.north -> {
                yPos -=100
                pig.y = yPos
                cellId -= maze!!.COLS
            }
        }
    }

    fun goLeft(view: View)
    {
        when {
            !maze!!.board[cellId]!!.west -> {
                xPos -=100
                pig.x = xPos
                cellId--
            }
        }
    }

    fun goRight(view: View)
    {
        when {
            !maze!!.board[cellId]!!.east -> {
                xPos +=100
                pig.x = xPos
                cellId++
            }
        }
    }

    fun goDown(view: View)
    {
        when {
            !maze!!.board[cellId]!!.south -> {
                yPos +=100
                pig.y = yPos
                cellId += maze!!.COLS
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if(id == R.id.action_settings){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
