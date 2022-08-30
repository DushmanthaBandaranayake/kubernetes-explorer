package org.dush.idea.plugin.k8.nodes;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1NodeList;

/**
 * Abstracting out kubernetes node level operations.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeNode
{
    /**
     * List k8 nodes (cluster machines)
     *
     * @return {@link io.kubernetes.client.openapi.models.V1NodeList }
     * @throws ApiException indicates connection or execution failures
     */
    V1NodeList listNodes() throws ApiException;
}
