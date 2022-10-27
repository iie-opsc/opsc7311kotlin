package za.ac.iie.opsc7311.starsucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import za.ac.iie.opsc7311.starsucks.databinding.ActivityOrderHistoryBinding

class OrderHistoryActivity : AppCompatActivity() {
    val database = Firebase.database
    // add orders to the path
    val starSucksRef = database.getReference("orders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Read from the database
        starSucksRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var list = mutableListOf<Order>()

                // iterate over the children in the list
                for (pulledOrder in snapshot.children) {
                    val order : Order? = pulledOrder.getValue(Order::class.java)
                    if (order != null) {
                        list.add(order)
                    }
                }

                // create the adapter to display the items
                var orderAdapter = ArrayAdapter(this@OrderHistoryActivity,
                    android.R.layout.simple_list_item_1, list)
                binding.lstvOrderHistory.adapter = orderAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@OrderHistoryActivity,
                    "Error reading from database", Toast.LENGTH_SHORT).show()
            }
        })
    }
}