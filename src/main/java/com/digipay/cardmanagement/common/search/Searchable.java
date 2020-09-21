package com.digipay.cardmanagement.common.search;

import java.io.Serializable;

public class Searchable implements Serializable {
  private String name;
  private String field;
  private SearchableOperation operation;
  private Object value;
  private SearchableType type;
  private Class dataClass = null;

  public Searchable(String name, String field, SearchableOperation operation, SearchableType type) {
    this.name = name;
    this.field = field;
    this.operation = operation;
    this.type = type;
  }

  public Searchable(
      String name, String field, SearchableOperation operation, SearchableType type, Object value) {
    this.name = name;
    this.field = field;
    this.operation = operation;
    this.type = type;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public Searchable setName(String name) {
    this.name = name;
    return this;
  }

  public String getField() {
    return field;
  }

  public Searchable setField(String field) {
    this.field = field;
    return this;
  }

  public SearchableOperation getOperation() {
    return operation;
  }

  public Searchable setOperation(SearchableOperation operation) {
    this.operation = operation;
    return this;
  }

  public Object getValue() {
    return value;
  }

  public Searchable setValue(Object value) {
    this.value = value;
    return this;
  }

  public SearchableType getType() {
    return type;
  }

  public void setType(SearchableType type) {
    this.type = type;
  }

  public Class getDataClass() {
    return dataClass;
  }

  public Searchable setDataClass(Class dataClass) {
    this.dataClass = dataClass;
    return this;
  }

  @Override
  public String toString() {
    return "Searchable{"
        + "name='"
        + name
        + '\''
        + ", field='"
        + field
        + '\''
        + ", operation="
        + operation
        + ", value="
        + value
        + ", type="
        + type
        + ", dataClass="
        + dataClass
        + '}';
  }
}
