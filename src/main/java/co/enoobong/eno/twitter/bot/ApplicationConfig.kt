package co.enoobong.eno.twitter.bot

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application



/**
 * @author Ibanga Enoobong I
 * @since 18-Aug-17.
 */
@ApplicationPath("/")
class ApplicationConfig: Application() {

    override fun getClasses(): Set<Class<*>> {
        val resources = HashSet<Class<*>>()
        addRestResource(resources)

        return resources
    }

    private fun addRestResource(resources: HashSet<Class<*>>) {
        resources.add(Bot::class.java)
    }

}