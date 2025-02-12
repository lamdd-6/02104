package com.java._4.model;

import com.java._4.base.BaseEntity;
import com.java._4.constants.CollectionNames;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = CollectionNames.CLASSROOMS)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classroom extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String courseId;
}
