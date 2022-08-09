package com.emresari.cyrpto_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.emresari.cyrpto_app.R
import com.emresari.cyrpto_app.adapter.CryptoAdapter
import com.emresari.cyrpto_app.databinding.ActivityMainBinding
import com.emresari.cyrpto_app.model.CryptoModel
import com.emresari.cyrpto_app.service.CryptoApi
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var Base_data: String = "https://raw.githubusercontent.com/"
    private var array: ArrayList<CryptoModel>? = null
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CryptoAdapter
    private var compositeDisposable:CompositeDisposable?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        compositeDisposable= CompositeDisposable()

        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        loadData()

//

    }

    fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_data)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoApi::class.java)

        compositeDisposable!!.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
        /*
        val call=service.getData()
        call.enqueue(object :Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {

                        array= ArrayList(it)
                        adapter=CryptoAdapter(array!!)
                        binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                       binding.recyclerView.adapter=adapter

                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }*/


    }fun handleResponse(arrayList: List<CryptoModel>){
        array= ArrayList(arrayList)
        adapter=CryptoAdapter(array!!)
        binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter=adapter


    }

    override fun onDestroy() {
        compositeDisposable!!.clear()
        super.onDestroy()
    }


}