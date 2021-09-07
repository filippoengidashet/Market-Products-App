## Market products App (Features)

* List all products screen (Persisted for offline access)
* View product detail screen

#### Added 3rd party SDK

* Hilt (Dependency Injection)
* Room(Database)
* Coroutines (light weight thread computation)
* Retrofit (Rest Api Integration)
* Android Arch (View Model, Livedata, etc.)
* Glide (Image loading and caching)
* Mockito, Junit, Espresso (Testing)

#### Architecture - MVVM

* The App is architected using Google’s recommended pattern
* It survives continuous operation on the model side even on screen rotation, then once the view is ready it’ll be automatically reflected via the vie model
* Is not gonna cause any memory leak as it’s tied to the component lifecycle (Activity)

#### Additional Notes

* Scrolling through the list can be tedious, having a search/filter functionality can be something to add in the future
* Design can be improved with great looks, UI/UX, colours and typography
