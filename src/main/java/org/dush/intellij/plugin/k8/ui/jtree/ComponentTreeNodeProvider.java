package org.dush.intellij.plugin.k8.ui.jtree;

import java.util.List;

/**
 * This interface provides abstraction for the main UI`s tree data.
 * By implementing this interface, a new node for the main UI`s tree can be added with adding
 * the reference to the <b>settings.properties</b> file.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public interface ComponentTreeNodeProvider
{
    List<KubeTreeNode> getNodes( String nameSpace );

}
