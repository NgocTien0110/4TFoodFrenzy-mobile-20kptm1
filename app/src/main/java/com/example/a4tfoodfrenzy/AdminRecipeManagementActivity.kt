package com.example.a4tfoodfrenzy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.a4tfoodfrenzy.model.FoodRecipe
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class AdminRecipeManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_recipe_management)

        // xml views
        val recipeManagementRecyclerView : RecyclerView = findViewById(R.id.adminRecipeManagementRecyclerView)
        val optionAdapter : ImageView = findViewById(R.id.recipeManagementFilterImageView)
        val filterOptionTV : TextView = findViewById(R.id.recipeManagementFilterTextView)

        val recipeList = arrayListOf<FoodRecipe>()
        var adapter : RecipeManagementAdapter? = null
        val optionList = arrayListOf("Mới nhất", "Phổ biến", "Nhiều lượt thích nhất")

        recipeList.add(FoodRecipe(1, "Bò xốt me", R.drawable.bo_nuong, 2, 15, 1, "Đặng Ngọc Tiến", R.drawable.avt, 100, Date(2022, 2,2)))
        recipeList.add(FoodRecipe(1, "Bò xốt me", R.drawable.bo_nuong, 2, 15, 1, "Đặng Ngọc Tiến", R.drawable.avt, 100, Date(2022, 2,2)))
        recipeList.add(FoodRecipe(1, "Bò xốt me", R.drawable.bo_nuong, 2, 15, 1, "Đặng Ngọc Tiến", R.drawable.avt, 100, Date(2022, 2,2)))
        recipeList.add(FoodRecipe(1, "Bò xốt me", R.drawable.bo_nuong, 2, 15, 1, "Đặng Ngọc Tiến", R.drawable.avt, 100, Date(2022, 2,2)))

        // assign recipe recycler view adapter
        adapter = RecipeManagementAdapter(recipeList)
        recipeManagementRecyclerView.adapter = adapter
        recipeManagementRecyclerView.layoutManager = GridLayoutManager(this, 2)

        // assign recipe filter pop-up
        optionAdapter.setOnClickListener{
            val popUp = PopupMenu(this, optionAdapter)

            // add option string to pop-up
            for(option in optionList)
                popUp.menu.add(option)

            popUp.setOnMenuItemClickListener{ item ->
                when (item.title){
                    "Mới nhất" -> {
                        filterOptionTV.text = item.title
                        true
                    }
                    "Phổ biến" -> {
                        filterOptionTV.text = item.title
                        true
                    }
                    "Nhiều lượt thích nhất" -> {
                        filterOptionTV.text = item.title
                        true
                    }
                    else -> false
                }
            }

            popUp.show()
        }
    }
}