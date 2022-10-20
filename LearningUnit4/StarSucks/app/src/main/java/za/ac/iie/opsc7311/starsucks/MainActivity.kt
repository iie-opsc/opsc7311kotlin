package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import za.ac.iie.opsc7311.starsucks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgSb1.setOnClickListener(this)
        binding.imgSb2.setOnClickListener(this)
        binding.imgSb3.setOnClickListener(this)
        binding.imgSb4.setOnClickListener(this)
        binding.imgSb5.setOnClickListener(this)
        binding.imgSb6.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_sb1 -> Toast.makeText(this@MainActivity,
                "MMM Soy Latte", Toast.LENGTH_SHORT).show()
            R.id.img_sb2 -> Toast.makeText(this@MainActivity,
                "MMM Chocco Frapp", Toast.LENGTH_SHORT).show()
            R.id.img_sb3 -> Toast.makeText(this@MainActivity,
                "MMM Bottled Americano", Toast.LENGTH_SHORT).show()
            R.id.img_sb4 -> Toast.makeText(this@MainActivity,
                "MMM Rainbow Frapp", Toast.LENGTH_SHORT).show()
            R.id.img_sb5 -> Toast.makeText(this@MainActivity,
                "MMM Caramel Frapp", Toast.LENGTH_SHORT).show()
            R.id.img_sb6 -> Toast.makeText(this@MainActivity,
                "MMM Black Forest Frapp", Toast.LENGTH_SHORT).show()
        }
    }
}