package za.ac.iie.opsc7311.birdiegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var birdieGameView : BirdieGameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        birdieGameView = BirdieGameView(this)
        setContentView(birdieGameView)

        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.scheduleAtFixedRate(
            {
                birdieGameView.invalidate()
            }, 0, 30, TimeUnit.MICROSECONDS)
    }
}