package org.dush.idea.plugin.k8;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class Constants
{
    /**
     * Combo box item Default - automatically try to resolve configs for connecting k8 API
     */
    public static final String CMB_BOX_AUTH_TYPE_DEFAULT = "Default";

    /**
     * Combo box item selecting conf file - select k8 admin conf file
     */
    public static final String CMB_BOX_AUTH_TYPE_FROM_CONF_FILE = "Select Conf File";

    public static final String JMENU_ITEM_SCALE = "Scale";
    public static final String JMEMU_ITEM_DELETE = "Delete";

    public static final String INTELLIJ_NOTIFICATION_GROUP = "kubernetes-explorer";

    public static final String PROGRESS_BAR_TITLE = "Kubernetes explorer";

    private Constants()
    {
        //hide explicit object creation
    }


}
