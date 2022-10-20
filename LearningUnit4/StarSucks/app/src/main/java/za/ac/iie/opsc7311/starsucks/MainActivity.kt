package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    // declare fields for ImageViews
    lateinit var img_Sb1: ImageView
    lateinit var img_Sb2: ImageView
    lateinit var img_Sb3: ImageView
    lateinit var img_Sb4: ImageView
    lateinit var img_Sb5: ImageView
    lateinit var img_Sb6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_Sb1 = findViewById(R.id.img_sb1)
        img_Sb2 = findViewById(R.id.img_sb2)
        img_Sb3 = findViewById(R.id.img_sb3)
        img_Sb4 = findViewById(R.id.img_sb4)
        img_Sb5 = findViewById(R.id.img_sb5)
        img_Sb6 = findViewById(R.id.img_sb6)
    }
}