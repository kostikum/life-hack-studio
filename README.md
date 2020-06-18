## life-hack-studio test project
Android demo project that illustrates modern approach to implementing the Model-View-ViewModel design pattern using contemporary libraries that make code concise and easy-to-read: Kotlin, Kotlin coroutines, AAC (Lifecycle, LiveData, Room, DataBinding, Navigation), Retrofit2, Gson, Glide.

# Under the hood
The demo project is a typical Android app that fetches data from remote server (in our case we fetch the list of companies or company details), saves it on device using ROM and displays to the user on two screens implemented with fragments. You can also navigate to a company location using Maps Fragment.

**Data Binding** is a library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically;  
**Retrofit2** turns HTTP API into a Java interface;  
**Gson** is serialization/deserialization library used together with Retrofit to turn server response (JSON, etc) into POJOs;  
**Room** is a persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.  
**Glide** is fast and efficient image loading and caching library.  


![alt text](https://github.com/kostikum/kostikum.github.io/blob/master/lifehack-images/1.jpeg "Screenshot 1")
![alt text](https://github.com/kostikum/kostikum.github.io/blob/master/lifehack-images/2.jpeg "Screenshot 1")
![alt text](https://github.com/kostikum/kostikum.github.io/blob/master/lifehack-images/3.jpeg "Screenshot 1")