package org.dush.idea.plugin.k8.ui.jtree;

import org.dush.idea.plugin.k8.Utils;
import org.dush.idea.plugin.k8.ui.Configs;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * The class whose is responsible for creating main ui tree structure.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class KubeComponentTreeBuilder
{
    private final JTree jTree;
    private final JComboBox<String> comboNamespaces;

    /**
     * Build the tree nodes.
     *
     * @param jTree
     */
    public KubeComponentTreeBuilder( JTree jTree, JComboBox<String> comboNamespaces )
    {
        this.jTree = jTree;
        this.comboNamespaces = comboNamespaces;
    }

    public void buildFromSettingFile()
    {
        Utils.clearJTree( jTree, true );

        //set root node. this root node is hidden by default
        jTree.setModel( new DefaultTreeModel( new KubeTreeNode( "k8" ) ) );

        Properties properties = Configs.getInstance();
        buildNodes( properties );

    }

    private void buildNodes( @NotNull Properties properties )
    {
        String keySpace = "ui.componentTree.node";

        for( int i = 1; i <= properties.size(); i++ )
        {

            String name = ( String ) properties.get( ( keySpace + "." + i + ".name" ) );
            String provider = ( String ) properties.get( ( keySpace + "." + i + ".provider" ) );

            if( name != null && provider != null )
            {
                try
                {
                    DefaultMutableTreeNode node = buildNode( name, Class.forName( provider ) );

                    DefaultTreeModel model = ( DefaultTreeModel ) jTree.getModel();
                    DefaultMutableTreeNode root = ( DefaultMutableTreeNode ) model.getRoot();
                    root.add( node );
                    model.reload( root );

                }
                catch( Exception e )
                {
                    //fail safe
                    e.printStackTrace();
                }
            }
            else
            {
                break;
            }
        }
    }

    private DefaultMutableTreeNode buildNode( String value, Class<?> clz ) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        KubeTreeNode parentNode = new KubeTreeNode( value );
        var nodeProviderImpl = ( ComponentTreeNodeProvider ) clz.getDeclaredConstructor().newInstance();

        for( DefaultMutableTreeNode childNode : nodeProviderImpl.getNodes( ( String ) comboNamespaces.getSelectedItem() ) )
        {
            parentNode.add( childNode );
        }
        return parentNode;
    }


}
