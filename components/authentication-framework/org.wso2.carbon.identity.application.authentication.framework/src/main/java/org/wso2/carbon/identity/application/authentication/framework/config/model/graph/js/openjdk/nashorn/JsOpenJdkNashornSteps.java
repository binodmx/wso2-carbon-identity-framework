/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
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

package org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.openjdk.nashorn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.authentication.framework.config.model.StepConfig;
import org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.AbstractJSContextMemberObject;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;

import java.util.Optional;

/**
 * Returns when context.steps[<step_number] is called.
 * Since Nashorn is deprecated in JDK 11 and onwards. We are introducing OpenJDK Nashorn engine.
 */
public class JsOpenJdkNashornSteps extends AbstractJSContextMemberObject implements AbstractOpenJdkNashornJsObject {

    private static final Log LOG = LogFactory.getLog(JsOpenJdkNashornSteps.class);

    public JsOpenJdkNashornSteps() {

    }

    public JsOpenJdkNashornSteps(AuthenticationContext context) {

        initializeContext(context);
    }

    @Override
    public boolean hasSlot(int step) {

        if (getContext() == null) {
            return false;
        } else {
            return getContext().getSequenceConfig().getStepMap().containsKey(step);
        }
    }

    @Override
    public Object getSlot(int step) {

        if (getContext() == null) {
            return AbstractOpenJdkNashornJsObject.super.getSlot(step);
        } else {
            return new JsOpenJdkNashornStep(getContext(), step, getAuthenticatedIdPOfStep(step));
        }
    }


    private String getAuthenticatedIdPOfStep(int step) {

        if (getContext().getSequenceConfig() == null) {
            //Sequence config is not yet initialized
            return null;
        }

        Optional<StepConfig> optionalStepConfig = getContext().getSequenceConfig().getStepMap().values().stream()
                .filter(stepConfig -> stepConfig.getOrder() == step).findFirst();
        return optionalStepConfig.map(StepConfig::getAuthenticatedIdP).orElse(null);
    }
}
