package es.imovil.fcrtrainer.ui.highscores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import es.imovil.fcrtrainer.R

class HighscoreAdapter(private val ctx: Context, highscores: List<Pair<String,Int>>) :
    ArrayAdapter<Pair<String,Int>?>(ctx, R.layout.highscore_item, highscores) {

    private val scores: List<Pair<String,Int>>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = ctx
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.highscore_item, parent, false)

        val pos = rowView.findViewById<View>(R.id.text_view_position) as TextView
        pos.text = Integer.toString(position + 1) // Position 0 is 1st place

        val userName = rowView.findViewById<View>(R.id.text_view_user_name) as TextView
        userName.setText(scores[position].first)

        val score = rowView.findViewById<View>(R.id.text_view_score) as TextView
        score.text = Integer.toString(scores[position].second)

        return rowView
    }

    init {
        scores = highscores
    }
}