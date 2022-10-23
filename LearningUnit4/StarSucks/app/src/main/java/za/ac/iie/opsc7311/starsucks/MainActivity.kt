package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import za.ac.iie.opsc7311.starsucks.databinding.ActivityMainWithNavDrawerBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var order = Order()
    private lateinit var binding: ActivityMainWithNavDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWithNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgSb1.setOnClickListener(this)
        binding.imgSb2.setOnClickListener(this)
        binding.imgSb3.setOnClickListener(this)
        binding.imgSb4.setOnClickListener(this)
        binding.imgSb5.setOnClickListener(this)
        binding.imgSb6.setOnClickListener(this)

        setSupportActionBar(binding.navToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        var toggleOnOff = ActionBarDrawerToggle(this,
            binding.drawerLayout, binding.navToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggleOnOff)
        toggleOnOff.syncState()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_sb1 -> order.productName = "Soy Latte"
            R.id.img_sb2 -> order.productName = "Chocco Frapp"
            R.id.img_sb3 -> order.productName = "Bottled Americano"
            R.id.img_sb4 -> order.productName = "Rainbow Frapp"
            R.id.img_sb5 -> order.productName = "Caramel Frapp"
            R.id.img_sb6 -> order.productName = "Black Forest Frapp"
        }
        Toast.makeText(this@MainActivity,
            "MMM " + order.productName, Toast.LENGTH_SHORT).show()
        openIntent(applicationContext, order.productName,
            OrderDetailsActivity::class.java)
    }

    override fun onBackPressed() {
        // if the drawer is open, close it
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // otherwise, let the super class handle it
            super.onBackPressed()
        }
    }
}