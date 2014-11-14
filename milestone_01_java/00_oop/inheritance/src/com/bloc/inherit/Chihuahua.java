package com.bloc.inherit;

import java.lang.Override;

class Chihuahua extends Dog {

    @Override
    void feed() {
        mWeight += WEIGHT_GAINED_FROM_FEEDING;
        // Pre-increment feed counter
        if (++mFeedCounter == 5) {
            changeSize(true);
            mFeedCounter = 0;
        }
    }
}
