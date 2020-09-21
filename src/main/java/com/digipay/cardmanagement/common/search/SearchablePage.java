package com.digipay.cardmanagement.common.search;

import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public class SearchablePage {
  private int page;
  private int total;
  private String order;
  private String direction;
  private Map<String, String> filter;
  private PageRequest pageRequest;
  private List<Searchable> searchables;
  // region getter and setter

  public int getPage() {
    return page;
  }

  public SearchablePage setPage(int page) {
    this.page = page;
    return this;
  }

  public int getTotal() {
    return total;
  }

  public SearchablePage setTotal(int total) {
    this.total = total;
    return this;
  }

  public String getOrder() {
    return order;
  }

  public SearchablePage setOrder(String order) {
    this.order = order;
    return this;
  }

  public String getDirection() {
    return direction;
  }

  public SearchablePage setDirection(String direction) {
    this.direction = direction;
    return this;
  }

  public Map<String, String> getFilter() {
    return filter;
  }

  public SearchablePage setFilter(Map<String, String> filter) {
    this.filter = filter;
    return this;
  }

  public PageRequest getPageRequest() {
    return pageRequest;
  }

  public SearchablePage setPageRequest(PageRequest pageRequest) {
    this.pageRequest = pageRequest;
    return this;
  }

  public List<Searchable> getSearchables() {
    return searchables;
  }

  public SearchablePage setSearchables(List<Searchable> searchables) {
    this.searchables = searchables;
    return this;
  }

  // endregion
}
