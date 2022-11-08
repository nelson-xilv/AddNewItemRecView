package com.xilv.addnewitemrecview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.xilv.addnewitemrecview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter = MaestroAdapter()
    private var editLauncher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        supportActionBar - это и есть Toolbar
        setDisplayHomeAsUpEnabled - активирует стрелку, которая находится в Toolbar
         */
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        /*
        title позволяет изменить надпись в Toolbar
         */
        supportActionBar?.title = "Maestro"

        init()

        /*
        инициализируем переменную с помощью callback (слушатель)
         */
        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) { // проверяем данные
                adapter.addMaestro(it.data?.getSerializableExtra("maestro") as Maestro)
            }
        }
    }

    /*
    данная функция позволяет загрузить main_menu в память
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    /*
    слушатель нажатий для элементов Toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // прослушиваем нажатия на элементы в Toolbar
        when (item.itemId) {
            // android.R.id.home -> finish()
            R.id.refresh_menu -> {
                Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show()
            }
            R.id.delete_menu -> {
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            }
            R.id.search_menu -> {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            }
            R.id.save_menu -> {
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter

            // запускаем с помощью buttonAdd новое активити
            buttonAdd.setOnClickListener {
                editLauncher?.launch(
                    Intent(this@MainActivity, EditActivity::class.java))
            }
        }
    }
}