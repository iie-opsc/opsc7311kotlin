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
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import za.ac.iie.opsc7311.starsucks.databinding.ActivityCoffeeSnapsBinding
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CoffeeSnapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeSnapsBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraSelector: CameraSelector
    private var imageCapture: ImageCapture? = null
    private lateinit var imgCaptureExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffeeSnapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the executor
        imgCaptureExecutor = Executors.newSingleThreadExecutor()

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

        // Add the code to the button to take the photo
        // Based on https://developer.android.com/training/camerax/take-photo and
        // https://www.section.io/engineering-education/how-to-implement-camerax-api-in-android/
        binding.photoFab.setOnClickListener() {
            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
                File(externalMediaDirs[0],"Coffee_${System.currentTimeMillis()}")).build()
            imageCapture?.takePicture(outputFileOptions, imgCaptureExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(error: ImageCaptureException) {
                    }
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        Log.d("CoffeeSnapsActivity",
                            "Photo saved to ${outputFileResults.savedUri}")
                        // With thanks to the tutorial at
                        // https://www.tutorialkart.com/kotlin-android/original-thread-created-view-hierarchy-can-touch-views/
                        this@CoffeeSnapsActivity.runOnUiThread(java.lang.Runnable {
                            binding.imgSavedPhoto.setImageURI(outputFileResults.savedUri)
                        })
                    }
                })
        }
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
            imageCapture = ImageCapture.Builder().build()
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview,
                    imageCapture)
            } catch (e: Exception) {
                Log.d("CoffeeSnapsActivity", "Use case binding failed")
            }

        }, ContextCompat.getMainExecutor(this))
    }
}