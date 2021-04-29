# Weekly Reflection 

# Week 1 

Tasks Completed:
- Chose a project

This week I spent time brainstorming an idea for a project. I had an idea that I previously planned to do, but after thinking it through realized it might not be ideal for this project. My original idea was for an American Mahjong hand analyzer that would help players during the game by analyzing their tiles and telling them the best hand. However, I realized a few things. 

First, American Mahjong is not very popular, and most people don't know how to play it. It might be difficult for classmates and potential employers to relate to this program for a complicated game they don't know how to play. Next, in order to play, you need a special card with the year's hands printed on it. It's illegal to copy or distribute these cards online. By creating an application that tells players hand ideas, I would be going against the creater's wishes by publishing their hands and allowing people to play who don't own a card. 

So with these things in mind, I went to work brainstorming a new project idea. I ended up deciding on an application related to Stardew Valley, a popular video game. I love this idea because it's a great accessible game that a lot of people play, and a resource like the Stardew Valley Assistant will be helpful to people needing quick information. Also, for my final PHP project last year I did a project related to Stardew Valley as well, and I love that my portfolio will be themed and show different ways I'm solving problems in a specific game. 

### Week 2

Tasks completed:
- Set up repository in Github

This week has been crazy - I'm currently working two jobs for the next few weeks (last few weeks of one, first few weeks of another) and have had minimal time for school work unfortunately. However, I did manage to get my project repository set up and linked to the Student repository in Github. 

### Week 3

Tasks completed:
- Finished problem statement 

Still working two jobs, but trying so hard to get caught up. This week I finished adding my problem statement to my README and spent time getting caught up on my classwork. 

### Week 4

Tasks completed:
- Created project plan and planning documents 
- Created wireframes in Figma
- Created user stories
- Updated reflections 

So happy to finally have time to devote to my project. So far this week I sketched out my screens on scratch paper and then built them out as wireframes in Figma. I also spent time setting up the rest of the design and tracking files I need in my project structure, as well as starting to think big picture about what I need to do and learn for the rest of my project.

### Week 5

Tasks completed:
- Implemented first Dao and tables

Overall I'm starting to feel better about this semester, but I'm stressed about my project. I feel like I bit off more than I can chew, and am worried about doing a good job with this project while still maintaining good database design. I think the amount of data I need to work with may be over-ambitious for the time frame of this course. I thought I would be able to use a Stardew Valley API to pull in the data about each item that I needed, not realizing that none exist! I am exploring options to make this project more maneagable for V1 while still staying true to my original vision.

### Week 6

Tasks completed:
- Added the following tables: crops, notes, user, user_favorites
- Implemented Project Lombok
- Created UserDao and NoteDao (will have to switch these out for GenericDao)

I believe I've been comparing my potential application to already completed applications I've seen, and I need to focus on what is possible in 15 weeks vs. what is pie-in-the-sky. I've decided to focus on a subset of information for my V1 project to make sure I have all the functionality hammered out and working properly, and then in the future I can add more data and functionality. V1 will include standard grown crops and information about those crops, such as the seed price, base sell price, season grown, and recipes that crops appear in. This will give me a great framework for the rest of the project once it's built out, but give me more time to focus on the functionality and not how I'm going ot map out and insert thousands of database rows. 

I'm happy with my new direction and spent yesterday building out all the tables I needed for V1. Unfortunately, I then ran into a number of issues when trying to load up the data before each unit test because of the all the foreign keys and dependencies. I had originally planning on all items, no matter what they were, living in the Item column and linking foreign keys back to item_id, but I realized that won't be possible because then in some other tables, I'll need two separate columns to each have the same Item id column as the foreign key. My solution to this is to move recipes outside of the item column since they are groups of other items. However, I haven't yet figured out how I will deal with recipes that use other recipes as ingredients. Am planning on pulling in some others to help me troubleshoot that! 

### Week 7

Tasks completed:
- Implemented GenericDao
- Added role table 
- Created unit tests for Item and Note
- Implemented authorization and login

This week I added the GenericDao and converted all of my unit tests to use it. I was a bit concerned about it at first, but after getting used to it it is so easy! And works so much better than having a separate DAO for every entity. 

I also implemented authorization in my project. I haven't added sign up yet, but I am fully able to log in with users created directly in my database. Yay!

### Week 8

Tasks completed:
 
- Implemented basic javamail structure
- Added JSPs and Crop entity 
- Added CSS
- Added all crop data to database
- Added ProcessItem class to reduce duplicate 
- Added insert note functionality

I got a lot done this week! Technically it was two weeks because of spring break, and the extra time really paid off. I added styling to my project using the Bootstrap theme Sketchy from Bootswatch which I love working with and fits really well for Stardew Valley. I added template jsps to hold my header, nav, footer, etc to reduce duplicate code.

