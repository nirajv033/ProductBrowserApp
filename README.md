Product Browser App

Summary of Business Requirements

The Product Browser App is designed to provide users with a seamless experience to browse, search, and view detailed information about products. The main requirements include:
	•	Display a list of products fetched from a remote API.
	•	Allow searching products by name or keywords.
	•	Provide a detailed screen for each product, including description, price, rating, and images.
	•	Support category-based product filtering.
	•	Ensure a consistent experience across Android and iOS using Kotlin Multiplatform (KMP).

Project Architecture Overview  
The project is structured using a clean architecture approach with clear separation of concerns:
Product Browser App
├── commonMain        # Shared Kotlin Multiplatform code
 │   ├── model         # Data models (Product, Review, etc.)
 │   ├── repository    # ProductRepositoryImpl for API interactions
 │   ├── usecase       # GetProductsUseCase, SearchProductsUseCase
 │   ├── viewmodel     # ProductViewModel for state management
 │   └── network       # ApiService for Ktor HTTP calls
├── androidApp        # Android-specific code
 │   ├── MainActivity.kt
 │   └── Compose UI screens
├── iosApp            # iOS-specific code
 │   ├── MainViewController.swift
 │   └── SwiftUI Compose integration
└── commonTest        # Unit tests for repository and use cases


Key Technologies Used:
	•	Kotlin Multiplatform (KMP) – share code between Android and iOS.
	•	Jetpack Compose – for Android UI.
	•	SwiftUI + ComposeUIViewController – for iOS UI.
	•	Ktor Client – for network calls.
	•	Kotlinx Serialization – for JSON parsing.
	•	Kamel Image Library – for image loading.
	•	JUnit + kotlinx.coroutines.test + Ktor MockEngine – for unit testing.


Instructions to Build and Run the App

Android
	1.	Environment:
	•	Android Studio Otter | 2025.2.1
	•	Gradle Version: 8.14.3
	•	Android Gradle Plugin: 8.11.2
	2.	Steps:
	•	Open the project in Android Studio.
	•	Let Gradle sync and download dependencies.
	•	Run the app on an emulator or physical device.
	•	The first screen will load the product list automatically.
	3.	Running Unit Tests:
	•	In Android Studio, open ProductRepositoryImplTest.kt and click Run.
	•	Or use the command line:  ./gradlew :composeApp:commonTest
		• 	Test reports are available at: composeApp/build/reports/tests/test/index.html.

iOS
	1.	Environment:
	•	Xcode Version: 26.1.1 (17B100)
	•	Run on iPhone 17 Simulator
	2.	Steps:
	•	Open the iosApp folder in Xcode.
	•	Make sure the shared KMP framework (ComposeApp) is built.
	•	Build and run the project on the iPhone simulator.
	•	The first screen will fetch and display the product list using the shared ProductViewModel.


Trade-offs and Assumptions
	•	The network layer uses Ktor with MockEngine for unit tests; in production, a real backend API URL is assumed.
	•	For simplicity, some optional fields in JSON are ignored using ignoreUnknownKeys = true.
	•	iOS integration relies on ComposeUIViewController, which may not support some advanced Compose animations.
	•	The app currently does not implement offline caching. Future versions may include local database support.
	•	Tested primarily on Android Emulator and iPhone 17 Simulator; behavior may vary on other devices.
	•	Image loading uses Kamel, which is efficient but may have differences in iOS rendering compared to Android.
