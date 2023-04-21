package com.example.a4tfoodfrenzy.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a4tfoodfrenzy.*
import com.example.a4tfoodfrenzy.Adapter.RecipeListAdapter
import com.example.a4tfoodfrenzy.Model.FoodRecipe
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList

class AfterSearchActivity : AppCompatActivity() {
    var adapterRecipeAfterSearchRV: RecipeListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_search)

        val recipeAfterSearchRV = findViewById<RecyclerView>(R.id.recipeAfterSearchRV)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botNavbar)
        findViewById<ImageView>(R.id.imgBack).setOnClickListener {
            val intent = Intent(this, SearchScreen::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.imgFilter).setOnClickListener {
            val intent = Intent(this, SortRecipeActivity::class.java)
            startActivity(intent)
        }

        var recipeAfterSearch = generateRecipeTodayEatData() //implemened below
        adapterRecipeAfterSearchRV = RecipeListAdapter(this,recipeAfterSearch)
        recipeAfterSearchRV!!.adapter = adapterRecipeAfterSearchRV
        recipeAfterSearchRV!!.layoutManager = GridLayoutManager(this, 3)
        adapterRecipeAfterSearchRV!!.onItemClick = { foodRecipe, i ->
            val intent = Intent(this, ShowRecipeDetailsActivity::class.java)
            startActivity(intent)
        }

        val menu = bottomNavigationView.menu

        menu.findItem(R.id.search).isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.search -> {
                    val intent = Intent(this, SearchScreen::class.java)
                    startActivity(intent)
                    true
                }
                R.id.addRecipe -> {
                    val intent = Intent(this, AddNewRecipe::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun generateRecipeTodayEatData(): ArrayList<FoodRecipe> {
        var result = ArrayList<FoodRecipe>()

        var foodRecipe: FoodRecipe = FoodRecipe(1, "Canh khổ qua nhồi thịt",
            "khoquanhoithit", 2, "15 phút",
            Date(2022, 2,2), true,
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
        result.add(foodRecipe)

        foodRecipe = FoodRecipe(1, "Canh chua cá lóc","canhcaloc", 2, "15 phút",
            Date(2022, 2,2), true,
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
        result.add(foodRecipe)


        foodRecipe = FoodRecipe(1, "Bò sốt me", "bosotme", 2, "15 phút",
            Date(2022, 2,2), true,
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
        result.add(foodRecipe)

        foodRecipe = FoodRecipe(1, "Bò kho", "bokho", 2, "15 phút",
            Date(2022, 2,2), true,
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
        result.add(foodRecipe)

        foodRecipe = FoodRecipe(1, "Bún bò huế", "bunbohue", 2, "15 phút",
            Date(2022, 2,2), true,
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
        result.add(foodRecipe)

        foodRecipe = FoodRecipe(1, "Bưởi trộn khô gà", "buoitronkhoga", 2, "15 phút",
            Date(2022, 2,2), true,
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
        result.add(foodRecipe)

        return result
    }
}
