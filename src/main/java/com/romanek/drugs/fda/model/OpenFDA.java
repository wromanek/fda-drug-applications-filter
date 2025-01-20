package com.romanek.drugs.fda.model;

import java.util.List;

public record OpenFDA(
    List<String> application_number,
    List<String> brand_name,
    List<String> generic_name,
    List<String> manufacturer_name,
    List<String> product_ndc,
    List<String> product_type,
    List<String> route,
    List<String> substance_name,
    List<String> rxcui,
    List<String> spl_id,
    List<String> spl_set_id,
    List<String> package_ndc,
    List<String> unii,
    List<String> nui,
    List<String> pharm_class_epc,
    List<String> pharm_class_cs,
    List<String> pharm_class_moa,
    List<String> pharm_class_pe) {
}
