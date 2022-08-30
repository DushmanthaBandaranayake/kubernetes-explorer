package org.dush.idea.plugin.k8.pods;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1PodList;

/**
 * Abstracting out kubernetes Pod level operations.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubePods
{
    /**
     * List k8 Pods for a given namespace.
     *
     * @return {@link io.kubernetes.client.openapi.models.V1PodList }
     * @throws ApiException when connection or execution failures occurred.
     */
    V1PodList listPods( String namespace ) throws ApiException;
}
