package za.ac.iie.opsc7311.starsucks

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import za.ac.iie.opsc7311.starsucks.databinding.ActivityOrderHistoryBinding

class OrderHistoryActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Read the data from the database and display it
        db.collection("orders")
            .get()
            .addOnSuccessListener { result ->
                var list = mutableListOf<Order>()

                // iterate over the children in the list
                for (document in result) {
                    Log.d("Order History", "${document.id} => ${document.data}")
                    list.add(document.toObject<Order>())
                }

                // create the adapter to display the items
                var orderAdapter = ArrayAdapter(this@OrderHistoryActivity,
                    R.layout.simple_list_item_1, list)
                binding.lstvOrderHistory.adapter = orderAdapter
            }
            .addOnFailureListener { exception ->
                Log.w("Order History", "Error getting documents.", exception)
            }
    }
}