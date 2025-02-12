package com.java._4.model;

import com.java._4.base.BaseEntity;
import com.java._4.constants.CollectionNames;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = CollectionNames.COURSES)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends BaseEntity {
    @Id
    private String id;
    private String name;
}
