# Eno Twitter Bot Kotlin!
  This project contains a Twitter Bot to automate an activity (Retweeting all my mentions :smile: ) on my [Twitter Account](https://twitter.com/IEnoobong) Kotlin and Java EE web framework 
  
  It makes use of [Twitter4j](https://github.com/yusuke/twitter4j) to call the Twitter API
 
# Getting Started
Follow these steps to create
* clone the project `https://github.com/IEnoobong/Eno-Twitter-Bot.git`
* Create file `twitter4j.properties` in `main/resources` with the following credentials gotten from your [twitter dashboard](https://apps.twitter.com/)
>oauth.consumerKey=Your_Consumer_Key
 oauth.consumerSecret=Your_Consumer_Secret
 oauth.accessToken=Your_Access_Token
 oauth.accessTokenSecret=Your_Access_Token_Secret
 
 As the application is hosted on Heroku, you'd need to create an [account](https://dashboard.heroku.com/apps) (if you don't have one)
 
 If you're completely knew to heroku checkout this [Java guide](https://devcenter.heroku.com/articles/getting-started-with-java#introduction)
 
 Heroku Maven Plugin is used to deploy the application, (there are other ways too) so once all is set i.e creating your app run
 * `mvn clean heroku:deploy-war`
 Voila! your app should be deployed! Now you can save your self some mundane tasks! :wink:
 
 # How to contribute
 I'm more than welcome to contributions, If you are willing to contribute to the project feel free to make a fork and submit a pull request.
 You can hit me up on [@IEnoobong](https://twitter.com/IEnoobong) on Twitter
