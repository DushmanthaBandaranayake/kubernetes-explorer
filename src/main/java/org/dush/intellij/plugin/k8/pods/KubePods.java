package org.dush.intellij.plugin.k8.pods;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1PodList;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubePods
{
    V1PodList listPods( String namespace ) throws ApiException;
}
