package com.ferhat.roadtimecalculator

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ferhat.roadtimecalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val TAG: String = "FerhatMain"
    private lateinit var bindings: ActivityMainBinding

    var startPoint = 0
    var endPoint = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(bindings.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Previous session loading
        val defDistance: Float = FunctionsData(this).getDistance()
        val defSpeed: Float = FunctionsData(this).getSpeed()
//        if (defDistance != 0)
//            bindings.txtDistance.setText(defDistance)
//        if (defSpeed != 0)
//            bindings.txtSpeed.setText(defSpeed)

        bindings.btnCalculate.setOnClickListener() {
            val dist: Float = bindings.txtDistance.text.toString().toFloat()
            val speed: Float = bindings.txtSpeed.text.toString().toFloat()
            FunctionsData(this).saveDistance(dist)
            FunctionsData(this).saveSpeed(speed)
            val time: Float = (dist / speed) * 60
            val hour: Int = (time / 60).toInt()
            val min: Int = (time % 60).toInt()
            Log.i(TAG, "onCreate: time=$time, hour=$hour, minute=$min")
            bindings.txtResult.text = "Estimated time: $hour hour $min minute(s)"

        }

        bindings.barDistance.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar?, pval: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged: Distance Bar: $pval")
                bindings.txtDistance.setText("$pval")
            }

            override fun onStartTrackingTouch(bar: SeekBar?) { }

            override fun onStopTrackingTouch(bar: SeekBar?) { }
        })

        bindings.barSpeed.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar?, pval: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged: Speed bar: $pval")
                bindings.txtSpeed.setText("$pval")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) { }

            override fun onStopTrackingTouch(p0: SeekBar?) { }
        })
    }
}