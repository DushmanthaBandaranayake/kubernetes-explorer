package org.dush.idea.plugin.k8.ui.jtree;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.dush.idea.plugin.k8.KubeClientProvider;
import org.dush.idea.plugin.k8.pods.DefaultKubePodClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This class provides child nodes of k8 <b>pods</b> for the main UI tree view.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class KubePodTreeProvider implements ComponentTreeNodeProvider
{
    @Override
    public List<KubeTreeNode> getNodes( String nameSpace )
    {
        List<KubeTreeNode> mutableTreeNodesList = new ArrayList<>();
        Optional<ApiClient> client = KubeClientProvider.getClient();
        if( client.isPresent() )
        {
            DefaultKubePodClient podClient = new DefaultKubePodClient( client.get() );
            try
            {
                V1PodList v1PodList = podClient.listPods( nameSpace );
                for( V1Pod pod : v1PodList.getItems() )
                {
                    mutableTreeNodesList.add( new KubeTreeNode( pod ) );
                }
            }
            catch( Exception e )
            {
                mutableTreeNodesList.add( new KubeTreeNode( "{error getting pods}" ) );
                e.printStackTrace();
            }
        }
        return mutableTreeNodesList;
    }
}
