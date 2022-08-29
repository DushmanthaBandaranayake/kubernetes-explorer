package org.dush.intellij.plugin.k8.ui.actions;

import org.dush.intellij.plugin.k8.Constants;
import org.dush.intellij.plugin.k8.ui.jtree.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public class AuthTypeComboBoxActionListener implements ActionListener
{

    public AuthTypeComboBoxActionListener()
    {

    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        if( Objects.equals( e.getActionCommand(), "comboBoxChanged" ) )
        {
            String selectedItem = ( String ) ( ( JComboBox<String> ) e.getSource() ).getSelectedItem();
            if( Constants.CMB_BOX_AUTH_TYPE_FROM_CONF_FILE.equals( selectedItem ) )
            {
                JFileChooser configFileChooser = FileChooser.getInstance();
                configFileChooser.showOpenDialog( null );
            }
        }
    }
}
