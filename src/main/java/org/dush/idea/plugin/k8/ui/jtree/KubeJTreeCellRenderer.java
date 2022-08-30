package org.dush.idea.plugin.k8.ui.jtree;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Customized JTree cell renderer. This will decorate the tree with image icon etc.
 *
 * @author dushmantha
 * @since 1.0
 */
public class KubeJTreeCellRenderer extends DefaultTreeCellRenderer
{
    private static final Map<String,ImageIcon> imageIconCache = new HashMap<>();

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus )
    {
        super.getTreeCellRendererComponent( tree, value, selected, expanded, leaf, row, hasFocus );
        TreeNode parent = ( ( KubeTreeNode ) value ).getParent();

        if( parent != null )
        {

            if( Objects.equals( ( ( KubeTreeNode ) parent ).getUserObject(), "Nodes" ) )
            {
                addImageIcon( "icons/k8node-blue.png" );
            }

            else if( Objects.equals( ( ( KubeTreeNode ) parent ).getUserObject(), "Services" ) )
            {
                addImageIcon( "icons/k8service-blue.png" );
            }
            else if( Objects.equals( ( ( KubeTreeNode ) parent ).getUserObject(), "Pods" ) )
            {
                addImageIcon( "icons/k8pods-blue.png" );
            }
            else if( Objects.equals( ( ( KubeTreeNode ) parent ).getUserObject(), "Deployments" ) )
            {
                addImageIcon( "icons/k8deployment-blue.png" );
            }
            else
            {
                //no image
                addImageIcon( null );
            }
        }
        return this;
    }


    private void addImageIcon( String iconImage )
    {
        ImageIcon rendererIcon = null;
        if( imageIconCache.get( iconImage ) != null )
        {
            rendererIcon = imageIconCache.get( iconImage );
        }
        else if( iconImage != null )
        {
            URL resource = getClass().getClassLoader().getResource( iconImage );
            if( resource != null )
            {
                try
                {
                    rendererIcon = new ImageIcon( ImageIO.read( resource ) );
                    imageIconCache.put( iconImage, rendererIcon );

                }
                catch( Exception e )
                {
                    //If cannot load image, just continue
                }
            }
        }
        this.setIcon( rendererIcon );
    }

}
