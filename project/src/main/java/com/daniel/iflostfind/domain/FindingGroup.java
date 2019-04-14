package com.daniel.iflostfind.domain;

import lombok.Getter;

public enum FindingGroup {

    ALL("All"),
    GADGET("Gadget"),
    ANIMAL("Animal"),
    DOCUMENT("Document"),
    BAG("Bag");

    @Getter
    private final String displayName;

    FindingGroup(String displayName) {
        this.displayName = displayName;
    }
}
