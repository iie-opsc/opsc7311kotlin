package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import za.ac.iie.opsc7311.starsucks.databinding.ActivityMainWithNavDrawerBinding

class MainActivity : AppCompatActivity(), View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

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

        binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_photo -> openIntent(applicationContext, "",
                                CoffeeSnapsActivity::class.java)
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        // returning true marks the item as selected
        return true
    }
}