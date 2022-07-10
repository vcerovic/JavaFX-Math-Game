<h1 align="center">JavaFX Math Game</h1>

![Admin Page Products](https://i.imgur.com/Pe0wRcI.png)

<br>

## Content
  1. [Info](#Info)
  2. [Technologies](#Technologies)
  3. [User guide](#Guide)
  4. [Functionality](#Functionality)
  5. [Database](#Database)


<br>
<br>

## <a name="Info"></a> Project Info

Beautiful math game built with JavaFX. There are three level difficulties, players ranking leaderboard, and profile customization.
The game introduces two abilities, the first is to get 50/50 chances of guessing an answer, and the second one is to reset the timer. 
Both of these options cost 1 point.

<br>
<br>

## <a name="Technologies"></a> Technologies
<ul>
       <li>Java</li>
       <li>JavaFX</li>
       <li>Hibernate</li>
       <li>MySQL</li>
       <li>FXML</li>
       <li>CSS</li>
</ul>


<br>
<br>

## <a name="Guide"></a> User guide
1. You need to execute a script for creating the database that is in the file `mysql/math_game_db.sql`
2. Modify the database connection configuration to suit your settings. All settings can be found in the file `src/main/resources/com/veljkocerovic/hibernate.cfg.xml`. 
3. Finally, you can start application by running maven command `mvn javafx:run`.


<br>
<br>

## <a name="Functionality"></a> Functionality

<br>

>**1.** Home page.

<br>

![Home](https://i.imgur.com/m2u5MQR.png)

<br>

>**2.** Options page.

<br>

![Options](https://i.imgur.com/X4oH4Nj.png)

<br>

>**3.** Manage profile.

<br>

![Profile](https://i.imgur.com/nHW4231.png)


<br>

>**4.** Ranking leaderboard.

<br>


![Leaderboard](https://i.imgur.com/Y91ZCVK.png)

<br>
<br>

<br>

>**5.** Gameplay.

<br>


![Gameplay](https://i.imgur.com/Pe0wRcI.png)

<br>
<br>

## <a name="Database"></a> Database Diagram

![Database Diagram](https://imgur.com/IdAckE0.png)
