package com.romanek.drugs.saved.model;

public record DrugRecordApplicationQuery(
    String applicationNumber,
    String manufacturerName,
    String substanceName
) {
}
