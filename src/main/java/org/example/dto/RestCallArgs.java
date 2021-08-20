package org.example.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class RestCallArgs {

  private String usersEmail;
  private String article;
  private LocalDateTime start_date;
  private LocalDateTime end_date;

  public RestCallArgs(String usersEmail, String article, LocalDateTime start_date, LocalDateTime end_date) {
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

  public LocalDateTime getStart_date() {
    return start_date;
  }

  public LocalDateTime getEnd_date() {
    return end_date;
  }
}
