package org.dush.intellij.plugin.k8;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;
import org.dush.intellij.plugin.k8.ui.MainToolWindow;
import org.dush.intellij.plugin.k8.ui.jtree.FileChooser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Use this class to get Kubernetes API client.
 * This class provides creating of {@link ApiClient} based on the user selected
 * logic from main UI.
 *
 * @author dushmantha
 * @since 1.0
 */
public class KubeClientProvider
{
    private static final Map<String,ApiClient> clientCache = new HashMap<>();

    /**
     * Get client by default values.
     *
     * <ul>
     *   <li>If $KUBECONFIG is defined, use that config file.
     *   <li>If $HOME/.kube/config can be found, use that.
     *   <li>If the in-cluster service account can be found, assume in cluster config.
     *   <li>Default to localhost:8080 as a last resort.
     * </ul>
     */
    @Nullable
    private static ApiClient initWithDefaultConfig()
    {
        try
        {
            return Config.defaultClient();
        }
        catch( Exception e )
        {
            Utils.fireErrorNotification( e, "Action get default kube client: " );
        }
        return null;
    }

    /**
     * Get an {@link ApiClient} with the given config file.
     *
     * @param configFilePath path to kube config file.
     */
    private static ApiClient initWithDefaultConfig( String configFilePath )
    {
        try
        {
            return Config.fromConfig( configFilePath );
        }
        catch( Exception e )
        {
            Utils.fireErrorNotification( e, "Get kube config from(" + configFilePath + ") failed: " );
        }
        return null;
    }

    /**
     * Get an {@link ApiClient} with the given config file.
     */
    @Nullable
    private static ApiClient getDefaultApiClient( String confFilePath )
    {
        if( clientCache.get( confFilePath ) == null )
        {
            clientCache.put( confFilePath, initWithDefaultConfig( confFilePath ) );
        }
        return clientCache.get( confFilePath );
    }

    /**
     * Get an default {@link ApiClient}
     */
    @Nullable
    private static ApiClient getDefaultApiClient()
    {
        if( clientCache.get( "DEFAULT" ) == null )
        {
            clientCache.put( "DEFAULT", initWithDefaultConfig() );
        }
        return clientCache.get( "DEFAULT" );
    }

    /**
     * Get an {@link ApiClient}. with the use selected type(from conf file of default tye).
     */
    public static @NotNull Optional<ApiClient> getClient()
    {
        JComboBox<String> comboAuthType = MainToolWindow.getInstance().getComboAuthType();

        if( Objects.equals( comboAuthType.getSelectedItem(), Constants.CMB_BOX_AUTH_TYPE_DEFAULT ) ||
                    Constants.CMB_BOX_AUTH_TYPE_DEFAULT.equals( comboAuthType.getSelectedItem() ) )
        {
            return Optional.ofNullable( getDefaultApiClient() );
        }
        else if( Objects.equals( comboAuthType.getSelectedItem(), Constants.CMB_BOX_AUTH_TYPE_FROM_CONF_FILE ) )
        {
            return Optional.ofNullable( getDefaultApiClient( FileChooser.getInstance().getSelectedFile().getPath() ) );
        }
        return Optional.empty();
    }
}
