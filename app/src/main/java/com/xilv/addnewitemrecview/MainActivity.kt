package com.xilv.addnewitemrecview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.xilv.addnewitemrecview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter = MaestroAdapter()
    private val imageIdList = listOf(
        R.drawable.maestro1,
        R.drawable.maestro2,
        R.drawable.maestro3,
        R.drawable.maestro4,
        R.drawable.maestro5
    )
    private var index = 0
    private var indexMaestro = index

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            buttonAdd.setOnClickListener {
                indexMaestro++
                if (index > 4) index = 0

                val maestro = Maestro(imageIdList[index], "Maestro $indexMaestro")
                adapter.addMaestro(maestro)
                index++
            }
        }
    }
}