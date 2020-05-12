package com.practice.dao.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class to add Criteria Objects
 */
public class CriteriaBuilder {

    private List<Criteria> criteriaList;

    /**
     * Method to add Criteria to a list
     *
     * @param criteria
     * @return
     */
    public CriteriaBuilder addCriteria(Criteria criteria) {
        if (criteria == null) {
            return this;
        }

        if (this.criteriaList == null) {
            this.criteriaList = new ArrayList<>();
        }

        this.criteriaList.add(criteria);
        return this;
    }

    /**
     * Simple method to return criteriaList
     *
     * @return
     */
    public List<Criteria> build() {
        return this.criteriaList;
    }
}
