package com.practice.dao.specification;





import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.practice.dao.utility.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generic class to build Search criteria to perform condition based Database operations using JPA Repository.
 *
 * @param <T>
 */
public class BaseSpecification<T> {

    private BaseSpecification() {
    }

    /**
     * Builds and returns Specification for Equals condition
     *
     * @param attribute
     * @param value
     * @return
     */
    protected static Specification withEqual(String attribute, Object value) {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }
    
    /**
     * Builds and returns Specification for Equals condition
     *
     * @param attribute
     * @param value
     * @return
     */
    protected static Specification greaterThan(String attribute, Integer value) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
    }

    /**
     * Builds and returns Specification for In condition
     *
     * @param attribute
     * @param values
     * @return
     */
    protected static Specification withIn(String attribute, Set<String> values) {
        if (!values.isEmpty() && values.size() == 1) {
            return withEqual(attribute, values.stream().findFirst().get());
        }
        return (root, query, cb) -> root.get(attribute).in(values);
    }

    /**
     * Builds and returns Specification for Equals condition for Nested Objects in One Level
     *
     * @param attributeParent
     * @param attribute
     * @param value
     * @return
     */
    protected static Specification withNestedEqual(String attributeParent,
                                                   String attribute, Object value) {
        return (root, query, cb) -> cb.equal(root.join(attributeParent).get(attribute), value);
    }

    /**
     * Builds and returns Specification for IN condition for Nested Objects in One Level
     *
     * @param attributeParent
     * @param attribute
     * @param values
     * @return
     */
    protected static Specification withNestedIn(String attributeParent, String attribute, Set<String> values) {
        return (root, query, cb) -> root.join(attributeParent).get(attribute).in(values);
    }

    protected static Specification withSortBy(Map<String, String> attributeMap) {
        return (root, query, cb) -> sortBy(root, cb, query, attributeMap);
    }

    protected static Predicate sortBy(Root root, CriteriaBuilder criteriaBuilder, CriteriaQuery query, Map<String, String> attributeMap) {
        List<Order> orderList = new ArrayList<Order>();
        if (attributeMap.size()>0) {
            attributeMap.forEach((colName, sortOrder) -> {
                if (!StringUtils.isEmpty(sortOrder) && sortOrder.equals("ASC")) {
                    orderList.add(criteriaBuilder.asc(root.get(colName)));
                } else {
                    orderList.add(criteriaBuilder.desc(root.get(colName)));
                }
            });
        }
        query.orderBy(orderList);
        return query.getRestriction();
    }

    protected static Specification withDistinct() {
        return (root, query, cb) -> addDistinct(root, cb, query);
    }

    protected static Predicate addDistinct(Root root, CriteriaBuilder criteriaBuilder, CriteriaQuery query) {
        query.distinct(true);
        return query.getRestriction();
    }

    /**
     * Builds and returns Specification for IN condition for Nested Objects in One Level
     *
     * @param attributeParent
     * @return
     */
    protected static Specification withNestedMany(String attributeParent, List<Map<String, Object>> fieldList) {
        return (root, query, cb) -> buildNestedCriteria(attributeParent, fieldList, root, cb);
    }

    protected static Predicate buildNestedCriteria(String attributeParent, List<Map<String, Object>> fieldList, Root root, CriteriaBuilder criteriaBuilder) {
        if (!CollectionUtils.isEmpty(fieldList)) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();
        fieldList.stream().forEach(e -> {
            Join parent = root.join(attributeParent);
            e.forEach((fieldName, fieldValue) -> {
                if (fieldValue instanceof Set) {
                    predicates.add(criteriaBuilder.in(parent.get(fieldName)).value(fieldValue));
                } else {
                    predicates.add(criteriaBuilder.equal(parent.get(fieldName), fieldValue));
                }
            });
        });
        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }

    /**
     * Builds and returns Specification for JSON_CONTAINS condition
     *
     * @param attribute
     * @param jsonString
     * @return
     */
    public static Specification jsonContains(String attribute, Object jsonString) {
        return (root, query, cb) ->
                cb.isTrue(cb.function("JSON_CONTAINS", Boolean.class, root.<String>get(attribute), cb.literal((String) jsonString)));
    }

    /**
     * Builds and returns Specification for LIKE condition
     *
     * @param attribute
     * @param value
     * @return
     */
    protected static Specification withLike(String attribute, Object value) {
        return (root, query, cb) -> cb.like(root.get(attribute), (String) value);
    }

    /**
     * Builds and returns Specification for LIKE condition for Nested Objects in One Level
     *
     * @param attribute
     * @param value
     * @return
     */
    protected static Specification withNestedLike(String attributeParent, String attribute, Object value) {
        return (root, query, cb) -> cb.like(root.join(attributeParent).get(attribute), (String) value);
    }

    /**
     * Builds and returns Specification for NULL condition
     *
     * @param attribute
     * @return
     */
    protected static Specification withNull(String attribute) {
        return (root, query, cb) -> cb.isNull(root.get(attribute));
    }

    /**
     * Builds and returns Specification for NULL condition for Nested Objects in One Level
     *
     * @param attribute
     * @return
     */
    protected static Specification withNestedNull(String attributeParent, String attribute) {
        return (root, query, cb) -> cb.isNull(root.get(attributeParent).get(attribute));
    }


    public static Specification withTimestampLessThanOrEqual(String attribute, Object value) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), (Timestamp) value);
    }

    public static Specification withTimestampGreaterThanOrEqual(String attribute, Object value) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), (Timestamp) value);
    }

    /**
     * Method to consolidate multiple Specifications and finalize the search criteria
     *
     * @param criteriaList
     * @return
     */
    public static Specification consolidateSpecifications(List<Criteria> criteriaList) {
        List<Specification> andSpecs = new ArrayList<>();
        List<Specification> orSpecs = new ArrayList<>();

        if (!CollectionUtils.isEmpty(criteriaList)) {

            for (Criteria criteria : criteriaList) {
                if (criteria.isOrOperator()) {
                    buildSpecification(orSpecs, criteria);
                } else {
                    buildSpecification(andSpecs, criteria);
                }
            }
        }

        Specification result = null;

        if (!CollectionUtils.isEmpty(andSpecs)) {
            result = andSpecs.get(0);
            for (int index = 1; index < andSpecs.size(); index++) {
                result = (result).and(andSpecs.get(index));
            }
        }

        if (!CollectionUtils.isEmpty(orSpecs)) {
            int index = 0;
            if (null == result) {
                result = orSpecs.get(index);
                index++;
            }
            for (; index < orSpecs.size(); index++) {
                result = (result).or(orSpecs.get(index));
            }
        }

        return result;
    }

    private static void buildSpecification(List<Specification> specifications, Criteria criteria) {

        switch (criteria.getOperator()) {
            case EQ:
                specifications.add(withEqual(criteria.getKey(), criteria.getValue()));
                break;
            case NESTED_EQ:
                specifications.add(withNestedEqual(criteria.getParentKey(), criteria.getKey(), criteria.getValue()));
                break;
            case IN:
                specifications.add(withIn(criteria.getKey(), criteria.getValues()));
                break;
            case NESTED_IN:
                specifications.add(withNestedIn(criteria.getParentKey(), criteria.getKey(), criteria.getValues()));
                break;
            case LIKE:
                specifications.add(withLike(criteria.getKey(), criteria.getValue()));
                break;
            case NESTED_LIKE:
                specifications.add(withNestedLike(criteria.getParentKey(), criteria.getKey(), criteria.getValue()));
                break;
            case NULL:
                specifications.add(withNull(criteria.getKey()));
                break;
            case NESTED_NULL:
                specifications.add(withNestedNull(criteria.getParentKey(), criteria.getKey()));
                break;
            default:
                buildAdditionalSpecification(specifications, criteria);
                break;
        }
    }

    private static void buildAdditionalSpecification(List<Specification> specifications, Criteria criteria) {
        switch (criteria.getOperator()) {
            case NESTED_MANY:
                specifications.add(withNestedMany(criteria.getParentKey(), criteria.getFieldMap()));
                break;
            case SORT_BY:
                specifications.add(withSortBy(criteria.getSortByMap()));
                break;
            case DISTINCT:
                specifications.add(withDistinct());
                break;
            case JSON_CONTAINS:
                specifications.add(jsonContains(criteria.getKey(), criteria.getValue()));
                break;
            case TIMESTAMP_LESS_THAN_OR_EQUAL_TO:
                specifications.add(withTimestampLessThanOrEqual(criteria.getKey(), criteria.getValue()));
                break;
            case TIMESTAMP_GREATER_THAN_OR_EQUAL_TO:
                specifications.add(withTimestampGreaterThanOrEqual(criteria.getKey(), criteria.getValue()));
                break;
            case GT:
                specifications.add(greaterThan(criteria.getKey(), (Integer)criteria.getValue()));
                break;
            default:
                break;
        }
    }

}
