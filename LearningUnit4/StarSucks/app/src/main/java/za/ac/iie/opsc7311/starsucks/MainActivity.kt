package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import za.ac.iie.opsc7311.starsucks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgSb1.setOnClickListener() {
            Toast.makeText(this@MainActivity, "MMM Soy Latte",
                            Toast.LENGTH_SHORT).show()
        }
    }
}