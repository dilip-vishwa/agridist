package com.callforcode.agridist.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Document {
//    document
//
//    user_id - fk(user)
//    document_type_given - int - 1. aadhaar, 2. pan, 3. driving, 4. agriland, 5. passport
//    document_path - varchar - path of document in local filesystem.
//    document_verified - bool
//    document_verified_by - varchar - the person name who verified doc.
//    document_verified_by_org - varchar - the org name who verified doc.

    private int user_id;
    private int document_type_given;
    private String document_path;
    private boolean document_verified;
    private String document_verified_by;
    private String document_verified_by_org;
}
