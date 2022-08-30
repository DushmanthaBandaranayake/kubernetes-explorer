package org.dush.idea.plugin.k8.ns;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1NamespaceList;

/**
 * Client for accessing k8 namespaces.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeNamespace
{
    /**
     * List k8 namespaces.
     *
     * @return {@link io.kubernetes.client.openapi.models.V1NamespaceList }
     * @throws ApiException when connection or execution failures occurred.
     */
    V1NamespaceList listNamespaces() throws ApiException;
}
