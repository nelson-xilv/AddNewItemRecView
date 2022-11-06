package com.xilv.addnewitemrecview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xilv.addnewitemrecview.databinding.MaestroItemBinding

/*
адаптер - это некий "посредник" между RecyclerView и данными
 */
class MaestroAdapter: RecyclerView.Adapter<MaestroAdapter.MaestroHolder>() {

    // список для заполнения view элементами
    private val maestroList = ArrayList<Maestro>()

    class MaestroHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = MaestroItemBinding.bind(item)

        fun bind(maestro: Maestro) = with(binding) {
            imView.setImageResource(maestro.imageId)
            tvTitle.text = maestro.title
        }
    }

    /*
    когда адаптер начинает заполнять шаблоны и выводит их, самое первое, что он делает - это
    "надувает" View, т.е. загружает его в память, после чего запускается след. метод
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaestroHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.maestro_item,
            parent, false)
        return MaestroHolder(view)
    }

    /*
    здесь view, который находится в памяти, заполняется, т.е. в данном случае заполняется
    image и title
     */
    override fun onBindViewHolder(holder: MaestroHolder, position: Int) {
        holder.bind(maestroList[position]) // заполняем с помощью ArrayList элементами
    }

    // тут передается размер нашего массива, чтобы адаптер знал сколько нужно запустить функции выше
    override fun getItemCount(): Int {
        return maestroList.size
    }

    // добавляем в список новые элементы
    @SuppressLint("NotifyDataSetChanged")
    fun addMaestro(maestro: Maestro) {
        maestroList.add(maestro)
        notifyDataSetChanged() // данная функция "уведомляет" адаптер, что данные внутри изменились
    }

}

