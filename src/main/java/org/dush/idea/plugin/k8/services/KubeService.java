package org.dush.idea.plugin.k8.services;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1ServiceList;

/**
 * Abstracting out kubernetes Service level operations.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeService
{
    /**
     * List k8 services for a given namespace.
     *
     * @return {@link io.kubernetes.client.openapi.models.V1ServiceList }
     * @throws ApiException when connection or execution failures occurred.
     */
    V1ServiceList listServices( String namespace ) throws ApiException;

}
