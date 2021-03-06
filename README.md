# FeedViewer
App displays details of Simpsons and Starwars characters which are fetched from restful web services. Below are some of screencaps of the App from Android phone and tablet along with the points covered in the app based on excercise sheet


### Android Phone:
1. App displays only the text in the list inside the cardview.
2. List is implemented with RecyclerView and it is toggelable from List to Grid and viceversa 
3. Detail view displays Image, Title and Desciption using LinearLayout. 
4. App name will be shown in the title bar for list of items screen and Selected item will be shown in title bar for detailed view.
5. Bonus item: Search is implmented to filter out lists in list item view.

<img src="/ScreenCaps/PhoneMainList.png" alt="Phone Main List" width="200px"/>&nbsp;&nbsp;<img src="/ScreenCaps/PhoneMainGrid.png" alt="Phone Main Grid" width="200px"/>&nbsp;&nbsp;<img src="/ScreenCaps/PhoneDetail.png" alt="Phone Detail" width="200px"/>&nbsp;&nbsp;<img src="/ScreenCaps/PhoneSearch.png" alt="Phone Search" width="200px"/>


### Android Tablet:
1. App displays only the text in the list inside the cardview and Detail view will be populated once a list item is selected.
2. List is implemented with RecyclerView.
3. Detail view displays Image, Title and Desciption using LinearLayout. 
4. App name will be shown in the title bar.
5. Bonus item: Search is implmented to filter out lists in list item view.


<img src="/ScreenCaps/TabletList.png" alt="Tablet List" width="275px"/>&nbsp;&nbsp;<img src="/ScreenCaps/TabletSearch.png" alt="Tablet Detail" width="275px"/>&nbsp;&nbsp;<img src="/ScreenCaps/TabletDetail.png" alt="Tablet Search" width="275px"/>

### Building the App: 

As we need to build two different App from same code base I have used product flavors. Product flavors contains 'ApplicationId', 'Feed Url' and 'App Name' properties in it. Below is the product flavors thats being used in build.gradle
      
      
      
      productFlavors {
        SimpsonsViewer {
            applicationId "com.xfinity.feedviewer.simpsonsviewer"
            buildConfigField "String", "FEED_URL","\"http://api.duckduckgo.com/?q=simpsons+characters&format=json\""
            resValue "string", "app_name", "Simpsons Character Viewer"
        }


        StarwarsViewer {
            applicationId "com.xfinity.feedviewer.gotviewer"
            buildConfigField "String", "FEED_URL","\"http://api.duckduckgo.com/?q=star+wars+characters&format=json\""
            resValue "string", "app_name", "Star Wars Character Viewer"
        }
    }
      

To build the app ./gradlew clean build command will create both the apps. Below are the detailed steps to create both the apps 

```sh
//prerequiste
export ANDROID_HOME=/Users/[USER_NAME]/Library/Android/sdk //Set ANDROID_HOME
export JAVA_HOME=/Users/[USER_NAME]/jdk1.8 //Set ANDROID_HOME


git clone https://github.com/saravanan86/FeedViewer.git
cd FeedViewer/
./gradlew build clean
cd app/build/outputs/apk //This will list all APKs builded
```

Note: APKs that are built with latest code base is present in https://github.com/saravanan86/FeedViewer/tree/master/apks

### Dependecies

      compile 'com.android.support:appcompat-v7:23.4.0' //Need Android support library installed using Android SDK Manager
      compile 'com.android.support:recyclerview-v7:23.2.1' //Repository from jcenter()
      compile 'com.android.support:cardview-v7:23.0.0' //Repository from jcenter()
      compile 'com.squareup.picasso:picasso:2.5.0' //Repository from jcenter()

### Libraries and Components used:

1. Fragments: 
      
    Used Fragments as we need to support the view differently between phone and tablet. Also it is easy to dynamically adjust the UI elements in feauture if there is any need. I used fragments both for list and detail view for Phones and Used two panel fragments in the list fragment to place both list and detail for tablets. Below are the activity and fragment details used in this app
  - SplashScreen Activity -- Used to show progress bar while loading RSS Feed when the App loads
  - MainActivity/FeedListFragment -- Main activity and fragment combination to display feed items as list. For phone "activity_fragment.xml" and for Tablet "activity_two_fragment.xml" layouts are used.
  - FeedDetailActivity/FeedDetailFragment -- Detail view activity and fragment used for phone to display detail view. For tablet only FeedDetailFragment is used.

2. RecyclerView: 
      
    Used RecyclerView as we need to switch between Linearlayout and GridLayout. Also it makes it easy to implement animations and decorations.

3. Cardview: 
      
    Used cardview to provide a clean look for items in RecyclerView and also it makes it easy to add more items to the card in future.

4. Picasso: 
      
    Used to place Images to the view. This library has rich features related to image handling like easy progress indicator, error handling, async image loading.

5. Search: 
      
    Search widget is used to filterout the list items and update RecyclerView. Clicking on the updated RecyclerView will show the detail view.
