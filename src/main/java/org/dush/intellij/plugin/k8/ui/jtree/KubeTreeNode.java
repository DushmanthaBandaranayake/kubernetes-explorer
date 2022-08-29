package org.dush.intellij.plugin.k8.ui.jtree;

import io.kubernetes.client.openapi.models.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

/**
 * Customized {@link javax.swing.JTree} node.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class KubeTreeNode extends DefaultMutableTreeNode
{

    public KubeTreeNode( Object userObject )
    {
        super( userObject, true );
    }

    /**
     * This toString method is used to get tree node name.
     *
     * @return name of the node.
     */
    @Override
    public String toString()
    {
        if( userObject instanceof String )
        {
            return ( String ) userObject;
        }
        else if( userObject instanceof V1Node && ( ( V1Node ) userObject ).getMetadata() != null )
        {
            StringBuilder label = new StringBuilder();
            label.append( ( ( V1Node ) userObject ).getMetadata().getName() );

            if( ( ( V1Node ) userObject ).getStatus() != null && ( ( V1Node ) userObject ).getStatus().getConditions() != null )
            {
                ( ( V1Node ) userObject ).getStatus().getConditions()
                                         .stream()
                                         .filter( c -> Objects.equals( c.getType(), "Ready" ) )
                                         .map( V1NodeCondition::getStatus )
                                         .findFirst().ifPresent( c -> label.append( " [ Ready =" ).append( c ).append( "]" ) );

                //  ( ( V1Node ) userObject ).getMetadata()
                //                         .getLabels().get( "node-role.kubernetes.io/master" );

            }

            return label.toString();
        }
        else if( userObject instanceof V1Service && ( ( V1Service ) userObject ).getMetadata() != null )
        {
            return ( ( V1Service ) userObject ).getMetadata().getName();
        }
        else if( userObject instanceof V1Pod && ( ( V1Pod ) userObject ).getMetadata() != null )
        {
            V1Pod pod = ( ( V1Pod ) userObject );
            return pod.getMetadata().getName() + " [" + ( pod.getStatus() != null ? pod.getStatus().getPhase() : "unknown" ) + "]";
        }
        else if( userObject instanceof V1Deployment && ( ( V1Deployment ) userObject ).getMetadata() != null )
        {
            StringBuilder sb = new StringBuilder();
            V1Deployment deployment = ( ( V1Deployment ) userObject );
            sb.append( deployment.getMetadata().getName() );
            sb.append( "[" );
            if( deployment.getStatus() != null )
            {
                sb.append( " Replicas = " ).append( deployment.getStatus().getReplicas() );
                sb.append( " AvailableReplicas = " ).append( deployment.getStatus().getAvailableReplicas() );
                sb.append( " ReadyReplicas = " ).append( deployment.getStatus().getReadyReplicas() );
            }
            sb.append( "]" );
            return sb.toString();
        }
        else
        {
            return userObject != null ? userObject.toString() : "{error loading}";
        }
    }
}
