package com.romanek.drugs.fda.model;

public record Meta(
    String disclaimer,
    String terms,
    String license,
    String last_updated,
    MetaResults results) {
}
