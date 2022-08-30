package org.dush.idea.plugin.k8.ui.jtree;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import org.dush.idea.plugin.k8.KubeClientProvider;
import org.dush.idea.plugin.k8.nodes.DefaultKubeNodeClient;
import org.dush.idea.plugin.k8.nodes.KubeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class provides child nodes of k8 cluster machines(cluster nodes) for the main UI tree view.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class ClusterNodeTreeProvider implements ComponentTreeNodeProvider
{
    @Override
    public List<KubeTreeNode> getNodes( String nameSpace )
    {
        List<KubeTreeNode> mutableTreeNodesList = new ArrayList<>();
        Optional<ApiClient> client = KubeClientProvider.getClient();
        if( client.isPresent() )
        {
            KubeNode nodeClient = new DefaultKubeNodeClient( client.get() );
            try
            {
                V1NodeList nodes = nodeClient.listNodes();
                for( V1Node node : nodes.getItems() )
                {
                    KubeTreeNode mutableTreeNode = new KubeTreeNode( node );
                    mutableTreeNodesList.add( mutableTreeNode );
                }
            }
            catch( Exception e )
            {
                mutableTreeNodesList.add( new KubeTreeNode( "{error getting nodes}" ) );
            }
        }
        return mutableTreeNodesList;
    }
}
