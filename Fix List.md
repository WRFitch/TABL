# Fixes

If you've got some free time, try to fix one of these. 

### UI
- Image assets display strangely - super low-res or zoomed in when accessing correctly. Issue seems to come from 3 areas:
  - Implementation - may not have implemented the imageview correctly (works fine with other assets though)
  - App is having trouble selecting the correct dpi to display images
   
- clean up findRestaurantActivity

- MenuActivity recyclerView isn't displaying correctly

- MoreActivity often crashes

- 

###Project Architecture
- At some point we're going to need to refactor the methods that get the default data 
  - this can probably be left until a better solution is found during sprint 4

- fix up resource directories

- add local data caching