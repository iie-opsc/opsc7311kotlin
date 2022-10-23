package za.ac.iie.opsc7311.starsucks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import za.ac.iie.opsc7311.starsucks.databinding.ActivityCoffeeSnapsBinding
import za.ac.iie.opsc7311.starsucks.databinding.ActivityMainWithNavDrawerBinding

class CoffeeSnapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeSnapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffeeSnapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}