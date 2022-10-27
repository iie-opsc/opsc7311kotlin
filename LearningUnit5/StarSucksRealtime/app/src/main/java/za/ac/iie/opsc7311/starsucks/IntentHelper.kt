package za.ac.iie.opsc7311.starsucks

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun openIntent(context: Context, order: String,
               activityToOpen: Class<*>) {
    // declare intent with context and class to pass the value to
    val intent = Intent(context, activityToOpen)
    // pass through the string value with key "order"
    intent.putExtra("order", order)
    // start the activity
    context.startActivity(intent)
}

fun shareIntent(context: Context, order: String) {
    var sendIntent = Intent()
    // set the action to indicate what to do - send in this case
    sendIntent.setAction(Intent.ACTION_SEND)
    sendIntent.putExtra(Intent.EXTRA_TEXT, order)
    // we are sending plain text
    sendIntent.setType("text/plain")
    // show the share sheet
    var shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

fun shareIntent(context: Context, order: Order) {
    var sendIntent = Intent()
    sendIntent.setAction(Intent.ACTION_SEND)

    // create a bundle and add multiple values to it
    var shareOrderDetails = Bundle()
    shareOrderDetails.putString("productName", order.productName)
    shareOrderDetails.putString("customerName", order.customerName)
    shareOrderDetails.putString("customerCell", order.customerCell)

    // share the whole bundle
    sendIntent.putExtra(Intent.EXTRA_TEXT, shareOrderDetails)
    sendIntent.setType("text/plain")

    var shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}