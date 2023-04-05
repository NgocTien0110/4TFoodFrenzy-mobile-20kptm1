package com.example.a4tfoodfrenzy

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListIngredientAdapter(context: Context, list:ArrayList<Ingredient>)
    : RecyclerView.Adapter<ListIngredientAdapter.ViewHolder>() {
    private var listItem=list
    private val context=context
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val name: TextView =listItemView.findViewById(R.id.name)
        val amount: TextView =listItemView.findViewById(R.id.amount)
        val menu:ImageView=listItemView.findViewById(R.id.optionMenu)

        init{
            menu.setOnClickListener {
                popMenus(it)
            }
        }

    }
    private fun popMenus(v:View)
    {
        val popupMenu = PopupMenu(context, v)
        popupMenu.menuInflater.inflate(R.menu.menu_ingredient, popupMenu.menu)
        popupMenu.show()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListIngredientAdapter.ViewHolder {
        val context=parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.list_ingredient, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ListIngredientAdapter.ViewHolder, position: Int) {
        val item=listItem[position]
        holder.name.text=item.nameIngredient
        holder.amount.text="${item.amount} g"
    }

}
