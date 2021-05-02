package com.example.advancedanimotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Step9CompletedActivity : AppCompatActivity() {

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
        var preLayout = extra.getString(Loader.KEY)
        var id = applicationContext.resources.getIdentifier(preLayout, "layout", applicationContext.packageName)
        setContentView(id)
    }

    private fun setupCarousel() {
        TODO("Not yet implemented")
    }


}