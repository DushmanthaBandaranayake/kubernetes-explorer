package org.dush.intellij.plugin.k8;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * This class provides utility methods.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public final class Utils
{
    private Utils()
    {
        // This is an util class.no object creation allowed
    }

    /**
     * Fires IntelliJ pop up {@link NotificationType.ERROR} type message with the given Exception. The given prefix will be added
     * in front of the message.
     *
     * @param ex     Exception
     * @param prefix the prefix
     */
    public static void fireErrorNotification( Exception ex, String prefix )
    {
        NotificationGroupManager.getInstance()
                                .getNotificationGroup( Constants.INTELLIJ_NOTIFICATION_GROUP )
                                .createNotification( prefix + ex.getMessage() + " " + ex, NotificationType.ERROR )
                                .notify( null );
    }


    /**
     * Fires IntelliJ pop up {@link NotificationType.ERROR} type message with the given message.
     *
     * @param message Message
     */
    public static void fireErrorNotification( String message )
    {
        fireNotification( message, NotificationType.ERROR );
    }

    /**
     * Fires IntelliJ pop up with the given type and the message.
     *
     * @param message Message
     */
    public static void fireNotification( String message, NotificationType notificationType )
    {
        NotificationGroupManager.getInstance()
                                .getNotificationGroup( Constants.INTELLIJ_NOTIFICATION_GROUP )
                                .createNotification( message, notificationType )
                                .notify( null );

    }

    public static void clearJTree( JTree componentTree, boolean removeRoot )
    {
        DefaultTreeModel model = ( DefaultTreeModel ) componentTree.getModel();
        DefaultMutableTreeNode root = ( DefaultMutableTreeNode ) model.getRoot();
        if( root != null )
        {
            root.removeAllChildren();
            model.reload();

            if( removeRoot )
            {
                model.setRoot( null );
            }
        }
    }
}
