package org.dush.intellij.plugin.k8.ui.jtree;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import org.dush.intellij.plugin.k8.KubeClientProvider;
import org.dush.intellij.plugin.k8.deployments.DefaultKubeDeploymentClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class provides child nodes of k8 <b>Deployment</b> for the main UI tree view.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class KubeDeploymentTreeProvider implements ComponentTreeNodeProvider
{
    @Override
    public List<KubeTreeNode> getNodes( String nameSpace )
    {
        List<KubeTreeNode> mutableTreeNodesList = new ArrayList<>();
        Optional<ApiClient> client = KubeClientProvider.getClient();
        if( client.isPresent() )
        {
            DefaultKubeDeploymentClient deploymentClient = new DefaultKubeDeploymentClient( client.get() );
            try
            {
                V1DeploymentList v1DeploymentList = deploymentClient.listPods( nameSpace );
                for( V1Deployment deployment : v1DeploymentList.getItems() )
                {
                    mutableTreeNodesList.add( new KubeTreeNode( deployment ) );
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
