package com.bloc.inherit;

class GoldenRetriever extends Dog {

    /*
     * play
     * Side-effect: 1. The Dog loses weight
     *				2. Every 3 play invocations, the Dog shrinks to a smaller size, if possible
     *				i.e. "large" -> "average" -> "small" -> "tiny"
     * @return nothing
     */
    void play() {
        setWeight(getWeight() - WEIGHT_LOST_FROM_FEEDING);
        if (getWeight() < MINIMUM_WEIGHT) {
            setWeight(MINIMUM_WEIGHT);
        }
        // Pre-increment play counter
        if (++mPlayCounter == 3) {
            changeSize(false);
            mPlayCounter = 0;
        }
    }

}