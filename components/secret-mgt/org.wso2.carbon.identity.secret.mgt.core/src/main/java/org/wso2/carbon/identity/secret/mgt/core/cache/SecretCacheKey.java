/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com).
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.secret.mgt.core.cache;

import org.wso2.carbon.identity.application.common.cache.CacheKey;

/**
 * Abstract representation of the secret cache key.
 */
public abstract class SecretCacheKey extends CacheKey {

    private final String cacheKey;

    public SecretCacheKey(String cacheKey, String tenantDomain) {

        this.cacheKey = cacheKey;
        this.tenantDomain = tenantDomain;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        SecretCacheKey that = (SecretCacheKey) o;
        return tenantDomain.equals(that.tenantDomain) && cacheKey.equals(that.cacheKey);
    }

    @Override
    public int hashCode() {

        int result = super.hashCode();
        result = 31 * result + cacheKey.hashCode();
        result = 31 * result + tenantDomain.hashCode();
        return result;
    }
}
