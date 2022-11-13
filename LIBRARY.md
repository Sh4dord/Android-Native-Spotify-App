# Libraries for this project

## Hilt

I Use this library to allow me to use Dependency Injection.
This is the Package recommended by developer.android to avoid me to do dependency injection hand by hand.

With this package, I will be able to put media player, repository in memory to ue it in all app by instanciating only one
time

But I didn't had the time to finish to implement it to make the player work on all Activities
If I Had the item, I rollback Fragment decomposition to make 2 Activites with playerController on both screen

## Glide
I wanted to load network image and It was the quickly way I found to do it

## Gson
Use to convert JsonString to Object usable by ViewModel

## ViewModels packages
I decide to work with ViewModel and LiveData as advice android.developer website with Coroutines to deal with async functions
I try to follow the adviced architecture for an Android App
https://developer.android.com/topic/architecture

https://developer.android.com/topic/libraries/architecture/viewmodel?hl=fr#use-coroutines

https://developer.android.com/topic/libraries/architecture/coroutines?hl=fr#livedata



