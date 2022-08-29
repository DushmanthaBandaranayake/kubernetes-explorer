package org.dush.intellij.plugin.k8.ui;

import org.dush.intellij.plugin.k8.ui.actions.ScaleDeplMenuMouseActListener;

import javax.swing.*;

import static org.dush.intellij.plugin.k8.Constants.JMenu_ITEM_DELETE;
import static org.dush.intellij.plugin.k8.Constants.JMenu_ITEM_SCALE;

/**
 * Singleton provider for pop up menus specific for K8 deployments
 * (eg - Right-clicking on kube deployment node in tree view will pop up this menu).
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class KubeDeploymentPopupMenu extends JPopupMenu
{
    private static KubeDeploymentPopupMenu instance;

    private KubeDeploymentPopupMenu()
    {
    }

    public static KubeDeploymentPopupMenu getInstance()
    {
        if( instance == null )
        {
            init();
        }
        return instance;
    }

    private static void init()
    {
        instance = new KubeDeploymentPopupMenu();
        JMenuItem scale = new JMenuItem( JMenu_ITEM_SCALE );
        JMenuItem delete = new JMenuItem( JMenu_ITEM_DELETE );
        scale.addActionListener( new ScaleDeplMenuMouseActListener() );
        instance.add( scale );
        instance.add( delete );
    }

}
