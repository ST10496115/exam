package za.co.st10496115exam1

// Singleton object to store playlist data across activities using basic arrays
object PlaylistData {
    // Using basic arrays with maximum capacity of 4
    var songTitles = Array(4) { "" }
    var artistNames = Array(4) { "" }
    var ratings = Array(4) { "" }
    var comments = Array(4) { "" }
    var currentSize = 0
    
    fun addSong(title: String, artist: String, rating: String, comment: String): Boolean {
        if (currentSize >= 4) {
            return false // Playlist is full
        }
        
        songTitles[currentSize] = title
        artistNames[currentSize] = artist
        ratings[currentSize] = rating
        comments[currentSize] = comment
        currentSize++
        return true
    }
    
    fun clearPlaylist() {
        // Using basic for loop to clear arrays
        for (i in 0 until 4) {
            songTitles[i] = ""
            artistNames[i] = ""
            ratings[i] = ""
            comments[i] = ""
        }
        currentSize = 0
    }
    
    fun getBasicPlaylist(): String {
        if (currentSize == 0) {
            return "No songs in playlist yet."
        }
        
        var result = "🎵 MY PLAYLIST ($currentSize/4 songs) 🎵\n\n"
        
        // Using basic for loop
        for (i in 0 until currentSize) {
            result += "${i + 1}. ${songTitles[i]}\n"
            result += "   Artist: ${artistNames[i]}\n"
            result += "   Rating: ${ratings[i]}/5 ⭐\n"
            if (comments[i].isNotEmpty()) {
                result += "   Comment: ${comments[i]}\n"
            }
            result += "\n"
        }
        
        return result
    }
    
    fun getDetailedPlaylist(): String {
        if (currentSize == 0) {
            return "No songs in playlist yet.\nAdd some songs to see detailed information!"
        }
        
        var result = "🎵 DETAILED PLAYLIST VIEW 🎵\n"
        result += "═══════════════════════════════════\n\n"
        
        // Using basic for loop for detailed view
        for (i in 0 until currentSize) {
            result += "SONG #${i + 1}\n"
            result += "────────────────────────────────\n"
            result += "♪ Title: ${songTitles[i]}\n"
            result += "🎤 Artist: ${artistNames[i]}\n"
            result += "⭐ Rating: ${ratings[i]}/5 "
            
            // Add star visualization using for loop
            var stars = ""
            val ratingNum = ratings[i].toIntOrNull() ?: 0
            for (j in 1..5) {
                if (j <= ratingNum) {
                    stars += "★"
                } else {
                    stars += "☆"
                }
            }
            result += "($stars)\n"
            
            if (comments[i].isNotEmpty()) {
                result += "💬 Comment: ${comments[i]}\n"
            } else {
                result += "💬 Comment: No comment provided\n"
            }
            result += "────────────────────────────────\n\n"
        }
        
        result += "Total Songs: $currentSize/4\n"
        result += "═══════════════════════════════════"
        
        return result
    }
    
    fun calculateAverageRating(): String {
        if (currentSize == 0) {
            return "No songs to calculate average rating."
        }
        
        var totalRating = 0.0
        var validRatings = 0
        
        // Using basic for loop to calculate average
        for (i in 0 until currentSize) {
            val rating = ratings[i].toDoubleOrNull()
            if (rating != null) {
                totalRating += rating
                validRatings++
            }
        }
        
        if (validRatings == 0) {
            return "No valid ratings found."
        }
        
        val average = totalRating / validRatings
        
        var result = "📊 AVERAGE RATING CALCULATION 📊\n"
        result += "═══════════════════════════════════\n\n"
        result += "Rating breakdown:\n"
        
        // Using basic for loop to show individual ratings
        for (i in 0 until currentSize) {
            result += "${i + 1}. ${songTitles[i]}: ${ratings[i]}/5\n"
        }
        
        result += "\nCalculation:\n"
        result += "Total Rating Points: $totalRating\n"
        result += "Number of Songs: $validRatings\n"
        result += "Average: $totalRating ÷ $validRatings = ${String.format("%.2f", average)}\n\n"
        
        // Rating interpretation using basic if-else
        var interpretation = ""
        if (average >= 4.5) {
            interpretation = "🌟 Excellent playlist! Outstanding ratings!"
        } else if (average >= 4.0) {
            interpretation = "⭐ Great playlist! Very good ratings!"
        } else if (average >= 3.0) {
            interpretation = "👍 Good playlist! Decent ratings!"
        } else if (average >= 2.0) {
            interpretation = "👌 Fair playlist! Room for improvement!"
        } else {
            interpretation = "📈 Consider adding higher-rated songs!"
        }
        
        result += "Rating: ${String.format("%.2f", average)}/5.00\n"
        result += interpretation
        
        return result
    }
}
