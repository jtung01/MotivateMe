# My Personal Project
## Motivate Me
##

About *Motivate Me*:


"Motivate Me!" is project that is similar to a "to-do list"
with the addition of a points based system.
This application will ask the user to assign exercises or workouts to certain days in the week
and the user can then check them off when accomplished on the set day. Each time a task is complete,
x amount of points is assigned to their rewards that they can later cash in for a cheat day treat.
The people who would use this application are those who want to stay in shape, get in shape, or get stronger
but lack the motivation to do it. The reason why I am interested in doing a project like this is because
I have experienced what it feels like to be unmotivated towards keeping in shape, especially during the pandemic.
So I want to provide something that will incentivize people to stick to their goals.

###
- Set your exercise routine
- Accomplish each task
- Earn points!
- Enjoy your reward:)

##
##User Stories
###
- As a user, I want to be able to add an exercise to my workout list
- As a user, I want to be able to see what exercises are on my workout list
- As a user, I want to be able to mark which exercises I have completed from my workout list
- As a user, I want to be able to delete unwanted exercises from my workout list
- As a user, I want to be able to see the reward points I have accumulated
- As a user, I want to be able to redeem my points for a reward when I have accumulated enough
- As a user, I want to be able to save my workout list and points
- As a user, I want to be able to load saved workout lists and points

###
##Phase 4: Task 2
####

Mon Nov 22 13:46:07 PST 2021
Added exercise: running

Mon Nov 22 13:46:12 PST 2021
Added exercise: swimming

Mon Nov 22 13:46:15 PST 2021
Added exercise: squats

Mon Nov 22 13:46:20 PST 2021
Dropped exercise: running

Mon Nov 22 13:46:32 PST 2021
Finished exercise: swimming



###
##Phase 4: Task 3
####
- I could get rid of the semantic coupling with the Exercise class WorkoutList class->
the two classes share similar methods getName() and toJson() that can be refactored into an abstract class
- I could also better the cohesion in the WorkoutList class by making a points class so WorkoutList is not dealing with
both points and exercises 
- Another cohesion problem could be the WorkoutAppGraphics class having the JDesktop, JInternalFrames, and JButtons 
added on and also having the actions for each button, this can be changed by making a new class for the actions alone 