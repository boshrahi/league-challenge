# android-code-challenge
A modern Android application showcasing best practices in Android development.

## 🛠 Build & Setup

### Requirements
- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 17
- Android SDK API 34
- Gradle 8.6

### Build Instructions
1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Run the app on an emulator or physical device

## 🏗 Architecture & Project Structure

This project follows Clean Architecture principles with a modular approach, utilizing Modern Android Development practices.

### Modules Structure
    .
    android-code-challenge/
    ├── kotlin_app/ # Main application module
    │ └── MainActivity # App entry point with navigation
    │
    ├── feature/ # Feature modules
    │ └── posts/ # Posts feature implementation
    │ ├── PostsScreen # UI implementation
    │ └── PostsViewModel # Presentation logic
    │
    ├── domain/ # Business logic layer
    │ └── league-post/ # Posts domain logic
    └── data/ # Data layer
    ├── model/ # Shared data models
    ├── network/ # Network handling
    ├── api/
    │ └── league/ # API implementation
    ├── league-auth/ # Auth data implementation
    └── league-posts/ # Posts data implementation


### Module Dependencies Flow
feature:posts → domain:league-post → data:league-posts → data:api:league → data:network

### Tech Stack
- **UI**: Jetpack Compose with Material 3
- **Architecture**: Clean Architecture + MVI
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation Compose
- **Image Loading**: Coil
- **Networking**: Retrofit
- **Build System**: Gradle with Kotlin DSL, Version Catalog
- **Animation**: Lottie
- **Testing**: JUnit4
- **Formatting**: Spotless

### Key Features
- Modern UI with Jetpack Compose
- Clean Architecture implementation
- Modular project structure
- CI/CD with GitHub Actions
- Gradle convention plugins for better build configuration

### NOTE
1. This repository has a CI/CD setup in place, allowing you to review Merge Requests and actions.