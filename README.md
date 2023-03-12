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
<img src="https://user-images.githubusercontent.com/68656704/200169516-fd215ff2-69f5-4de0-bfb4-96d0469c70c7.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/200169788-bb8f7cf7-e0b4-419b-ace6-1a2f03afbeec.jpg" width="200">| <img src="https://user-images.githubusercontent.com/68656704/200169685-e77e1e3f-6365-4663-9656-24b80dde3796.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/200169651-a80d5578-8f2c-4e7e-ac35-ede031c21496.jpg" width="200"> 

Password recovery screen  |   Enter email for code     |   Code input screen  
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200169812-653e2739-0e63-43a0-9808-43269aa4173f.jpg" width="200"> | <img src="https://user-images.githubusercontent.com/68656704/200169861-94bcc669-5382-4ec3-bcce-66cd361e84c2.jpg" width="200">| <img src="https://user-images.githubusercontent.com/68656704/200169836-94a70bc1-8f81-4a30-9f7f-221c71c01f09.jpg" width="200">

Implement a standard user script for entering and registering into the application.
The most interesting part of it is the code entry screen: it implements additional logic for switching focus between cells and automatically "sending" the entered code.

On the main screen implemented BottomNavigationBar. \
Cards on main screen are drawn using Canvas, not png images.

### Main screen

Main screen              |   Update pop-ups            
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200170265-cb9016bd-7edc-4fe2-87c0-db058e5661f1.jpg" width="200"> |  <img src="https://user-images.githubusercontent.com/68656704/200170454-c3364eae-950d-4d47-bc42-d4e69e74a0f5.jpg" width="200">

The main screen contains all the features that I implement in the application. \
Of the interesting, you can highlight the feature cards with icons. The drawing on them is done by drawing on canvas. \
The application has a bottom navigation bar. It has the ability to switch to the beer list and profile screens. \
The profile screen is a dummy for now. All it contains at the moment is an logout button.
The application database stores a history of all changes in "release" versions. When the application is updated, a dialog appears describing the changes made.

### Shuffle puzzle

Image select screen     |  Game      |     Picking image from gallery       |  Permission request
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200193557-30c38ad4-2dca-4652-85b7-adf5ba0e538c.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/200193582-d6ea2a4b-93ba-4ebe-a5e2-e0f00bfc33f0.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/200193634-f14eaf17-9f6b-40c5-90ac-a23891b368c1.jpg" width="200">  | <img src="https://user-images.githubusercontent.com/68656704/200193649-e23969f5-4c0c-4342-a5cc-8c80498821bb.jpg" width="200"> 

Shuffle puzzle game added. User can select one of default images, or select image from his gallery. For cropping images i used  `com.github.CanHub:Android-Image-Cropper` (pictures must be square in order for the game to look nice. The logic allows you to divide the picture not only into squares, but also any other resolutions, however, I added functions to the application itself only for sizes 3x3, 4x4 and 5x5.) \
In order to access gallery - added permisions request via `accompanist`.

### Pagination with with open punk-api

Paging list screen         |   Detailed info screen            
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/68656704/200193098-d4e25705-417f-40a2-86e0-d3f8493b2a8e.jpg" width="200"> |  <img src="https://user-images.githubusercontent.com/68656704/200193111-82ff34b4-7256-459a-ac52-ee1eb2d895a0.jpg" width="200">

List of beers implemented with usage of Pagination. (Used api: `https://punkapi.com/documentation/v2`) \
On click user can open Detailed info screen. Nothing really interesting here, but in my opinion pagination is a must to know thing, so i have to add something.


### What is next?
First of all is redesign, especially beer and puzzle features. 
After it I'm planning to implement music player as next feature.
