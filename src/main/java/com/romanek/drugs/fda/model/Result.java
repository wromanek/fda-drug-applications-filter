package com.romanek.drugs.fda.model;

public record Result(
    String authoritynumb,
    String safetyreportversion,
    String safetyreportid,
    String primarysourcecountry,
    String occurcountry,
    String transmissiondateformat,
    String transmissiondate,
    String reporttype,
    String serious,
    String receivedateformat,
    String receivedate,
    String receiptdateformat,
    String receiptdate,
    int fulfillexpeditecriteria,
    String companynumb,
    String duplicate,
    int seriousnesscongenitalanomali,
    int seriousnessdeath,
    int seriousnessdisabling,
    int seriousnesshospitalization,
    int seriousnesslifethreatening,
    int seriousnessother,
    ReportDuplicate reportduplicate,
    PrimarySource primarysource,
    Sender sender,
    Receiver receiver,
    Patient patient
) {
}
