package pt.ulusofona.deisi.a2020.cm.g6.ui.graficos

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_graficos.*
import me.ithebk.barchart.BarChartModel
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.CovidData
import pt.ulusofona.deisi.a2020.cm.g6.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*


class GraficosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graficos, container, false)
    }

    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
    }

    override fun onStart() {
        super.onStart()
        var covidHoje: CovidData = CovidData()
        val chartData = mutableListOf<Int>(541, 125, 148, 65)
        val intervalData = mutableListOf<Int>(1, 2, 3, 4)

        bar_chart_Confirmados.barMaxValue = 800
        bar_chart_Recuperados.barMaxValue = 800
        bar_chart_Obitos.barMaxValue = 800
        bar_chart_Internados.barMaxValue = 880

        var maxC = 0;
        for (i in covidHoje.listDiasConfirmados){
            if (i > maxC){
                maxC = i;
            }
        }

        var maxR = 0;
        for (i in covidHoje.listDiasRecuperados){
            if (i > maxR){
                maxR = i;
            }
        }

        var maxO = 0;
        for (i in covidHoje.listDiasObitos){
            if (i > maxO){
                maxO = i;
            }
        }

        var maxI = 0;
        for (i in covidHoje.listDiasInternados){
            if (i > maxI){
                maxI = i;
            }
        }

        bar_chart_Confirmados.barMaxValue = maxC
        bar_chart_Recuperados.barMaxValue = maxR
        bar_chart_Obitos.barMaxValue = maxO
        bar_chart_Internados.barMaxValue = maxI

        var barchartModel = BarChartModel()

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = covidHoje.listDiasConfirmados.elementAt(i)
            //barchartModel.barColor = Color.parseColor(listColors.elementAt(i))
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#989898")
            }else{
                barchartModel.barColor = Color.parseColor("#767676")
            }
            barchartModel.barTag = i
            barchartModel.barText = covidHoje.listDiasConfirmados.elementAt(i).toString()
            bar_chart_Confirmados.addBar(barchartModel)
        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = covidHoje.listDiasRecuperados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#5ca08e")
            }else{
                barchartModel.barColor = Color.parseColor("#2e856e")
            }
            barchartModel.barText = covidHoje.listDiasRecuperados.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Recuperados.addBar(barchartModel)

        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = covidHoje.listDiasObitos.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#ff7b7b")
            }else{
                barchartModel.barColor = Color.parseColor("#ffbaba")
            }
            barchartModel.barText = covidHoje.listDiasObitos.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Obitos.addBar(barchartModel)

        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = covidHoje.listDiasInternados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#70ADCC")
            }else{
                barchartModel.barColor = Color.parseColor("#568EBC")
            }
            barchartModel.barText = covidHoje.listDiasInternados.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Internados.addBar(barchartModel)

        }

        bar_chart_Confirmados.setOnBarClickListener {
            Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }
        bar_chart_Recuperados.setOnBarClickListener {
            Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }
        bar_chart_Obitos.setOnBarClickListener {
            Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }
        bar_chart_Internados.setOnBarClickListener {
            Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }

        startDateC.setText(getDaysAgo(0))
        endDateC.setText(getDaysAgo(14))

        startDateR.setText(getDaysAgo(0))
        endDateR.setText(getDaysAgo(14))

        startDateO.setText(getDaysAgo(0))
        endDateO.setText(getDaysAgo(14))

        startDateI.setText(getDaysAgo(0))
        endDateI.setText(getDaysAgo(14))

    }


}