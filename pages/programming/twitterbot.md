---
title: How to build a simple twitter bot with Java
keywords: bot for twitter, java bot for twitter, simple java twitter bot, twitbot, twitter, twitter bot with java 
last_updated: February 20, 2018
tags: [programming]
summary: "Making simple twitter bot with java"
sidebar: mydoc_sidebar
permalink: twitterbot.html
folder: programming
---

![](../images/pages/twitterbot/Twitter.jpg)

In this post, I'm going to show you how to make a simple Twitter bot with JAVA

Here are the steps to build an Autobot for twitter.
(I'm using MAC with IntelliJ and going to use maven)

## Step 1
We need to include twitter4j dependency by including below in POM.xml

````xml
<!-- twitter dependencies -->
<dependency>
    <groupId>org.twitter4j</groupId>
    <artifactId>twitter4j-core</artifactId>
    <version>[4.0,)</version>
</dependency>
````
## Step 2
We need to go to application management and [create an application at Twitter.](https://apps.twitter.com/app/new)

![](../images/pages/twitterbot/Twitter Application Management.png)

I'm going to leave empty for 'Callback URL' for now.

After creating the Application, you need to click 'Key and Access Tokens' tab and create the Access Token with the button on the bottom of the page.
Be sure to keep Consumer Secret key secret.

![](../images/pages/twitterbot/Myappnametest.png)

## Step 3
After creating an access token. you will have consumer key, consumer secret key, access token, and access token secret.
If we implement these 4 random strings and a message to twit on Twitter(variable twitStr) in the following code. Our bot is ready to work. Have Fun~

````java
public class Twit {

    private String OAUTH_CONSUMER_KEY;
    private String OAUTH_CONSUMER_SECRET;
    private String OAUTH_ACCESS_TOKEN;
    private String OAUTH_ACCESS_TOKEN_SECRET;

    private String twitStr;


    private Twitter twitter;
    private Configuration configuration;
    
    public void update(String twitStr) throws TwitterException {
        this.configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(OAUTH_CONSUMER_KEY)
                .setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET)
                .setOAuthAccessToken(OAUTH_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET)
                .build();
        TwitterFactory tf = new TwitterFactory(this.configuration);
        this.twitter = tf.getInstance();
    

        StatusUpdate su = new StatusUpdate(twitStr);
        Status status = twitter.updateStatus(su);
    }

}
````

{% include links.html %}
