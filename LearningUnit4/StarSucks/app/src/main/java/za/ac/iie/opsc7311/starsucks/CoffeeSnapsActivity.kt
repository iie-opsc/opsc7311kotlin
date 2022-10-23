package za.ac.iie.opsc7311.starsucks

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import za.ac.iie.opsc7311.starsucks.databinding.ActivityCoffeeSnapsBinding

class CoffeeSnapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeSnapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffeeSnapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activity result launcher that will wait for the photo result
        val getResult = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if(it.resultCode == Activity.RESULT_OK && it.data != null) {
                    var bitmap = it.data!!.extras?.get("data") as Bitmap
                    binding.imgCameraImage.setImageBitmap(bitmap)
                }
            }

        binding.photoFab.setOnClickListener() {
            // create the intent
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // launch the intent
            getResult.launch(intent)
        }
    }
}