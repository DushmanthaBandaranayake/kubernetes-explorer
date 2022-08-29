package org.dush.intellij.plugin.k8.services;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1ServiceList;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeService
{
    V1ServiceList listServices( String namespace ) throws ApiException;

}
