/*******************************************************************************
 * Copyright 2019 metaphore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.crashinvaders.vfx.common.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;

public class OptionalAction extends DelegateAction {

    private Condition condition;

    private boolean firstRun = true;
    private boolean checkPassed = false;

    @Override
    public void restart() {
        super.restart();
        firstRun = true;
        checkPassed = false;
    }

    @Override
    public void reset() {
        super.reset();
        condition = null;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    protected boolean delegate(float delta) {
        if (firstRun) {
            checkPassed = condition.check();
            firstRun = false;
        }
        if (checkPassed) {
            return action.act(delta);
        } else {
            return true;
        }
    }

    public interface Condition {
        boolean check();
    }
}
