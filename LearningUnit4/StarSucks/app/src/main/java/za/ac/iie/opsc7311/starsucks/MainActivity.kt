package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import za.ac.iie.opsc7311.starsucks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var order = Order()

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
            R.id.img_sb1 -> order.productName = "MMM Soy Latte"
            R.id.img_sb2 -> order.productName = "MMM Chocco Frapp"
            R.id.img_sb3 -> order.productName = "MMM Bottled Americano"
            R.id.img_sb4 -> order.productName = "MMM Rainbow Frapp"
            R.id.img_sb5 -> order.productName = "MMM Caramel Frapp"
            R.id.img_sb6 -> order.productName = "MMM Black Forest Frapp"
        }
        Toast.makeText(this@MainActivity,
            order.productName, Toast.LENGTH_SHORT).show()
        openIntent(applicationContext, order.productName,
            OrderDetailsActivity::class.java)
    }
}