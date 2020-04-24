# MVVM-persion-Dictionary
This is a english-persian dictionary that use MVVM design pattern and room library for sqlite data base .  
### Room
As i mentioned earlier i use Room library for interact with my prepopulate database.
</br> Room support prepopulate database in version 2.2.0
```kotlin
    Room.databaseBuilder(application, WordDB::class.java, "w_database")
                    .createFromAsset("database/word_db")
                    .build()
```
### Screenshot

![img_1](/pic/pic_1.jpg)
![img_2](/pic/pic_2.jpg)
![img_3](/pic/pic_3.jpg)

### Resources
[ViewBinding](https://developer.android.com/topic/libraries/view-binding)

[pre-populate database](https://developer.android.com/jetpack/androidx/releases/room)
