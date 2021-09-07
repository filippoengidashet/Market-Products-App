# Market products App

![Market Products-App](https://github.com/filippoengidashet/Market-Products-App/blob/main/files/demo.gif)

#### Features

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

![Architecture](https://github.com/filippoengidashet/Market-Products-App/blob/main/files/arch.png)

* The App is architected using Google’s recommended pattern
* It survives continuous operation on the model side even on screen rotation, then once the view is ready it’ll be automatically reflected via the ViewModel
* Is not gonna cause any memory leak as it’s tied to the component lifecycle (Activity)

#### Additional Notes

* Scrolling through the list can be tedious, having a search/filter functionality can be something to add in the future
* Design can be improved with great looks, UI/UX, colours and typography


License
-------

    Copyright 2021 Filippo Engidashet.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

