package com.acstudio.foodscanner

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

//TODO: Make it a suspend function
object Utils {
     fun computeString(jsonString: String) : String {
        val jObject = JSONObject(jsonString)
        val status = jObject.getInt("status")
        return if(status  == 1) {
            jObject.getJSONObject("product").getString("product_name")
        } else {
            "Cannot find the product"
        }
    }
}