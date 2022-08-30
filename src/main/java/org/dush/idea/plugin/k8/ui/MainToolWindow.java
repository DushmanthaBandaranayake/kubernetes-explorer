package org.dush.idea.plugin.k8.ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.dush.idea.plugin.k8.Constants;
import org.dush.idea.plugin.k8.Utils;
import org.dush.idea.plugin.k8.ui.actions.AuthTypeComboBoxActionListener;
import org.dush.idea.plugin.k8.ui.actions.RefreshButtonActionHandler;
import org.dush.idea.plugin.k8.ui.actions.TreeNodeMouseClickListener;
import org.dush.idea.plugin.k8.ui.jtree.KubeJTreeCellRenderer;

import javax.swing.*;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class MainToolWindow
{
    private static MainToolWindow instance;
    private JComboBox<String> comboNamespaces;
    private JButton refreshButton;
    private JPanel panelMain;
    private JTree componentTree;
    private JComboBox<String> comboAuthType;
    private JLabel lblLog;

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

    /**
     * Initialize main window.
     *
     * @param toolWindow Intellij parent tool window. Currently, not used by this method.
     * @param project    current project.
     */
    private static void init( ToolWindow toolWindow, Project project )
    {

        //JButton setup
        var mainToolWindow = new MainToolWindow();
        mainToolWindow.refreshButton.setIcon( AllIcons.Actions.Refresh );
        mainToolWindow.refreshButton.
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
        mainToolWindow.comboAuthType.addItem( Constants.CMB_BOX_AUTH_TYPE_DEFAULT );
        mainToolWindow.comboAuthType.addItem( Constants.CMB_BOX_AUTH_TYPE_FROM_CONF_FILE );
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

    public JLabel getLblLog()
    {
        return lblLog;
    }

}
