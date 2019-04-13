package com.daniel.iflostfind.domain;

import lombok.Getter;

public enum LossGroup {

    ALL("All"),
    GADGET("Gadget"),
    ANIMAL("Animal"),
    DOCUMENT("Document"),
    BAG("Bag");

    @Getter
    private final String displayName;

    LossGroup(String displayName) {
        this.displayName = displayName;
    }
}
