package za.ac.iie.opsc7311.birdiegame

import android.content.Context
import android.graphics.*
import android.view.View

class BirdieGameView(context: Context?) : View(context) {

    private var bird : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.wingsup)
    private var birdDown : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.wingsdown)
    private var greengrassbackground : Bitmap = BitmapFactory.decodeResource(resources,
        R.drawable.villagescreensize)
    private var pugicorn : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.puggicorn)
    private var cuppicake : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cuppicake)
    private var lifealive : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.smallalive)
    private var lifedead : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.smalldead)

    private var scoreCounter = Paint()
    private var levelCounter = Paint()

    init {
        scoreCounter.color = Color.BLACK
        scoreCounter.textSize = 30f
        scoreCounter.typeface = Typeface.DEFAULT_BOLD
        scoreCounter.isAntiAlias = true

        levelCounter.color = Color.BLUE
        levelCounter.textSize = 30f
        levelCounter.typeface = Typeface.DEFAULT_BOLD
        levelCounter.textAlign = Paint.Align.CENTER
        levelCounter.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // take careful note of the order here
        canvas?.drawBitmap(greengrassbackground, 0f, 0f, null)
        canvas?.drawBitmap(bird, 0.0f, 0.0f, null)

        // draw the score and level
        canvas?.drawText("Score: 0", 20f, 60f, scoreCounter)
        canvas?.drawText("Level 1", canvas.width.toFloat()/2, 60f, levelCounter)

        // display the icons for the lives
        canvas?.drawBitmap(lifealive, canvas.width.toFloat() - 300, 30f, null)
        canvas?.drawBitmap(lifealive, canvas.width.toFloat() - 200, 30f, null)
        canvas?.drawBitmap(lifealive, canvas.width.toFloat() - 100, 30f, null)
    }
}