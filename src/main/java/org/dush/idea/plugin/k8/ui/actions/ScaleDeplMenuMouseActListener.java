package org.dush.idea.plugin.k8.ui.actions;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import org.dush.idea.plugin.k8.KubeClientProvider;
import org.dush.idea.plugin.k8.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class ScaleDeplMenuMouseActListener implements ActionListener
{
    static String jsonPatchStr =
            "[{\"op\":\"replace\",\"path\":\"/spec/template/spec/terminationGracePeriodSeconds\",\"value\":0}]";

    @Override
    public void actionPerformed( ActionEvent e )
    {
        Optional<ApiClient> client = KubeClientProvider.getClient();
        if( client.isEmpty() )
        {
            Utils.fireErrorNotification( "Error creating kube client for scaling deployment " );
            return;
        }

        throw new RuntimeException( "This functionality not implemented in this version" );

        //AppsV1Api api = new AppsV1Api( client.get() );

//            if( MainToolWindow.getInstance() != null )
//            {
//                JTree componentTree = MainToolWindow.getInstance().getComponentTree();
//                KubeTreeNode component = ( KubeTreeNode ) componentTree.getLastSelectedPathComponent();
//                if( component.getUserObject() instanceof V1Deployment )
//                {
//                    V1Deployment deployment = ( V1Deployment ) component.getUserObject();
//                    if( deployment.getMetadata() != null )
//                    {
//                        String name = deployment.getMetadata().getName();
//                        String namespace = deployment.getMetadata().getNamespace();
//                        try
//                        {
//                            api.patchNamespacedDeploymentScale(
//                                    name,
//                                    namespace,
//                                    new V1Patch( jsonPatchStr ),
//                                    null,
//                                    null,
//                                    null,
//                                    null,
//                                    true );
//
//                        }
//                        catch( ApiException ex )
//                        {
//                            ex.printStackTrace();
//                            Utils.fireErrorNotification( ex, "Scale deployment " );
//                        }
//                    }
//                }
//
//            }

    }


}
