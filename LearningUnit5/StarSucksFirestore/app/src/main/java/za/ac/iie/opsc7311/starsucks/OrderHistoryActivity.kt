package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import za.ac.iie.opsc7311.starsucks.databinding.ActivityOrderHistoryBinding

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO Read from database
    }
}