package es.imovil.fcrtrainer.ui.highscores

import android.content.Context
import android.content.SharedPreferences
import es.imovil.fcrtrainer.R
import org.json.JSONArray
import org.json.JSONObject

class HighscoreManager {
    companion object {
        private val TAG = "HigscoreManager"
        private val PREFERENCES_FILENAME = "Highscores"
        private val HIGHSCORE_PREFERENCE = "Highscores"
        private val HIGHSCORE_SCORE_TAG = "score"
        private val HIGHSCORE_EXERCISE_TAG = "exerciseID"
        //private val HIGHSCORE_DATE_TAG = "Date"
        //private val HIGHSCORE_USERNAME_TAG = "Username"
        private val MAX_NUMBER_HIGHSCORES = 10

        //private val mSharedPreferences: SharedPreferences? = null

        fun remPoint(ctx: Context, exID: Int, username: String) {
            modPoint(ctx, exID, username, -1)
        }

        fun remPoint(ctx:Context, exID: Int) {
            remPoint(ctx, exID, ctx.getString(R.string.default_user_name))
        }

        fun addPoint(ctx: Context, exID: Int, username: String) {
            modPoint(ctx, exID, username, 1)
        }

        fun addPoint(ctx:Context, exID: Int) {
            addPoint(ctx, exID, ctx.getString(R.string.default_user_name))
        }

        private fun modPoint(ctx:Context, exID: Int, username: String, value: Int) {
            val exerc = ctx.getString(exID)
            val sharedPrefs = ctx.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)
            val editor = sharedPrefs.edit()

            // Big object on each exercise with key-value pair of user-score
            val scores:JSONObject  = JSONObject(sharedPrefs.getString(exerc, "{}"))
            scores.put(username, scores.optInt(username) + value) // mod
            editor.putString(exerc, scores.toString()) // re-save

            editor.apply()
        }

        fun getScores(ctx: Context, exSID: String): ArrayList<Pair<String, Int>> {
            val arr = ArrayList<Pair<String, Int>>()
            val exerc = exSID
            val sharedPrefs = ctx.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)
            val scores:JSONObject = JSONObject(sharedPrefs.getString(exerc, "{}"))

            scores.keys().forEach { arr.add(Pair<String, Int>(it, scores.getInt(it))) }

            return arr
        }


    }
}