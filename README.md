# About

Hello, it's me. In this test project I tried to build android app on MVVM and clean architecture, using Dagger Hilt as DI and Apollo/GraphQl as remote datasource. This app is using the [CountriesGraphQl](https://trevorblades.github.io/countries/queries/country) backend. 

While developing, I followed official [android guide to building apps on clean arch](https://developer.android.com/topic/architecture), [GraphQl documentation](https://www.apollographql.com/docs/kotlin/) and [android Hilt guide](https://developer.android.com/training/dependency-injection/hilt-android). Aslo, I used some architecture components like LiveData and ViewModel.


# Project stucture

 - Data
 Everything related to datasources - repository implementations, mappers, result wrappers, local databases with dataconverters
 
  - DI
Dagger files - Room and Repositories modules, injected by using Dagger Hilt 

 - Domain
This is the domain layer and consists of the domain models as well as the usecases.
  - UI
 Presentation layer - Views, ViewModels, related adapters and СustomViews
 
  - Utils
This is where extensions and constants.

## Stack

- LiveData
- ViewModel
- MVVM
- Flow
- Coroutines
- Clean Architeture
- Hilt
- Apollo
- ViewBinding
- CustomView
- Room
