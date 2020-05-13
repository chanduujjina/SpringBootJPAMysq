package com.practice.dao.utility;

/**
 * Enumerator to hold basic conditional operators to build JPA Specification
 */
public enum Operator {
    EQ,
    IN,
    GT,
    NESTED_IN,
    NESTED_EQ,
    LIKE,
    NESTED_LIKE,
    NULL,
    NESTED_NULL,
    NESTED_MANY,
    SORT_BY,
    DISTINCT,
    JSON_CONTAINS,
    TIMESTAMP_LESS_THAN_OR_EQUAL_TO,
    TIMESTAMP_GREATER_THAN_OR_EQUAL_TO
}
