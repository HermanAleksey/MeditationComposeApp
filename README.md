# MeditationComposeApp

## Why this project exist?
This project is used by me to practice new technologies or try to implement something interesting.
My main idea when i was creating this project was to learn how to use Jetpack Compose. But the larger the project, the more important it becomes to write clean code to maintain it. \
I really hope that I will develop the project further and further and add various functions here, as this will mean that I continue to work on myself and my code ;)

## What is in the project now? 

Currently stack of project: Jetpack Compose, MVVM, Coroutines, Flow, Dagger-Hilt, Rxjava, Firebase\
Also used: Splashscreen, Datastore, Accompanist, Compose-destinations, Image-Cropper

## What does it look like?

Talking about what's already been done.

### Registration flow

Splash screen              |   Enter screen            |   Sing in screen  |   Sign up screen
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200169516-fd215ff2-69f5-4de0-bfb4-96d0469c70c7.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/200169788-bb8f7cf7-e0b4-419b-ace6-1a2f03afbeec.jpg" width="200">| <img src="https://user-images.githubusercontent.com/68656704/200169685-e77e1e3f-6365-4663-9656-24b80dde3796.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/200169651-a80d5578-8f2c-4e7e-ac35-ede031c21496.jpg" width="200"> 

Password recovery screen  |   Enter email for code     |   Code input screen  
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200169812-653e2739-0e63-43a0-9808-43269aa4173f.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/200169861-94bcc669-5382-4ec3-bcce-66cd361e84c2.jpg" width="200">| <img src="https://user-images.githubusercontent.com/68656704/200169836-94a70bc1-8f81-4a30-9f7f-221c71c01f09.jpg" width="200">

Implement a standard user script for entering and registering into the application.
The most interesting part of it is the code entry screen: it implements additional logic for switching focus between cells and automatically "sending" the entered code.

On the main screen implemented BottomNavigationBar. \
Cards on main screen are drawn using Canvas, not png images.

### Main screen

Main screen              |   Update pop-ups            |   q  |   q
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200170265-cb9016bd-7edc-4fe2-87c0-db058e5661f1.jpg" width="200">


### Shuffle puzzle

Default screen             |  Possibility to choose your photo  |     Puzzle itself       |  Completed puzzle
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/195431352-4b30b7a3-8b69-4aae-8393-aab4908b40a2.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/195431431-891ee2f7-5839-4f12-a570-cf57f119c432.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/195431467-a892b30f-69ec-4d2b-87b8-ad004eec8133.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/195431485-47cb7cc9-42f7-4028-b275-a0fe478575f5.jpg" width="200"> 

The ability to select your photo from the gallery and crop it to a square is implemented thanks to the open library: `com.github.CanHub:Android-Image-Cropper`


### List that displays data from open API

<img src="https://user-images.githubusercontent.com/68656704/195434819-758d1611-1b29-4050-841e-54906258dcf1.jpg" width="200"> 

Just list of items from open api. Implemented with usage of Pagination. Used api: `https://punkapi.com/documentation/v2`
