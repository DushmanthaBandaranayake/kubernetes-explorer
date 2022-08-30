package org.dush.idea.plugin.k8.ui.actions;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import org.dush.idea.plugin.k8.Constants;
import org.dush.idea.plugin.k8.KubeClientProvider;
import org.dush.idea.plugin.k8.Utils;
import org.dush.idea.plugin.k8.ns.DefaultKubeNamespaceClient;
import org.dush.idea.plugin.k8.ns.KubeNamespace;
import org.dush.idea.plugin.k8.ui.MainToolWindow;
import org.dush.idea.plugin.k8.ui.jtree.FileChooser;
import org.dush.idea.plugin.k8.ui.jtree.KubeComponentTreeBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Optional;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class RefreshButtonActionHandler
{
    private RefreshButtonActionHandler()
    {
        //this class intended to be used statically .
    }

    public static void performAction(
            Project project,
            JTree componentTree,
            JComboBox<String> cmbBoxNamespaces,
            JComboBox<String> comboAuthType )
    {
        if( Constants.CMB_BOX_AUTH_TYPE_FROM_CONF_FILE.equals( comboAuthType.getSelectedItem() )
                    && FileChooser.getInstance().getSelectedFile() == null )
        {
            Utils.fireNotification( "Please select kube conf file.", NotificationType.INFORMATION );
        }
        else
        {
            ProgressManager.getInstance().run( new Task.Backgroundable( project, Constants.PROGRESS_BAR_TITLE )
            {

                public void run( @NotNull ProgressIndicator progressIndicator )
                {
                    try
                    {
                        progressIndicator.setIndeterminate( false );
                        progressIndicator.setFraction( 0.10 );
                        progressIndicator.setText( Constants.PROGRESS_BAR_TITLE );

                        Optional<ApiClient> client = KubeClientProvider.getClient();
                        if( client.isEmpty() )
                        {
                            throw new IllegalArgumentException( "Error creating k8 client" );
                        }

                        MainToolWindow.getInstance().getLblLog().setText( "Connected to : " + ( client.get().getBasePath() != null ? client.get().getBasePath() : "error connecting.." ) );
                        fillNamespaceComboBox( cmbBoxNamespaces );

                        progressIndicator.setFraction( 0.50 );
                        progressIndicator.setText( Constants.PROGRESS_BAR_TITLE );

                        new KubeComponentTreeBuilder( componentTree, cmbBoxNamespaces ).buildFromSettingFile();

                        // Finished
                        progressIndicator.setFraction( 1.0 );
                        progressIndicator.setText( "Kubernetes explorer : Finished" );
                    }
                    catch( Exception e )
                    {
                        cmbBoxNamespaces.removeAllItems();
                        Utils.clearJTree( componentTree, false );
                        Utils.fireErrorNotification( e, "Error initializing " );
                    }
                }
            } );
        }

    }

    private static void fillNamespaceComboBox( JComboBox<String> comboNamespaces ) throws ApiException
    {
        Optional<ApiClient> client = KubeClientProvider.getClient();
        if( client.isPresent() )
        {
            KubeNamespace KubeNamespace = new DefaultKubeNamespaceClient( client.get() );
            V1NamespaceList v1NamespaceList = KubeNamespace.listNamespaces();
            Object temSelectedItem = comboNamespaces.getSelectedItem();
            comboNamespaces.removeAllItems();
            for( V1Namespace item : v1NamespaceList.getItems() )
            {
                comboNamespaces.addItem( item.getMetadata() != null ? item.getMetadata().getName() : "{error getting details}" );
            }
            if( temSelectedItem != null )
            {
                comboNamespaces.setSelectedItem( temSelectedItem );
            }
        }
    }
}
