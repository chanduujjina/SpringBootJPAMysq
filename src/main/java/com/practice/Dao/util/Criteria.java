package com.practice.dao.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class to holds the basic properties required to build a Criteria
 *
 * @param <T>
 */
public class Criteria<T> {

    private String parentKey;
    private String key;
    private T value;
    private Set<T> values;
    private Operator operator;
    private boolean orOperator;
    private boolean isDistinct;
    private List<Map<String, Object>> fieldMap;
    private Map<String, Object> sortByMap;
    

	public Map<String, Object> getSortByMap() {
		return sortByMap;
	}

	public Criteria setSortByMap(Map<String, Object> sortByMap) {
		this.sortByMap = sortByMap;
		return this;
	}

	public String getParentKey() {
        return parentKey;
    }

    public Criteria setParentKey(String parentKey) {
        this.parentKey = parentKey;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Criteria setKey(String key) {
        this.key = key;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Criteria<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public Operator getOperator() {
        return operator;
    }

    public Criteria setOperator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public Set<T> getValues() {
        return values;
    }

    public Criteria setValues(Set<T> values) {
        this.values = values;
        return this;
    }

    public boolean isOrOperator() {
        return orOperator;
    }

    public Criteria setOrOperator(boolean orOperator) {
        this.orOperator = orOperator;
        return this;
    }
    
    public List<Map<String, Object>> getFieldMap() {
        return fieldMap;
    }

    public Criteria setFieldMap(List<Map<String, Object>> fieldMap) {
        this.fieldMap = fieldMap;
        return this;
    }

	public boolean isDistinct() {
		return isDistinct;
	}

	public void setDistinct(boolean isDistinct) {
		this.isDistinct = isDistinct;
	}
    
    
    
}
