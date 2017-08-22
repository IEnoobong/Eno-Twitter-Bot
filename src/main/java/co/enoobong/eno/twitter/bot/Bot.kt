package co.enoobong.eno.twitter.bot

import twitter4j.TwitterException
import twitter4j.TwitterFactory
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener


/**
 * @author Ibanga Enoobong I
 * @since 18-Aug-17.
 */
@WebListener
class Bot : ServletContextListener {

    private lateinit var scheduler: ScheduledExecutorService
    override fun contextDestroyed(sce: ServletContextEvent?) {
        scheduler.shutdown()
    }

    override fun contextInitialized(sce: ServletContextEvent?) {
        println("Started up!")
        scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler.scheduleAtFixedRate({
            run {
                retweetMyMentions()
            }
        }, 0, 5, TimeUnit.HOURS)
    }

    fun retweetMyMentions() {
        println("Started checking for mentions")
        val twitter = TwitterFactory.getSingleton()
        try {
            twitter.timelines().mentionsTimeline.forEach {
                if (!it.isRetweeted) {
                    twitter.retweetStatus(it.id)
                }
            }
        } catch (ex: TwitterException) {
            println("Something bad happened $ex")
        }
    }
}