package pt.ulusofona.deisi.a2020.cm.g6.ui.graficos

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_graficos.*
import me.ithebk.barchart.BarChartModel
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.ui.MainActivity

class GraficosFragment : Fragment() {

    private lateinit var viewModel: GraficosViewModel

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_graficos, container, false)
        viewModel = ViewModelProviders.of(this).get(GraficosViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }



    override fun onStart() {
        super.onStart()

        bar_chart_Confirmados.barMaxValue = viewModel.setMaxConfirmados()
        bar_chart_Recuperados.barMaxValue = viewModel.setMaxRecuperados()
        bar_chart_Obitos.barMaxValue = viewModel.setMaxObitos()
        bar_chart_Internados.barMaxValue = viewModel.setMaxInternados()

        var barchartModel: BarChartModel

        var valuesGraficoConfirmados = viewModel.onDrawGraficosConfirmados()
        var valuesGraficoInternados = viewModel.onDrawGraficosInternados()
        var valuesGraficoObitos = viewModel.onDrawGraficosObitos()
        var valuesGraficoRecuperados = viewModel.onDrawGraficosRecuperados()

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoConfirmados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#989898")
            }else{
                barchartModel.barColor = Color.parseColor("#767676")
            }
            barchartModel.barTag = i
            barchartModel.barText = valuesGraficoConfirmados.elementAt(i).toString()
            bar_chart_Confirmados.addBar(barchartModel)
        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoRecuperados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#5ca08e")
            }else{
                barchartModel.barColor = Color.parseColor("#2e856e")
            }
            barchartModel.barText = valuesGraficoRecuperados.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Recuperados.addBar(barchartModel)

        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoObitos.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#ff7b7b")
            }else{
                barchartModel.barColor = Color.parseColor("#ffbaba")
            }
            barchartModel.barText = valuesGraficoObitos.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Obitos.addBar(barchartModel)

        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoInternados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#70ADCC")
            }else{
                barchartModel.barColor = Color.parseColor("#568EBC")
            }
            barchartModel.barText = valuesGraficoInternados.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Internados.addBar(barchartModel)

        }

        bar_chart_Confirmados.setOnBarClickListener {
            Toast.makeText(context as MainActivity,viewModel.getMessageToastDate(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }
        bar_chart_Recuperados.setOnBarClickListener {
            Toast.makeText(context as MainActivity,viewModel.getMessageToastDate(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }
        bar_chart_Obitos.setOnBarClickListener {
            Toast.makeText(context as MainActivity,viewModel.getMessageToastDate(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }
        bar_chart_Internados.setOnBarClickListener {
            Toast.makeText(context as MainActivity,viewModel.getMessageToastDate(it.barTag as Int), Toast.LENGTH_SHORT).show()
        }

        startDateC.setText(viewModel.getGraficoDatesInfo(0))
        endDateC.setText(viewModel.getGraficoDatesInfo(14))
        startDateR.setText(viewModel.getGraficoDatesInfo(0))
        endDateR.setText(viewModel.getGraficoDatesInfo(14))
        startDateO.setText(viewModel.getGraficoDatesInfo(0))
        endDateO.setText(viewModel.getGraficoDatesInfo(14))
        startDateI.setText(viewModel.getGraficoDatesInfo(0))
        endDateI.setText(viewModel.getGraficoDatesInfo(14))

    }


}