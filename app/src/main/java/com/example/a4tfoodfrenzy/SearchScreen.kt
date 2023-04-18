package com.example.a4tfoodfrenzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a4tfoodfrenzy.model.RecipeCategorySuggest
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchScreen : AppCompatActivity() {
    var adapterTypeRecipeRV: RecipeCateListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)

        val cateRecipeRV = findViewById<RecyclerView>(R.id.cateRecipeRV)
        var cateRecipeList = generateCateRecipeData() //implemened below
        adapterTypeRecipeRV = RecipeCateListAdapter(cateRecipeList, false, true)
        cateRecipeRV!!.layoutManager = GridLayoutManager(this, 3)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        cateRecipeRV!!.addItemDecoration(GridSpacingItemDecoration(spacingInPixels))

        cateRecipeRV!!.adapter = adapterTypeRecipeRV

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botNavbar)
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

    private fun generateCateRecipeData(): ArrayList<RecipeCategorySuggest> {
        var result = ArrayList<RecipeCategorySuggest>()

        var cateRecipe = RecipeCategorySuggest("Đồ uống",R.drawable.drink)
        result.add(cateRecipe)

        cateRecipe = RecipeCategorySuggest("Món gà",R.drawable.chicken)
        result.add(cateRecipe)

        cateRecipe = RecipeCategorySuggest("Nấu nhanh",R.drawable.time)
        result.add(cateRecipe)

        cateRecipe = RecipeCategorySuggest("Đồ ăn vặt",R.drawable.fastfood)
        result.add(cateRecipe)

        cateRecipe = RecipeCategorySuggest("Đồ chay",R.drawable.diet)
        result.add(cateRecipe)

        cateRecipe = RecipeCategorySuggest("Món chính",R.drawable.mainfood)
        result.add(cateRecipe)

        return result
    }
}