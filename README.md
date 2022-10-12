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

Enter screen     |  Sing in screen  |   Main menu screen
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/195434222-3c4e155f-8f98-486f-af18-b1f815fb194e.jpg" width="200">| <img src="https://user-images.githubusercontent.com/68656704/195434196-65014fea-e306-4d41-ad95-d1ee071d1380.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/195434439-9652dbc2-af07-4e28-86c7-701970d2bb60.jpg" width="200"> 

On the main screen implemented BottomNavigationBar. \
Cards on main screen are drawn using Canvas, not png images.


### Shuffle puzzle

Default screen             |  Possibility to choose your photo  |     Puzzle itself       |  Completed puzzle
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/195431352-4b30b7a3-8b69-4aae-8393-aab4908b40a2.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/195431431-891ee2f7-5839-4f12-a570-cf57f119c432.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/195431467-a892b30f-69ec-4d2b-87b8-ad004eec8133.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/195431485-47cb7cc9-42f7-4028-b275-a0fe478575f5.jpg" width="200"> 

The ability to select your photo from the gallery and crop it to a square is implemented thanks to the open library: `com.github.CanHub:Android-Image-Cropper`


### List that displays data from open API

<img src="https://user-images.githubusercontent.com/68656704/195434819-758d1611-1b29-4050-841e-54906258dcf1.jpg" width="200"> 

In the near future, I plan to use Pagination and data caching for this (or another, if I find a better option) list of data.
