package com.xilv.addnewitemrecview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xilv.addnewitemrecview.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    private var indexImage = 0
    private var imageId = R.drawable.maestro1

    private val imageIdList = listOf(
        R.drawable.maestro1,
        R.drawable.maestro2,
        R.drawable.maestro3,
        R.drawable.maestro4,
        R.drawable.maestro5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }

    /*
    специальная функция для пролистывания image, добавления title и description
     */
    private fun initButtons() = with(binding) {
        // "пролистывание" image для выбора
        buttonNext.setOnClickListener {
            indexImage++
            if (indexImage > imageIdList.size - 1) indexImage = 0
            imageId = imageIdList[indexImage]
            imageView.setImageResource(imageId)
        }

        // передача результата (image, title, description) через Intent
        buttonDone.setOnClickListener {
            val maestro = Maestro(imageId, editTitle.text.toString(),
                editDescription.text.toString())
            val editIntent = Intent().apply {
                putExtra("maestro", maestro) // передаем ключевое слово и переменную
            }
            setResult(RESULT_OK, editIntent)
            finish()
        }
    }
}