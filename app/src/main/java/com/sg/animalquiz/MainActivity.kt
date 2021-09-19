package com.sg.animalquiz

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.sg.animalquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    val GUESSES = "settings_numberOfGuesses"
    val ANIMALS_TYPE = "settings_animalsType"
    val QUIZ_BACKGROUND_COLOR = "settings_quiz_background_color"
    val QUIZ_FONT = "settings_quiz_font"

    private var isSettingsChanged = false


    var chunkfive: Typeface? = null
    var fontlerybrown: Typeface? = null
    var wonderbarDemo: Typeface? = null








    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        chunkfive = Typeface.createFromAsset(assets, "fonts/Chunkfive.otf")
        fontlerybrown = Typeface.createFromAsset(assets, "fonts/FontleroyBrown.ttf")
        wonderbarDemo = Typeface.createFromAsset(assets, "fonts/Wonderbar Demo.otf")

        PreferenceManager.setDefaultValues(this@MainActivity, R.xml.quiz_prefences, false)
        PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
            .registerOnSharedPreferenceChangeListener(settingsChangeListener)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val preferencesIntent = Intent(this@MainActivity, SettingsActivity::class.java)
        startActivity(preferencesIntent)
        return super.onOptionsItemSelected(item)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private val settingsChangeListener =
        OnSharedPreferenceChangeListener { sharedPreferences, key ->
            isSettingsChanged = true
            /*if (key == GUESSES) {
                myAnimalQuizFragment.modifyAnimalsGuessRows(sharedPreferences)
                myAnimalQuizFragment.resetAnimalQuiz()
            } else if (key == ANIMALS_TYPE) {
                val animalTypes = sharedPreferences.getStringSet(MainActivity.ANIMALS_TYPE, null)
                if (animalTypes != null && animalTypes.size > 0) {
                    myAnimalQuizFragment.modifyTypeOfAnimalsInQuiz(sharedPreferences)
                    myAnimalQuizFragment.resetAnimalQuiz()
                } else {
                    val editor = sharedPreferences.edit()
                    animalTypes!!.add(getString(R.string.default_animal_type))
                    editor.putStringSet(ANIMALS_TYPE, animalTypes)
                    editor.apply()
                    Toast.makeText(
                        this@MainActivity,
                        R.string.toast_message, Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (key == QUIZ_FONT) {
                myAnimalQuizFragment.modifyQuizFont(sharedPreferences)
                myAnimalQuizFragment.resetAnimalQuiz()
            } else if (key == QUIZ_BACKGROUND_COLOR) {
                myAnimalQuizFragment.modifyBackgroundColor(sharedPreferences)
                myAnimalQuizFragment.resetAnimalQuiz()
            }
            Toast.makeText(this@MainActivity, R.string.change_message, Toast.LENGTH_SHORT).show()*/
        }
}