I also gathered and assembled all of the crop data and loaded it into the database, and it's really cool to be able to look up all the different items! While I'm not planning on including fish/forage/etc in V1 of my project, if I have enough time I may add them to the Item table so that they come up when users search for them, and I can output a message that the website doesn't yet support those items. I think that would be a nice touch! 

One issue that came up this week as I set up my different JSPs and servlets that link out from the results page and then return to it is realizing that I had to duplicate the same code over and over. The item data is sent with a request, so every time a user inserted a note they would go back to the same results page, but all of the item data would be gone. I sovled this by creating a Javabean that holds a few methods that reconfigure the item data, and I can call those methods and then reset the attributes every time I need it to reappear. I was hoping to set the attributes in the javabean as well but I couldn't get it to work - I'm not sure if the functionality isn't supported or if I was doing it wrong. When I have more time for nice-to-have project improvements I hope to go back to this.

### Week 9

Tasks completed:
- Added update note and delete note functionality 
- Added error handling for update and delete
- Added sign up functionality 
- Added Role entity 

This week I added update and delete functionality to my project along with user sign ups, which is very exciting! I've gotten a few weird error with logging in that have worried me a little, but they seem to resolve themselves if I refresh the page. It seems to come from logging in and out and back in too quickly when I'm testing different users. I'll need to keep an eye on that. 
I'm very excited about the update and delete functionality. Getting it working was very satisfying, although it took a bit of thinking on my part to figure out every way a user might try to access those pages and make sure they got appropriate error messages instead of null pointer exceptions. I'm hoping I caught them all, but I'm sure more will come up with time.

### Week 10

Tasks completed:
- Added recent searches functionality 
- Updated sign in page to forward to login page

This week I added recent searches functionality to my project. This shows a logged in users five most recent searches and I am very proud of it! It took a bit of thinking to figure out how I wanted it to work, but once I mapped it out I was able to implement it without much trouble. I wanted to make sure that each searched for item only appeared once in the recent searches list, so that the user wouldn't see a list of only two items if they kept referring to them back and forth. I have no yet come up with a way to clear out the recent searches table in the DB because I'm sure it will get quite large, but I am okay with saving that for later.

### Week 11

Tasks completed: 
- Added user favorites page
- Created configuration class to reduce duplicative code 
- Added styling 
- Added the ability to add and remove items from favorites 
- Added missing Hibernate mapping
- Implemented contact form using Javamail 

This week I added the ability to add and remove items from a favorites list and made a nice page that shows all the users favorites in a table on one page. It's not actually what I pictured in my original design docs, but I think it might actually be more useful. It's an easy way to see the data for a bunch of crops at once when a user is looking to compare and contrast seed and sell prices. I also created a configuring class that allowed me to move a bunch of duplicate code out of each of the servlets that I used to refresh all the item and page data after a user took action. I hated having the same code on so many pages, and having all the servlets forward to the configuring class instead of back to the results page fixed this. I also changed the majority of info sent via request to be sent via sessions.

I fleshed out the Javamail functionality to add a contact form where users can send an email directly to my email. I haven't set up anything to send a confirmation email to the user's email, but that is something I will consider in the future.

Finally, I added more card styling to the results page which made it look colorful and pretty!

### Week 12

Tasks completed: 
- Added ability for logged in users to change their password 
- Added ability for logged out users to reset their password via email using Javamail 
- Added extra error handling to protect Account page 

This week I focused on passwords - the ability to change a password for a logged in user and the ability to reset a password via email. This was the functionality I was most worried about adding to my project because I hadn't done it before or thought it through when I mapped out my MVPs. Luckily the addition of Javamail to my project really paid off, and after looking up the general process for using tokens to reset a password, I was able to implement password resets. There are a number of APIs that would have done this for, and I'm not sure if it's "cheating" or not to do it myself. It was great to learn how it worked, but it also seemed much easier than adding a new API to my project this late in the semester. Using an API to manage all fo this for me will definitely be something I will practice with in the future.

### Week 13
No project work for me this week - I moved to a new house and didn't have a computer for most of it! But I feel good about where my project is and feel confident I will be able to finish any additional styling and refactoring next week.

### Week 14

Tasks completed: 
- Changed servlets handling sensitive info using GET to use POST 
- Peer code review 

I feel a little bit silly right now. Due to an initially shaky understanding of GET vs POST I defaulted to using GET for all of my project without really thinking about it. However, I had a little extra time this morning and decided that once and for all I was going to understand the difference. Imagine my shock to realize that the difference was quite simple once I found a good explanation that made sense to me, and that I've done a ton of extra work on my project due to using GETs instead of POSTs. I correctly realized that having sensitive info in my URLs was a no-go so I used a bunch of servlets and sessions in order to hide that data, but much of my work could have been expedited by using POSTs from the start. I took the time today to convert all my GETs that should be POSTs into POSTs, and now I am sure I will always remember the difference. I have a lot of error handling in my project that I'm now wondering if it's even necessary, but for now I'm sure extra error handling won't hurt anything. 