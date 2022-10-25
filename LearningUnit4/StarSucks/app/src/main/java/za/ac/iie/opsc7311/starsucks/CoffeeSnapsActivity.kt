package za.ac.iie.opsc7311.starsucks

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import za.ac.iie.opsc7311.starsucks.databinding.ActivityCoffeeSnapsBinding

class CoffeeSnapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeSnapsBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraSelector: CameraSelector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffeeSnapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ask for camera permission in case it wasn't granted already
        val cameraProviderResult = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) { permissionGranted->
                if (permissionGranted) {
                    // initialise the camera
                    startCamera()
                } else {
                    Toast.makeText(this@CoffeeSnapsActivity,
                        "Cannot take a photo without camera permissions",
                        Toast.LENGTH_SHORT)
                }
            }
        // start the camera
        cameraProviderResult.launch(android.Manifest.permission.CAMERA)
    }

    // With thanks to the tutorial at
    // https://www.section.io/engineering-education/how-to-implement-camerax-api-in-android/
    private fun startCamera(){
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also{
                it.setSurfaceProvider(binding.imgCameraImage.surfaceProvider)
            }
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (e: Exception) {
                Log.d("CoffeeSnapsActivity", "Use case binding failed")
            }

        }, ContextCompat.getMainExecutor(this))
    }
}