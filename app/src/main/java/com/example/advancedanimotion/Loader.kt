package com.example.advancedanimotion

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.constraintlayout.motion.widget.MotionLayout
import java.lang.reflect.Field
import java.util.*

object Loader {
    var KEY = "layout"
    private const val LAYOUTS_MATCHES = "demo_\\d+_.*"

    fun findMotionLayout(group: ViewGroup): MotionLayout? {
        val groups = ArrayList<ViewGroup>()
        groups.add(group)
        while (!groups.isEmpty()) {
            val vg = groups.removeAt(0)
            val n = vg.childCount
            for (i in 0 until n) {
                val view = vg.getChildAt(i)
                if (view is MotionLayout) {
                    return view
                }
                if (view is ViewGroup) {
                    groups.add(view)
                }
            }
        }
        return null
    }

    fun normalMenuStartUp(mainActivity: Step9CompletedActivity) {
        var layouts = getLayouts(object : Test {
            override fun test(s: String): Boolean {
                return s.matches(Regex(LAYOUTS_MATCHES))
            }
        })
        val sv = ScrollView(mainActivity)
        val linearLayout = LinearLayout(mainActivity)
        linearLayout.orientation = LinearLayout.VERTICAL
        for (i in layouts.indices) {
            val button = Button(mainActivity)
            button.text = layouts[i]
            button.tag = layouts[i]
            linearLayout.addView(button)
            button.setOnClickListener { view: View ->
                launch(
                    mainActivity,
                    view.tag as String
                )
            }
        }

        sv.addView(linearLayout)
        mainActivity.setContentView(sv)
    }

    private fun getLayouts(filter: Test?): Array<String> {
        val list = ArrayList<String>()
//        val f: Array<Field> = layout::class.java.getDeclaredFields()
//
//        for (i in f.indices) {
//            val name = f[i].name
//            if (filter == null || filter.test(name)) {
//                list.add(name)
//            }
//        }
        return list.toTypedArray()
    }

    fun launch(mainActivity: Step9CompletedActivity, id: String?) {
        val intent = Intent(mainActivity, Step9CompletedActivity::class.java)
        intent.putExtra(KEY, id)
        mainActivity.startActivity(intent)
    }

    internal interface Test {
        fun test(s: String): Boolean
    }

}