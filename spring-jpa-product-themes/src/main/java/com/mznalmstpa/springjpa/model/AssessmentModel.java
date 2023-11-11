package com.mznalmstpa.springjpa.model;

import com.mznalmstpa.springjpa.entity.AssessmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentModel {
    private Long id;
    private String code;
    private String name;
    private String categoryName;
    private int countChild;

    public AssessmentModel(AssessmentEntity entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        if (entity.getCategory() !=null){
            this.categoryName = entity.getCategory().getName();
        }

        if (entity.getChild() != null){
            this.countChild = entity.getChild().size();
        }
    }
}
