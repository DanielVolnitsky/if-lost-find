package com.daniel.iflostfind.domain;

import lombok.Getter;

public enum LossType {

    GADGET("Gadget"),
    ANIMAL("Animal"),
    DOCUMENT("Document"),
    BAG("Bag");

    @Getter
    private final String displayName;

    LossType(String displayName) {
        this.displayName = displayName;
    }
}
