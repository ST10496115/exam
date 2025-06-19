package za.co.st10496115exam1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlaylistActivity : AppCompatActivity() {
    
    private var isDetailedView = false
    private var isShowingAverage = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist)

        val playlistDisplay = findViewById<TextView>(R.id.playlistDisplay)
        val backButton = findViewById<Button>(R.id.backButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val detailedViewButton = findViewById<Button>(R.id.detailedViewButton)
        val averageRatingButton = findViewById<Button>(R.id.averageRatingButton)

        // Display the basic playlist initially
        updateDisplay(playlistDisplay)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        clearButton.setOnClickListener {
            PlaylistData.clearPlaylist()
            isDetailedView = false
            isShowingAverage = false
            updateDisplay(playlistDisplay)
            updateButtonTexts(detailedViewButton, averageRatingButton)
        }

        detailedViewButton.setOnClickListener {
            if (PlaylistData.currentSize == 0) {
                playlistDisplay.text = "No songs in playlist to show detailed view.\nAdd some songs first!"
                return@setOnClickListener
            }
            
            isDetailedView = !isDetailedView
            isShowingAverage = false
            updateDisplay(playlistDisplay)
            updateButtonTexts(detailedViewButton, averageRatingButton)
        }

        averageRatingButton.setOnClickListener {
            if (PlaylistData.currentSize == 0) {
                playlistDisplay.text = "No songs in playlist to calculate average.\nAdd some songs first!"
                return@setOnClickListener
            }
            
            isShowingAverage = !isShowingAverage
            isDetailedView = false
            updateDisplay(playlistDisplay)
            updateButtonTexts(detailedViewButton, averageRatingButton)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    private fun updateDisplay(playlistDisplay: TextView) {
        when {
            isShowingAverage -> {
                playlistDisplay.text = PlaylistData.calculateAverageRating()
            }
            isDetailedView -> {
                playlistDisplay.text = PlaylistData.getDetailedPlaylist()
            }
            else -> {
                playlistDisplay.text = PlaylistData.getBasicPlaylist()
            }
        }
    }
    
    private fun updateButtonTexts(detailedViewButton: Button, averageRatingButton: Button) {
        detailedViewButton.text = if (isDetailedView) "BASIC VIEW" else "DETAILED VIEW"
        averageRatingButton.text = if (isShowingAverage) "HIDE AVERAGE" else "CALCULATE AVERAGE"
    }
}
