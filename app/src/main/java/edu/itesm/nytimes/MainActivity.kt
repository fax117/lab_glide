package edu.itesm.nytimes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.nytimes.com/svc/books/v3/lists/current/"
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<BooksAdapter.ViewHolder>
    private lateinit var results: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = LinearLayoutManager(this)
        getAllData()
        Toast.makeText(applicationContext, "OnCreate!", Toast.LENGTH_LONG).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private lateinit var books: List<Book>
    fun getAllData(){
        val callToService = getRetrofit().create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val responseFromService = callToService.getBooks()
            runOnUiThread {
                results = responseFromService.body() as Results

                if (responseFromService.isSuccessful) {
                    Log.i("Books", results.results?.books.toString())
                    books = results.results?.books
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                        layoutManager = manager
                        myAdapter = BooksAdapter(books)
                        adapter = myAdapter
                    }
                } else {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    fun onViewCreated(){
        Toast.makeText(applicationContext, "onViewCreated!", Toast.LENGTH_LONG).show()
    }

    fun onViewStateRestored(){
        Toast.makeText(applicationContext, "onViewStateRestored!", Toast.LENGTH_LONG).show()
    }

    override fun onStart(){
        super.onStart()
        Toast.makeText(applicationContext, "onStart!", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume!", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause!", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop!", Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(applicationContext, "onSaveInstanceState!", Toast.LENGTH_LONG).show()
    }

    fun onDestroyView(){
        Toast.makeText(applicationContext, "onDestroyView!", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy!", Toast.LENGTH_LONG).show()
    }
}