# FeedViewer
App displays details of Simpsons and Starwars characters which are festched from restful web services. Below are some of screen caps of the App from Android phone and tablet.

### Android Phone:
<img src="/ScreenCaps/PhoneMainList.png" alt="Phone Main List" width="200px"/>&nbsp;&nbsp;<img src="/ScreenCaps/PhoneMainGrid.png" alt="Phone Main Grid" width="200px"/>&nbsp;&nbsp;<img src="/ScreenCaps/PhoneDetail.png" alt="Phone Detail" width="200px"/>&nbsp;&nbsp;<img src="/ScreenCaps/PhoneSearch.png" alt="Phone Search" width="200px"/>


### Android Tablet:
<img src="/ScreenCaps/TabletList.png" alt="Tablet List" width="275px"/>&nbsp;&nbsp;<img src="/ScreenCaps/TabletSearch.png" alt="Tablet Detail" width="275px"/>&nbsp;&nbsp;<img src="/ScreenCaps/TabletDetail.png" alt="Tablet Search" width="275px"/>


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
