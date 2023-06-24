# MeditationComposeApp

## Why this project exist?
This project is used by me to practice new technologies or try to implement something interesting.
My main idea when i was creating this project was to learn how to use Jetpack Compose. But the larger the project, the more important it becomes to write clean code to maintain it. \
I really hope that I will develop the project further and further and add various functions here, as this will mean that I continue to work on myself and my code ;)

## What is in the project now?
Currently stack of project: Jetpack Compose, MVVM, Coroutines, Flow, Dagger-Hilt, Rxjava, Firebase\
Also used: Splashscreen, Datastore, Accompanist, Compose-destinations, Image-Cropper, Paging, JUnit\
Configured CI/CD: run ktlint and tests. Was thinking about adding UI tests to pipeline, but Firebase solution limits you to only 10 free tests, so i decided not to.

## What does it look like?

Talking about what's already been done.

### Registration flow

Splash screen              |   Enter screen            |   Sing in screen  |   Sign up screen
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/splash_screen/screenshots/splash_screen.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/authentication/screenshots/enter_screen.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/authentication/screenshots/sign_in_screen.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/authentication/screenshots/sign_up_screen.png" width="200"> 

Enter email for code  |    Code input screen    |    Password recovery screen 
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/authentication/screenshots/enter_login_screen.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/authentication/screenshots/code_input_screen.png" width="200">| <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/authentication/screenshots/new_password_screen.png" width="200">

Implement a standard user script for entering and registering into the application. \
This whole script can use real web requests, but they are currently covered in data in the repositories. Later I will make a small server and feature-toogles to switch between data sources for this flow (and not only for this, hopefully) \
The most interesting part of it is the code entry screen: it implements additional logic for switching focus between cells and automatically "sending" the entered code.


### Main screen

On the main screen implemented `BottomNavigationBar`. \
Cards on main screen are drawn using Canvas, not png images.

Main screen                |        Update pop-up      |   Update notes screen            
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/main/screenshots/main_screen.png" width="200"> |  <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/update_history/screenshots/update_notes_pop_up.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/update_history/screenshots/update_notes_screen.png" width="200">

The main screen contains all the features that I implement in the application. \
Of the interesting, you can highlight the feature cards with icons. The drawing on them is done by drawing on canvas. \
The application has a bottom navigation bar. I put navigation to one of the features to bottom navigation bar just to use it, but later it might be more convinient to place some "Profile" feature there. \
The application database stores a history of all changes in "release" versions. When the application is updated, a dialog appears describing the changes made.
Right not it works on mocks, but repositories and api for Retrofit is already written, so later on i might add it to server and add feature-toogle to swith sources.


### Shuffle puzzle

Image select screen     |  Game   |     Picking image from gallery 
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/shuffle_puzzle/screenshots/image_select_screen.png" width="200">  | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/shuffle_puzzle/screenshots/game_screen.png" width="200">  | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/shuffle_puzzle/screenshots/pick_from_gallery.png" width="200"> 

Shuffle puzzle game added. User can select one of default images, or select image from his gallery. For cropping images i used  `com.github.CanHub:Android-Image-Cropper` (pictures must be square in order for the game to look nice. The logic allows you to divide the picture not only into squares, but also any other resolutions, however, I added functions to the application itself only for sizes 3x3, 4x4 and 5x5.) \
In order to access gallery - added permisions request via `accompanist`. 


### Pagination with with open punk-api

   Paging list screen      |   Detailed info screen    |    No internet    
:-------------------------:|:-------------------------:|:--------------------:
<img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/beer_sorts/screenshots/beer_paging_screen.png" width="200"> |  <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/beer_sorts/screenshots/beer_details_screen.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/internet_connection/screenshots/no_internet_connection_state.png" width="200">

List of beers implemented with usage of `Pagination`. (As data source used api: `https://punkapi.com/documentation/v2`) \
On click user can open Detailed info screen. Nothing really interesting here, but in my opinion pagination is a must to know thing, so i just had to try and add something like that. \
Also i used `NoInternetConnection`screen (that can be used independently, it's in separate module) there for cases, when there is nor internet connection nor cached data available.


### Music player

   Music list screen       |      Music player sheet   |     Notification
:-------------------------:|:-------------------------:|:-------------------:
<img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/music_player/screenshots/music_list_screen.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/music_player/screenshots/music_player_sheet.png" width="200"> | <img src="https://github.com/HermanAleksey/MeditationComposeApp/blob/master/feature/music_player/screenshots/music_player_notification.png" width="200">

Latest feature is music player. \
This is actually only one screen with `bottom sheet`. I am using the `EchoPlayer` library to play audio. There is a convenient pop-up, which, thanks to the libraries, was not difficult to create. What i really like is this spinning vinyl disc with simple animation on Start and on Stop of playing. Player works with different data sources is provided:
- audio from the Internet (using urls)
- local files located in the `raw` folder in resources

And this data can be obtained from database or from web. BUT currently I've just hardcoded few songs into the `Repository` just not to overcomplicate logic (and songs are stored in `raw` folder). Maybe I'll come back to this later, but for now I'm done with this feature.

## What is next?
Most likely i will create some screen with `Feature-Toogles` and slowly work on ktor server, so this app can work not only on mocks :)
Perhaps i will dig into jacoco and stuff like that.
