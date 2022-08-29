package org.dush.intellij.plugin.k8.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.dush.intellij.plugin.k8.Utils;
import org.dush.intellij.plugin.k8.ui.actions.AuthTypeComboBoxActionListener;
import org.dush.intellij.plugin.k8.ui.actions.RefreshButtonActionHandler;
import org.dush.intellij.plugin.k8.ui.actions.TreeNodeMouseClickListener;
import org.dush.intellij.plugin.k8.ui.jtree.KubeJTreeCellRenderer;

import javax.swing.*;

import static org.dush.intellij.plugin.k8.Constants.CMB_BOX_AUTH_TYPE_DEFAULT;
import static org.dush.intellij.plugin.k8.Constants.CMB_BOX_AUTH_TYPE_FROM_CONF_FILE;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class MainToolWindow
{
    private static MainToolWindow instance;
    private JComboBox<String> comboNamespaces;
    private JButton RefreshButton;
    private JPanel panelMain;
    private JTree componentTree;
    private JComboBox<String> comboAuthType;

    private MainToolWindow()
    {
    }

    public static MainToolWindow getInstance( ToolWindow toolWindow, Project project )
    {
        if( instance == null )
        {
            init( toolWindow, project );
        }
        return instance;
    }

    private static void init( ToolWindow toolWindow, Project project )
    {
        //JButton setup
        var mainToolWindow = new MainToolWindow();
        mainToolWindow.RefreshButton.
                addActionListener( a -> RefreshButtonActionHandler.performAction( project,
                        mainToolWindow.componentTree,
                        mainToolWindow.comboNamespaces,
                        mainToolWindow.comboAuthType ) );

        //JTree setup
        Utils.clearJTree( mainToolWindow.componentTree, true );
        mainToolWindow.componentTree.
                setCellRenderer( new KubeJTreeCellRenderer() );
        mainToolWindow.componentTree.
                addMouseListener( new TreeNodeMouseClickListener( mainToolWindow.componentTree ) );

        //JComboBox setup
        mainToolWindow.comboAuthType.removeAllItems();
        mainToolWindow.comboAuthType.addItem( CMB_BOX_AUTH_TYPE_DEFAULT );
        mainToolWindow.comboAuthType.addItem( CMB_BOX_AUTH_TYPE_FROM_CONF_FILE );
        mainToolWindow.comboAuthType.
                addActionListener( new AuthTypeComboBoxActionListener() );

        instance = mainToolWindow;

    }

    public static MainToolWindow getInstance()
    {
        return instance;
    }

    public JComboBox<String> getComboAuthType()
    {
        return comboAuthType;
    }

    public JComponent getContent()
    {
        return panelMain;
    }

    public JTree getComponentTree()
    {
        return componentTree;
    }

}
