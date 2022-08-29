package org.dush.intellij.plugin.k8.nodes;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1NodeList;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeNode
{
    V1NodeList listNodes() throws ApiException;

    V1NodeList listNodes( String namespace );
}
