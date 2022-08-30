package org.dush.idea.plugin.k8.ui.menu;

import org.dush.idea.plugin.k8.Constants;
import org.dush.idea.plugin.k8.ui.actions.ScaleDeplMenuMouseActListener;

import javax.swing.*;

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
        JMenuItem scale = new JMenuItem( Constants.JMENU_ITEM_SCALE );
        JMenuItem delete = new JMenuItem( Constants.JMEMU_ITEM_DELETE );
        scale.addActionListener( new ScaleDeplMenuMouseActListener() );
        instance.add( scale );
        instance.add( delete );
    }

}
