package de.piratentools.spickerrr2.model;

import java.util.Objects;

public record Package(String name, String key, String sourceType, String csvSeperator,
                      String csvQuote, String dataUrl, String colId, String colTitle,
                      String colTopic, String colKind, String colOwner, String colInfoUrl,
                      String colAbstract, String colDescription, String colMotivation,
                      String bookKey, String id) {


    @Override
    public boolean equals(Object o) {
        if (o instanceof Package p) {
            return Objects.equals(p.key(), key());
        }
        return false;
    }
}
