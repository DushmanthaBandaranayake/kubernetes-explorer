package org.dush.idea.plugin.k8.ui;

import org.dush.idea.plugin.k8.ui.jtree.KubeComponentTreeBuilder;

import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton provider for loading <b>settings.properties</b> file.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class Configs
{
    private Configs()
    {
        //hide explicit object creation
    }

    public static Properties getInstance()
    {
        return SingletonHolder.properties;
    }

    private static class SingletonHolder
    {
        static Properties properties = loadProperties();

        static Properties loadProperties()
        {
            Properties prop = new Properties();
            try( InputStream input = KubeComponentTreeBuilder.class.getClassLoader().getResourceAsStream( "settings.properties" ) )
            {
                prop.load( input );
                return prop;
            }
            catch( Exception e )
            {
                //error message. fail fast
                e.printStackTrace();
            }
            return prop;
        }


    }


}
