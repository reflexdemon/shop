package org.shop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by vprasanna on 12/29/16.
 */
@Document(collection = "appLogs")
public class AppLogger {

  @Id
  private String id;
  private Date createTimeStamp = new Date();
  private String message;
  private String username;
  private String action;
  private AppLogLevel level = AppLogLevel.TRACE;

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets create time stamp.
   *
   * @return the create time stamp
   */
  public Date getCreateTimeStamp() {
    return createTimeStamp;
  }

  /**
   * Sets create time stamp.
   *
   * @param createTimeStamp the create time stamp
   */
  public void setCreateTimeStamp(Date createTimeStamp) {
    this.createTimeStamp = createTimeStamp;
  }

  /**
   * Gets message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets username.
   *
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets action.
   *
   * @return the action
   */
  public String getAction() {
    return action;
  }

  /**
   * Sets action.
   *
   * @param action the action
   */
  public void setAction(String action) {
    this.action = action;
  }

  /**
   * Gets level.
   *
   * @return the level
   */
  public AppLogLevel getLevel() {
    return level;
  }

  /**
   * Sets level.
   *
   * @param level the level
   */
  public void setLevel(AppLogLevel level) {
    this.level = level;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AppLogger{");
    sb.append("id='").append(id).append('\'');
    sb.append(", createTimeStamp=").append(createTimeStamp);
    sb.append(", message='").append(message).append('\'');
    sb.append(", username='").append(username).append('\'');
    sb.append(", action='").append(action).append('\'');
    sb.append(", level=").append(level);
    sb.append('}');
    return sb.toString();
  }
}
