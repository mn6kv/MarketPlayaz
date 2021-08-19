package org.example.dto;

import java.util.Date;

public class RestCallArgs {

  private String usersEmail;
  private String article;
  private Date start_date;
  private Date end_date;

  public RestCallArgs(String usersEmail, String article, Date start_date, Date end_date) {
    this.usersEmail = usersEmail;
    this.article = article;
    this.start_date = start_date;
    this.end_date = end_date;
  }

  public String getUsersEmail() {
    return usersEmail;
  }

  public String getArticle() {
    return article;
  }

  public Date getStart_date() {
    return start_date;
  }

  public Date getEnd_date() {
    return end_date;
  }
}
