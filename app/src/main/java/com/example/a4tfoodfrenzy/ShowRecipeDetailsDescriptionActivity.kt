package com.example.a4tfoodfrenzy

import android.content.Context
import android.graphics.Typeface
import android.hardware.display.DisplayManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class ShowRecipeDetailsDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_recipe_details_description)

        var rv = findViewById<RecyclerView>(R.id.stepsRecyclerView)
        var stepList = arrayListOf<CookingStep>()

        stepList.add(
            CookingStep(
                "1. Bắp bò rửa sơ để ráo\n" +
                        "2. Hành tây, xà lách, cà chua rửa sạch rồi để ráo nước",
                "1. Bắp bò\n" +
                        "2.Hành tây, xà lách, cà chua",
                R.drawable.avt
            )
        )
        stepList.add(
            CookingStep(
                "1. Cho 1 muỗng canh dầu ăn vào làm nóng, chỉnh lửa vừa, phi thơm hành tỏi và cho bò vào xào trong 2 phút cho săn\n2. " +
                        "Cho gói sốt me vào đảo đều trong 3 phút. Nêm nếm lại cho vừa ăn\n3. Thêm hành tây vào xào sơ khoảng 1 phút rồi tắt bếp.",
                "1. Bắp bò\n" +
                        "2. Tỏi, dầu ăn, hành tây\n" +
                        "3. Xốt me",
                R.drawable.no_alcohol_icon
            )
        )
        stepList.add(
            CookingStep(
                "1. Xếp rau, cà chua thái lát ra đĩa, cho bò lên trên. \n" +
                        "2. Rắc thêm mè để thêm phần hấp dẫn.\n" +
                        "3. Thưởng thức cùng bánh mì ngay khi còn nóng. Ngon hơn khi dùng với cơm nóng hoặc",
                "1. Bắp bò đã xào\n" +
                        "2. Xà lách, cà chua, mè",
                R.drawable.veg_icon
            )
        )

        stepList.add(
            CookingStep(
                        "3. Thưởng thức cùng bánh mì ngay khi còn nóng. Ngon hơn khi dùng với cơm nóng hoặc",
                        "2. Xà lách, cà chua, mè",
                R.drawable.veg_icon
            )
        )


        var adapter = StepsAdapter(stepList, this)
        rv.adapter = adapter

        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv)

    }
}

class CookingStep(private val steps: String, private val ingredients: String) {
    private var imageResource = -1
    private var imageURL = ""

    constructor(step: String, ingredients: String, resource: Int) : this(step, ingredients) {
        this.imageResource = resource
    }

    constructor(step: String, ingredients: String, url: String) : this(step, ingredients) {
        this.imageURL = url
    }

    fun getStepImageResource(): Int {
        return imageResource
    }

    fun getStepImageURL(): String {
        return imageURL
    }

    fun getStep(): String {
        return steps
    }

    fun getIngredient(): String {
        return ingredients
    }
}

class StepsAdapter(
    private val stepsList: ArrayList<CookingStep>,
    private val mainContext: Context
) :
    RecyclerView.Adapter<StepsAdapter.ViewHolder>() {
    var currentStep = 0

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val stepIMG = listItemView.findViewById<ImageView>(R.id.stepImageView)
        val stepInstruction = listItemView.findViewById<TextView>(R.id.textInstructionTextView)

        //        val pageTextView = listItemView.findViewById<TextView>(R.id.stepPageTextView)
        val ingredientTextView = listItemView.findViewById<TextView>(R.id.ingredientStepTextView)

        val progressRV = listItemView.findViewById<RecyclerView>(R.id.stepProgressRecyclerView)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return ViewHolder(inflater.inflate(R.layout.recipe_step_item, parent, false))
    }

    override fun getItemCount(): Int {
        return stepsList.size
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = stepsList[position]

        holder.stepIMG.setImageResource(step.getStepImageResource())
        holder.stepInstruction.text = step.getStep()
        holder.ingredientTextView.text = step.getIngredient()

        val page = "${position + 1}/${stepsList.size}"
        val progressList = arrayListOf<String>()

        for (i in 1..stepsList.size)
            progressList.add(i.toString())

        // to get width
        val wm : WindowManager = mainContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val adapter = StepProgressAdapter(progressList, position,  wm.defaultDisplay.width)
        holder.progressRV.adapter = adapter

        holder.progressRV.layoutManager =
            LinearLayoutManager(mainContext, LinearLayoutManager.HORIZONTAL, false)
//        holder.pageTextView.text = page
    }
}

class StepProgressAdapter(
    private val progressList: ArrayList<String>,
    private val currenstPos: Int,
    private val parentRvWidth : Int,
) : RecyclerView.Adapter<StepProgressAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val progressTV = listItemView.findViewById<TextView>(R.id.progressTextView)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return ViewHolder(inflater.inflate(R.layout.step_progress_item, parent, false))
    }

    override fun getItemCount(): Int {
        return progressList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (currenstPos == position) {
            holder.progressTV.setBackgroundResource(R.color.SecondaryColor)
            holder.progressTV.typeface = Typeface.DEFAULT_BOLD
        }

        holder.progressTV.text = progressList[position]
        holder.progressTV.width = parentRvWidth / progressList.size
    }
}
