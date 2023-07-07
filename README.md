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
On launch, the app displays a splash screen with an animated logo and a welcome message. 
The first screen shows a list of flags of 12 countries. By clicking on the flag, the user is directed to the second screen with id of the coutry we clicked on. At this point,  a server request is made with that id and we get list of 100 ids of football clubs in that country. 
Then we make individual requests for each club ID, so 100 requests  to retrieve information about each team. The clubs are displayed in a list format, similar to the countries, with their logos downloaded using Coil. By clicking on the team's logo we move to the third screen with it's id, and similarly make a server request to get info about team's squad. 
On the third screen we can see name, logo, name of the coach and list of players of that team. Cards with players and coach's name are clickable and direct user to the next screen, which is a WebView screen. In this screen, user is directed to a Google.com page but with the name of the player we clicked on already searched in a Google search string.
