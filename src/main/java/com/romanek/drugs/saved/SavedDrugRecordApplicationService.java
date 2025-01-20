package com.romanek.drugs.saved;

import com.romanek.drugs.fda.FdaDrugRecordApplicationService;
import com.romanek.drugs.saved.model.DrugRecordApplicationQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavedDrugRecordApplicationService {

    public static final String COLLECTION_NAME = "savedDrugRecordApplication";
    private final FdaDrugRecordApplicationService fdaDrugRecordApplicationService;
    private final MongoTemplate mongoTemplate;

    public void saveApplicationByQuery(DrugRecordApplicationQuery saveDrugRecordApplicationQuery) {
        var application = fdaDrugRecordApplicationService.findApplicationByQuery(saveDrugRecordApplicationQuery);
        application.put("_id", saveDrugRecordApplicationQuery);
        var result = mongoTemplate.save(application, COLLECTION_NAME);
        log.info("Document saved under _id: {}", result.get("_id"));
    }

    public Page<Map<String, Object>> getSavedDrugRecordApplications(Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Document> collection = mongoTemplate.find(query, Document.class, COLLECTION_NAME);

        var result = collection.stream()
            .map(doc -> doc.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))).toList();

        return new PageImpl<>(result, pageable, result.size());
    }
}
