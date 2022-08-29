package org.dush.intellij.plugin.k8.ui.jtree;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import org.dush.intellij.plugin.k8.KubeClientProvider;
import org.dush.intellij.plugin.k8.services.DefaultKubeServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This class provides child nodes of k8 <b>Service</b> for the main UI tree view.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class KubeServicesTreeProvider implements ComponentTreeNodeProvider
{
    @Override
    public List<KubeTreeNode> getNodes( String nameSpace )
    {
        List<KubeTreeNode> mutableTreeNodesList = new ArrayList<>();
        Optional<ApiClient> client = KubeClientProvider.getClient();
        if( client.isPresent() )
        {
            DefaultKubeServiceClient serviceClient = new DefaultKubeServiceClient( client.get() );
            try
            {
                V1ServiceList v1ServiceList = serviceClient.listServices( nameSpace );
                for( V1Service service : v1ServiceList.getItems() )
                {
                    mutableTreeNodesList.add( new KubeTreeNode( service ) );
                }
            }
            catch( Exception e )
            {
                mutableTreeNodesList.add( new KubeTreeNode( "{error getting service}" ) );
                e.printStackTrace();
            }
        }

        return mutableTreeNodesList;
    }
}
