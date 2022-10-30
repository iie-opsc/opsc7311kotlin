package za.ac.iie.opsc7311.birdiegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var birdieGameView : BirdieGameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        birdieGameView = BirdieGameView(this)
        setContentView(birdieGameView)
    }
}