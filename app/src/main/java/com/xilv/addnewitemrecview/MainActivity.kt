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
        выбирает элемент из BottomNavigationView (в данном случае notes) по-умолчанию
         */
        binding.bottomNavMenu.selectedItemId = R.id.bn_notes

        /*
        supportActionBar - это и есть Toolbar
        setDisplayHomeAsUpEnabled - активирует стрелку, которая находится в Toolbar
         */
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        /*
        title позволяет изменить надпись в Toolbar
         */
        supportActionBar?.title = "Maestro"

        /*
        инициализируем переменную с помощью callback (слушатель)
         */
        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) { // проверяем данные
                adapter.addMaestro(it.data?.getSerializableExtra("maestro") as Maestro)
            }
        }

        init()
        setButtonNavigationView()
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

    /*
    в данной функции инициализируется RecyclerView
     */
    private fun init() {
        binding.apply {
            /*
            настраиваем какого типа будет RecyclerView (в данном случае тип выбран "сетчатый"
            с 3 элементами в 1-ой строке
             */
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter // присваиваем adapter

            // запускаем с помощью buttonAdd новое активити
            buttonAdd.setOnClickListener {
                editLauncher?.launch(
                    Intent(this@MainActivity, EditActivity::class.java))
            }
        }
    }

    /*
    при нажатии на элемент из BottomNavigationView запустится слушатель (внизу) и выдаст переменную,
    которая будет содержать в себе item, на который нажал пользователь
     */
    private fun setButtonNavigationView() {
        binding.bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bn_notes -> {
                    Toast.makeText(this, "Note", Toast.LENGTH_SHORT).show()
                }
                R.id.bn_favorites -> {
                    Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
                }
                R.id.bn_info -> {
                    Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

}