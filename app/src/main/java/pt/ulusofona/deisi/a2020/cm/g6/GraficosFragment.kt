package pt.ulusofona.deisi.a2020.cm.g6

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_graficos.*
import me.ithebk.barchart.BarChartModel
import kotlin.random.Random


class GraficosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graficos, container, false)
    }

    override fun onStart() {
        super.onStart()
        val chartData = mutableListOf<Int>(541, 125, 148, 65)
        val intervalData = mutableListOf<Int>(1, 2, 3, 4)

        bar_chart_vertical.barMaxValue = 500
        var barchartModel = BarChartModel()
        barchartModel.barValue = 50
        barchartModel.barColor = Color.parseColor("#9C27B0")
        barchartModel.barTag = "ola"
        barchartModel.barText = "12/03"


        var barchartModel2 = BarChartModel()
        barchartModel2.barValue = 250
        barchartModel2.barColor = Color.parseColor("#9C99B9")
        barchartModel2.barTag = "ola"
        barchartModel2.barText = "13/03"


        var barchartModel3 = BarChartModel()
        barchartModel3.barValue = 400
        barchartModel3.barColor = Color.parseColor("#1227B0")
        barchartModel3.barTag = "ola"
        barchartModel3.barText = "14/03\n 400 Casos"

        bar_chart_vertical.addBar(barchartModel)
        bar_chart_vertical.addBar(barchartModel2)
        bar_chart_vertical.addBar(barchartModel3)

    }


}