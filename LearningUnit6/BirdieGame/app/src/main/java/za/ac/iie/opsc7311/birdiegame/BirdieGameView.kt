package za.ac.iie.opsc7311.birdiegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View

class BirdieGameView(context: Context?) : View(context) {

    private var bird : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.wingsup)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bird, 0.0f, 0.0f, null)
    }
}