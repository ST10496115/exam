# My Playlist App

A simple Android application that allows users to create a music playlist with a maximum of 4 songs.

## Features

### Main Activity (Song Entry)
- **Add Songs**: Users can add songs to their playlist by entering:
  - Song Title (required)
  - Artist Name (required)
  - Rating (1-5, required)
  - Comments (optional)

- **4-Song Limit**: The app enforces a maximum of 4 songs in the playlist
- **Input Validation**: 
  - All required fields must be filled
  - Rating must be between 1 and 5
  - Shows error messages for invalid input

- **Real-time Counter**: Displays current song count (e.g., "Songs in playlist: 2/4")
- **User Feedback**: Toast messages confirm when songs are added or when limits are reached

### Playlist Activity (View Playlist)
- **Formatted Display**: Shows all songs in a nicely formatted list with:
  - Song number
  - Song title
  - Artist name
  - Star rating display
  - Comments (if provided)

- **Navigation**: Back button to return to the main activity
- **Clear Playlist**: Button to remove all songs from the playlist

## Technical Implementation

### Key Components
1. **MainActivity.kt**: Handles song input and validation
2. **PlaylistActivity.kt**: Displays the formatted playlist
3. **PlaylistData.kt**: Singleton object to manage playlist data across activities

### Data Management
- Uses a singleton pattern to maintain playlist data across activities
- Stores songs in synchronized lists for titles, artists, ratings, and comments
- Provides formatted output for display

### User Interface
- Clean, scrollable layouts with proper labels
- Responsive design with constraint layouts
- Material Design buttons and input fields
- Toast notifications for user feedback

## Usage Instructions

1. **Adding Songs**:
   - Fill in the song title, artist name, and rating (1-5)
   - Optionally add comments
   - Tap "ADD SONG TO PLAYLIST"
   - The counter will update to show current playlist size

2. **Viewing Playlist**:
   - Tap "VIEW PLAYLIST" to see all added songs
   - Songs are displayed with full details and formatting

3. **Managing Playlist**:
   - Use "BACK TO ADD SONGS" to return and add more songs
   - Use "CLEAR PLAYLIST" to remove all songs and start over

## Limitations
- Maximum of 4 songs per playlist
- Data is not persisted between app sessions
- No song editing functionality (must clear and re-add)

## Build Requirements
- Android Studio
- Java 11 or higher
- Android SDK API level as specified in build.gradle.kts
