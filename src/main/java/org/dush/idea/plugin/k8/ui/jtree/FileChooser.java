package org.dush.idea.plugin.k8.ui.jtree;

import javax.swing.*;

/**
 * Singleton JFile chooser.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class FileChooser
{
    private FileChooser()
    {
        //avoid obj creation
        //do any customization here
    }

    public static JFileChooser getInstance()
    {
        return SingletonHolder.fileChooser;
    }

    private static class SingletonHolder
    {
        public static JFileChooser fileChooser = new JFileChooser();
    }
}
