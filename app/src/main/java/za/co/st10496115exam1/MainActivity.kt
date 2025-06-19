package za.co.st10496115exam1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        var playlist = findViewById<TextView>(R.id.playlist)
        var songtitleInput = findViewById<EditText>(R.id.songtitleInput)
        var artistnameInput = findViewById<EditText>(R.id.artistnameInput)
        var ratingInput = findViewById<EditText>(R.id.ratingInput)
        var commentsInput = findViewById<EditText>(R.id.commentsInput)
        var enterButton = findViewById<Button>(R.id.enterButton)
        var nextButton = findViewById<Button>(R.id.nextButton)
        var songCountText = findViewById<TextView>(R.id.songCount)

        // Update song count display
        updateSongCount(songCountText)

        enterButton.setOnClickListener {
            val title = songtitleInput.text.toString().trim()
            val artist = artistnameInput.text.toString().trim()
            val ratingText = ratingInput.text.toString().trim()
            val comment = commentsInput.text.toString().trim()

            // Validate inputs
            if (title.isEmpty() || artist.isEmpty() || ratingText.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate rating (should be between 1-5)
            val rating = ratingText.toIntOrNull()
            if (rating == null || rating < 1 || rating > 5) {
                Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Try to add song using the new array-based method
            val success = PlaylistData.addSong(title, artist, ratingText, comment)
            
            if (!success) {
                Toast.makeText(this, "Playlist is full! Maximum 4 songs allowed.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Clear input fields
            songtitleInput.text.clear()
            artistnameInput.text.clear()
            ratingInput.text.clear()
            commentsInput.text.clear()

            // Update song count
            updateSongCount(songCountText)

            Toast.makeText(this, "Song added to playlist! (${PlaylistData.currentSize}/4)", Toast.LENGTH_SHORT).show()
        }

        nextButton.setOnClickListener {
            if (PlaylistData.currentSize == 0) {
                Toast.makeText(this, "Please add at least one song to the playlist", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateSongCount(songCountText: TextView) {
        songCountText.text = "Songs in playlist: ${PlaylistData.currentSize}/4"
    }
}













