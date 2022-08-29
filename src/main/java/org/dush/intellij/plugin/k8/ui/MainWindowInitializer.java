package org.dush.intellij.plugin.k8.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

/**
 * Entry point to the IntelliJ Idea plugin.
 * This class provides the implementation for the
 * method{@link ToolWindowFactory#createToolWindowContent(Project, ToolWindow)}
 * for creating the Tool window.
 *
 * @author dushmantha
 * @since 1.0
 */
public class MainWindowInitializer implements ToolWindowFactory
{
    public void createToolWindowContent( final Project project, final ToolWindow toolWindow )
    {
        final ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        final Content content = contentFactory.createContent( MainToolWindow.getInstance( toolWindow, project ).getContent(),
                "",
                false );
        toolWindow.getContentManager().addContent( content );
    }
}
