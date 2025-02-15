# ExpenceTracker
# Android Expense Manager - Implementation Guide

## Project Setup
1. Create a new Android project in Android Studio:
    - Choose "Empty Activity" as your template
    - Select minimum SDK (recommended: API 24)
    - Use Kotlin as the programming language

## Dependencies
Add these to your app/build.gradle:
```gradle
dependencies {
    // Room
    def room_version = "2.6.1"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"

    // ViewModel and LiveData
    def lifecycle_version = "2.7.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    // Navigation Component
    def nav_version = "2.7.7"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
}
```

## Project Structure
```
com.example.expensemanager/
├── data/
│   ├── dao/
│   │   ├── CategoryDao.kt
│   │   └── ExpenseDao.kt
│   ├── entities/
│   │   ├── Category.kt
│   │   └── Expense.kt
│   ├── repository/
│   │   └── ExpenseRepository.kt
│   └── AppDatabase.kt
├── ui/
│   ├── home/
│   │   ├── HomeFragment.kt
│   │   └── HomeViewModel.kt
│   ├── categories/
│   │   ├── CategoriesFragment.kt
│   │   └── CategoriesViewModel.kt
│   └── MainActivity.kt
└── utils/
    └── Constants.kt
```