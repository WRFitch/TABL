# BUGS

If you've got some free time, try to fix one of these. 

### UI
- Image assets display strangely - super low-res or zoomed in when accessing correctly. Issue seems to come from 3 areas:
  - Implementation - may not have implemented the imageview correctly (works fine with other assets though)
  - App is having trouble selecting the correct dpi to display images
   
- google maps isn't displaying on physical phone
  - app currently doesn't ask for location permissions, might be to do with that.  
  
- RecyclerItemClickListener.java has not been fully tested