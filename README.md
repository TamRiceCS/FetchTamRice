Hello,

In this read me document I will go over the requirements and discuss decisions I made and why.

### Dev Kit
I developed the app in Android Studio in Kotlin. I have an old Google Pixel 3 I enjoy testing on, but I also know how to use the emulator.

### Processing the JSON file

I actually haven't used a JSON file in Android Studio. I have however, used a lot of the Room API and decided to put it to good work. I read up on the documentation on how to extract the JSON using volley here. I then saved it internally. For huge amounts of data, saving it all on the device is a bad idea. This isn't necessarily oppressively big with ~1000 entries. In the case it is oppressively big, the smarter decision is to call from a database saved in the cloud.

All the database loading and fetching is done in a Viewmodel, following the Google MVVM paradigm. Business Logic should be separated from UI logic. Once our internal database is ready, we can observe a change and update the UI.

### Display data as a list
 
The natural choice for this one is to use a RecyclerView! I used a vertical orientation.

### Filtering the Data

Since I used an internal database, the natural reaction is to filter w/ SQL. The SQL command is as follows "SELECT * FROM itemBacklog where itemBacklog.name not in ('', 'null') group by itemBacklog.itemID order by itemBacklog.itemID, itemBacklog.name ASC"

where itemBacklog.name not in ('', 'null')  eliminates blank and null names.
order by itemBacklog.itemID, itemBacklog.name ASC will sort by first the id then name
group by itemBacklog.itemID will place all items with the same itemID together

### Confusion
So I used another SQL command to check if there were any instances of repeated listIDs and found none. I interpreted the group together as cluster all similar items together in the list. I initially implemented a solution where for a single item in the list it could have multiple item names (as the item names share an ID, hence grouping). I reverted back to the current solution upon seeing there were no repeat listIDs. This also means that I didn't necessarily need to make a new primary key due to all listIds being unique. 

### Extras
I changed the icon and implemented some minimal GlassMorphism UI (I am a huge fan of this design scheme!) 
