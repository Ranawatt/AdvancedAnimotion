package com.example.advancedanimotion

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.demo_050_carousel.*

class Step9CompletedActivity : AppCompatActivity() {
    private lateinit var layoutname: String
    private var numImages: Int = 0
    var images = intArrayOf(
        R.drawable.bryce_canyon,
        R.drawable.cathedral_rock,
        R.drawable.death_valley,
        R.drawable.fitzgerald_marine_reserve,
        R.drawable.goldengate,
        R.drawable.golden_gate_bridge,
        R.drawable.shipwreck_1,
        R.drawable.shipwreck_2,
        R.drawable.grand_canyon,
        R.drawable.horseshoe_bend,
        R.drawable.muir_beach,
        R.drawable.rainbow_falls,
    )

    var colors = intArrayOf(
        Color.parseColor("#9C4B8F"),
        Color.parseColor("#945693"),
        Color.parseColor("#8C6096"),
        Color.parseColor("#846B9A"),
        Color.parseColor("#7C769E"),
        Color.parseColor("#7480A2"),
        Color.parseColor("#6D8BA5"),
        Color.parseColor("#6595A9"),
        Color.parseColor("#5DA0AD"),
        Color.parseColor("#55ABB1"),
        Color.parseColor("#4DB5B4"),
        Color.parseColor("#45C0B8"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var extra = intent.extras
        if (extra != null){
            Loader.normalMenuStartUp(this)
            return
        }
        setupActivity(extra!!)
        setupCarousel()
//        setContentView(R.layout.activity_step9_completed)
    }
    private fun setupActivity(extra: Bundle) {
        layoutname = extra.getString(Loader.KEY)!!
        var id = applicationContext.resources.getIdentifier(
            layoutname,
            "layout",
            applicationContext.packageName
        )
        setContentView(id)
        val root = findViewById<View>(android.R.id.content).rootView as ViewGroup
        val mlView = findViewById<View>(R.id.motionLayout)
        var mMotionLayout = if (mlView != null) mlView as MotionLayout else Loader.findMotionLayout(
            root
        )
    }

    private fun setupCarousel() {

        if (carousel == null) {
            return
        }
        numImages = images.size

        if (layoutname == "demo_050_carousel") {
            numImages = 1
            setupCarouselDemo50(carousel)
        }

        val button = findViewById<Button>(R.id.button)
        if (layoutname == "demo_010_carousel") {
            button!!.setOnClickListener { v: View? ->
                val numItems = carousel.count
                val lastItem = numItems - 1
                if (carousel.currentIndex == 0) {
                    carousel.jumpToIndex(lastItem)
                } else {
                    carousel.jumpToIndex(0)
                }
            }
        }
        if (layoutname == "demo_060_carousel") {
            button!!.setOnClickListener { v: View? ->
                val numItems = carousel.count
                val lastItem = numItems - 1
                if (carousel.currentIndex == 0) {
                    carousel.transitionToIndex(lastItem, 200)
                } else {
                    carousel.transitionToIndex(0, 200)
                }
            }
        }

        carousel.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return numImages
            }

            override fun populate(view: View, index: Int) {
                if (view is ImageView) {
                    view.setImageResource(images[index])
                } else if (view is TextView) {
                    val textView = view
                    textView.text = "#" + (index + 1)
                    textView.setBackgroundColor(colors.get(index))
                }
            }

            override fun onNewItem(index: Int) {
                if (label != null) {
                    label.text = "#" + (index + 1)
                }
                if (button != null) {
                    if (index == carousel.count - 1) {
                        button.text = "Go to first item"
                    }
                    if (index == 0) {
                        button.text = "Go to last item"
                    }
                }
            }
        })
    }

    private fun setupCarouselDemo50(carousel: Carousel) {

        add?.setOnClickListener { view: View? ->
            numImages++
            if (text != null) {
                text.text = "$numImages images"
            }
            carousel.refresh()
        }
        remove?.setOnClickListener { view: View? ->
            numImages = 0
            if (text != null) {
                text.text = "$numImages images"
            }
            carousel.refresh()
        }
    }


}