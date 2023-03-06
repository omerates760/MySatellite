<h1 align="center">My Satellite</h1></br>

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/omerates760"><img alt="MyProfile" src="https://img.shields.io/badge/github-omerates-purple"/></a> 
</p>

## ScreenShots
<p align="center">
<img src="/screenshots/home.jpg" width="30%"/>
<img src="/screenshots/detail.jpg" width="30%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    -  A single-activity architecture, using the [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) to manage composable transactions.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Room-Database](https://developer.android.com/training/data-storage/room) - Room is a persistent library that is part of the Android jetpack. It is built on top of SQLite.
- [Material Design 3](https://m3.material.io/) is the latest version of Google’s open-source design system.

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
