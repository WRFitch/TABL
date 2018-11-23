# cs2001-18-19-2018-19-group-18 

## TABL - an app to allow users to order food on their phones, without needing an app for each restaurant. 

For each item on the TODO list, open a separate branch, implement the function, then merge when you're done. This should prevent any major merge conflicts and keep us delivering a testable app. The main branch of this repository should be the most polished version, any work in progress should be confined to its own branch until it is implemented and tested. See Contributing.md for details. 

[UI prototype](https://xd.adobe.com/view/0069b308-7ea3-4e31-4792-87b468c2fb61-7658/)

#### TODO (Android Application) 
##### Design
* update UI design based on feedback (tip under qr button, detailed item view/click outside item, set item quantity, possible issues with "sliding" type ui & android activity stack)
* add user flow to design doc 
* update prototype to fit UI design, including importing assets
* implement slider menu test - how will it work?
* implement different button designs

##### Frontend Development 
* implement basic prerequisites
  * AdapterView list items for Menus and Restaurants
  * import UI data such as colors and resources
* implement restaurant finder Activity 
  * consider preloading menu data in "high-data mode" 
  * custom TABL qr codes could be branded 
* implement menu Activity 
  * Implement slider menu design - slide along the row or use basic scroll wheel
  * consider swiping right to add item to order
  * consider swiping left to add to favourites 
* implement item detail view activity 
  * consider working with item detail as a composite view superimposed on current screen
* implement basket activity 
* implement payment activity 
* implement options/settings activity 

##### Backend Development 
* define backend structure - Firebase seems most logical option 
* learn how firebase works
* import data into firebase & test functionality. 

##### Testing 
* write JUnit tests for logic 
* write Espresso tests for UI 
* test UI and functionality with users 
