package com.example.asd.hotels

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asd.hotels.dummy.HotelData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main_sorted.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val hotelValues = mutableListOf<HotelData>();
        var idCounter = 0
        var hotelNames = listOf("Alpha", "Beta", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India")
        for (i in 0..10) {
            hotelValues.add(HotelData(idCounter, 1, "Graz", R.drawable.untitled, hotelNames.get((0..9).random()), (0..100).random(), (0..100).random(), "Gutes Hotel!", (0..10).random(), (1..5).random()))
            idCounter++
        }


        var overViewVisible = true
        sort_button_price.setOnClickListener {
            //add fragment to fill sorting view
            //supportFragmentManager.beginTransaction()
            //    .add(R.id.SortingView, MainFragment.newInstance(), "hotelList").commit()

            var hotelDummyListSorted = hotelValues.sortedBy { it.price }
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelDummyListSorted)
            }
            if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                overViewVisible = false
            } else {
                OverView.setVisibility(View.VISIBLE)
                SortingView.setVisibility(View.GONE)
                overViewVisible = true
            }
        }
        sort_button_rating.setOnClickListener {
            //add fragment to fill sorting view
            //supportFragmentManager.beginTransaction()
            //    .add(R.id.SortingView, MainFragment.newInstance(), "hotelList").commit()

            var hotelDummyListSorted = hotelValues.sortedBy { it.hotel_rating }
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelDummyListSorted)
            }
            if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                overViewVisible = false
            } else {
                OverView.setVisibility(View.VISIBLE)
                SortingView.setVisibility(View.GONE)
                overViewVisible = true
            }
        }
        sort_button_stars.setOnClickListener {
            //add fragment to fill sorting view
            //supportFragmentManager.beginTransaction()
            //    .add(R.id.SortingView, MainFragment.newInstance(), "hotelList").commit()

            var hotelDummyListSorted = hotelValues.sortedBy { it.hotel_stars }
            SortingView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SortingViewAdapter(hotelDummyListSorted)
            }
            if (overViewVisible) {
                OverView.setVisibility(View.INVISIBLE)
                SortingView.setVisibility(View.VISIBLE)
                overViewVisible = false
            } else {
                OverView.setVisibility(View.VISIBLE)
                SortingView.setVisibility(View.GONE)
                overViewVisible = true
            }
        }


        // Setting up the adapter
        OverView.apply {
            // Set up the layer
            layoutManager = LinearLayoutManager(this@MainActivity)
            // Pass the list into OverViewAdapter
            adapter = OverViewAdapter(hotelValues) {
                d("MainActivity", "Hi from main")
                // Call the detail view.
                startActivity(
                    Intent(
                        this@MainActivity,
                        HotelDetailActivity::class.java
                    )
                )
            }
        }

        // Add item separator to the overview.
        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        OverView.addItemDecoration(itemDecor)

        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class Hotel(){
        var hotel_price = 0;
        var location_id = 0;
    }

    fun inPriceRange(minPrice:Int, maxPrice:Int, hotel:Hotel):Boolean{
        var statePriceR:Boolean = false;
        if((hotel.hotel_price<=maxPrice) && (hotel.hotel_price>=minPrice))
            statePriceR = true;
        return statePriceR;
    }
    fun filterbyPrice(hotelsToFilter:MutableList<Hotel>){
        hotelsToFilter.forEach { hotel_inList->
            //minPrice, maxPrice form RangeSeekBar
            if(!inPriceRange(0, 100, hotel_inList))
                hotelsToFilter.remove(hotel_inList);
        }
    }

    fun inLocation(location_id:Int, hotel:Hotel):Boolean{
        var stateLocation:Boolean = false;
        if(hotel.location_id== location_id)
            stateLocation = true;
        return stateLocation;
    }
    fun filterbyLocation(){

    }





    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String
//
//    companion object {
//
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}
