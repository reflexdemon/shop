package org.shop.model;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vprasanna on 6/11/2016.
 */
@Document(collection = "orders")
public class Order {
    List<LineItem> lineItems = new ArrayList<>();
    Summary summary;
    @Indexed
    String username;
    @Id
    private String id;
    private OrderStatus status;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @LastModifiedBy
    private String updatedBy;
    @CreatedBy
    private String createdBy;
    private String notes;

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "lineItems=" + lineItems +
                ", summary=" + summary +
                ", username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                ", notes='" + notes + '\'' +
                '}';
    }
}
