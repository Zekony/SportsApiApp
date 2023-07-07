# SportsApiApp
Small app that uses RestApi to get information about 100 most famous football teams in 12 countries.

## Technologies used in this project

- Language: Kotlin
- Jetpack Compose
- Coroutines
- Retrofit
- Hilt
- Coil
- WebView

 ## Content

 In my app, users can see information about 100 most famous football teams in 12 countries, their players, coach.

 ## How it works
On launch the app displays a splash screen with an animated logo and a welcome message. 
The first screen shows flags of 12 countries in a list. By clicking on the flag we move to the second screen with id of the coutry we clicked on, there a server request is made with that id and we get list of 100 ids of football clubs in that country. 
Then we make requests for every id, so 100 requests to get each team's information. Clubs are shown in a list just as countries were, with their logos downloaded by Coil. By clicking on the team's logo we move to the third screen with it's id, and similarly make a server request to get info about team's squad. 
On the third screen we can see name, logo, name of the coach and list of players of that team. Cards with players and coach's name are clickable and will move us to the next screen which is a WebView screen, we get to a Google.com page but with the name of the player we clicked on already searched for in a google's search string.
