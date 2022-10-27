package za.ac.iie.opsc7311.starsucks

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import za.ac.iie.opsc7311.starsucks.databinding.ActivityOrderDetailsBinding
import java.util.*

class OrderDetailsActivity : AppCompatActivity() {
    var order = Order()
    val database = Firebase.database
    // add orders to the path
    val starSucksRef = database.getReference("orders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the name of the ordered product from the intent
        order.productName = intent.getStringExtra("order").toString()

        // set the product name on the text view
        binding.tvPlacedOrder.text = order.productName

        when(order.productName) {
            "Soy Latte" -> binding.imgOrderedBeverage.setImageResource(R.drawable.sb1)
            "Chocco Frapp" -> binding.imgOrderedBeverage.setImageResource(R.drawable.sb2)
            "Bottled Americano" -> binding.imgOrderedBeverage.setImageResource(R.drawable.sb3)
            "Rainbow Frapp" -> binding.imgOrderedBeverage.setImageResource(R.drawable.sb4)
            "Caramel Frapp" -> binding.imgOrderedBeverage.setImageResource(R.drawable.sb5)
            "Black Forest Frapp" -> binding.imgOrderedBeverage.setImageResource(R.drawable.sb6)
        }

        binding.fabOrder.setOnClickListener() {
            shareIntent(applicationContext, order.productName)
        }

        binding.fabCalendar.setOnClickListener() {
            // Create a calendar to get today's date
            val datePickerCalendar = Calendar.getInstance()
            val year = datePickerCalendar.get(Calendar.YEAR)
            val month = datePickerCalendar.get(Calendar.MONTH)
            val day = datePickerCalendar.get(Calendar.DAY_OF_MONTH)

            // Show a date picker, starting from today's date
            val listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    order.orderDate = "${year}-${month+1}-${day}"
                }
            }
            var ordersDatePicker = DatePickerDialog(this@OrderDetailsActivity,
                                            listener, year, month, day)
            ordersDatePicker.show()
        }

        binding.fabCloud.setOnClickListener() {
            order.customerName = binding.etCustomerName.text.toString()
            order.customerCell = binding.etCustomerCell.text.toString()

            // check that no data is missing
            if (!order.customerName.isNullOrBlank() && !order.customerCell.isNullOrBlank() &&
                    !order.orderDate.isNullOrEmpty() && !order.productName.isNullOrBlank()) {

                // add the order to the list of orders
                starSucksRef.push().setValue(order)
            } else {
                // message to display to the user if something is missing
                Toast.makeText(this@OrderDetailsActivity,
                    "Please complete all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}