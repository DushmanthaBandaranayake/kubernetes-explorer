package org.dush.idea.plugin.k8.ui.actions;

import io.kubernetes.client.openapi.models.V1Deployment;
import org.dush.idea.plugin.k8.ui.menu.KubeDeploymentPopupMenu;
import org.dush.idea.plugin.k8.ui.jtree.KubeTreeNode;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class TreeNodeMouseClickListener implements MouseListener
{
    private final JTree jTree;

    public TreeNodeMouseClickListener( JTree jTree )
    {
        this.jTree = jTree;
    }

    @Override
    public void mouseClicked( MouseEvent e )
    {
        if( SwingUtilities.isRightMouseButton( e ) )
        {
            Object component = jTree.getLastSelectedPathComponent();

            if( ( ( KubeTreeNode ) component ).getUserObject() instanceof V1Deployment )
            {
                KubeDeploymentPopupMenu.getInstance().show( e.getComponent(), e.getX(), e.getY() );
            }
        }
    }

    @Override
    public void mousePressed( MouseEvent e )
    {
        //no operation
    }

    @Override
    public void mouseReleased( MouseEvent e )
    {
        //no operation
    }

    @Override
    public void mouseEntered( MouseEvent e )
    {
        //no operation
    }

    @Override
    public void mouseExited( MouseEvent e )
    {
        //no operation
    }
}
