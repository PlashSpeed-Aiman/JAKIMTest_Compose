package com.example.jakimtest
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.HttpResponse
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.util.*
import java.net.*
@Serializable
data class PrayerTime(var hijri: String="",var date: String ="",var day:String ="",var prayer_times : Dictionary<String,String> )
class PrayerTimeExtract {
    var awqat:PrayerTime // awqat refers to prayer times in arabic
    init{
        awqat = PrayerTime("","","", Hashtable<String,String>())
    }
    //will implement httprequest using coroutines
    val jsonstring = "{\"prayerTime\":[{\"hijri\":\"1443-08-16\",\"date\":\"19-Mar-2022\",\"day\":\"Saturday\",\"imsak\":\"06:01:00\",\"fajr\":\"06:11:00\",\"syuruk\":\"07:17:00\",\"dhuhr\":\"13:24:00\",\"asr\":\"16:30:00\",\"maghrib\":\"19:26:00\",\"isha\":\"20:35:00\"}],\"status\":\"OK!\",\"serverTime\":\"2022-03-19 01:28:56\",\"periodType\":\"today\",\"lang\":\"ms_my\",\"zone\":\"SGR01\",\"bearing\":\"291&#176; 7&#8242; 23&#8243;\"}\n"
    //        val response: HttpResponse = client.get("https://www.e-solat.gov.my/index.php?r=esolatApi/takwimsolat&period=today&zone=SGR01")
    val stringBody: String = jsonstring
    val jsonObject_val = Json.parseToJsonElement(stringBody).jsonObject["prayerTime"]!!
    fun processJSON(): PrayerTime {
        for(item in jsonObject_val.jsonArray[0].jsonObject.iterator()){
            if(item.key != "hijri" && item.key != "date" && item.key != "day" )
                awqat.prayer_times.put(item.key,item.value.toString())
        }
        awqat.hijri = jsonObject_val.jsonArray[0].jsonObject["hijri"].toString()
        awqat.date = jsonObject_val.jsonArray[0].jsonObject["date"].toString()
        awqat.day = jsonObject_val.jsonArray[0].jsonObject["day"].toString()
        return awqat
    }



}