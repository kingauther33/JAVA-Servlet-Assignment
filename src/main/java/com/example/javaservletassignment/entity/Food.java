package com.example.javaservletassignment.entity;

import com.example.javaservletassignment.annotation.Column;
import com.example.javaservletassignment.annotation.Entity;
import com.example.javaservletassignment.annotation.Id;
import com.example.javaservletassignment.util.SQLDataTypes;
import com.example.javaservletassignment.util.ValidationUtil;

import java.util.Date;
import java.util.HashMap;

@Entity(tableName = "foods")
public class Food {

    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = SQLDataTypes.INTEGER)
    private int id;
    @Column(columnName = "name", columnType = SQLDataTypes.VARCHAR50)
    private String name;
    @Column(columnName = "category", columnType = SQLDataTypes.VARCHAR255)
    private String category;
    @Column(columnName = "description", columnType = SQLDataTypes.VARCHAR255)
    private String description;
    @Column(columnName = "thumbnail", columnType = SQLDataTypes.TEXT)
    private String thumbnail;
    @Column(columnName = "price", columnType = SQLDataTypes.DOUBLE)
    private double price;
    @Column(columnName = "sell_date", columnType = SQLDataTypes.DATETIME)
    private Date sellDate;
    @Column(columnName = "edit_date", columnType = SQLDataTypes.DATETIME)
    private Date editDate;
    @Column(columnName = "status", columnType = SQLDataTypes.INTEGER)
    private int status;

    public Food() {
        this.name = "";
        this.category = "";
        this.description = "";
        this.thumbnail = "";
        this.price = 0;
        this.sellDate = new Date();
        this.editDate = new Date();
        this.status = 1;
    }

    public Food(String name, String category, String description, String thumbnail, double price, Date sellDate, Date editDate, int status) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.sellDate = sellDate;
        this.editDate = editDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // ki???m tra ?????i t?????ng c?? h???p l??? hay kh??ng.
    public boolean isValid() {
        return getErrors().size() == 0;
    }

    // tr??? v??? danh s??ch l???i
    public HashMap<String, String> getErrors() {
        HashMap<String, String> errors = new HashMap<>();
        if (name == null || name.length() == 0) {
            errors.put("name", "Vui l??ng nh???p t??n s???n ph???m");
        }

        if (category == null || category.length() == 0) {
            errors.put("name", "Vui l??ng nh???p t??n category");
        }

        if (description == null || description.length() == 0) {
            errors.put("description", "Vui l??ng nh???p m?? t??? s???n ph???m");
        }

        if (thumbnail == null || thumbnail.length() == 0) {
            errors.put("thumbnail", "Vui l??ng l???a ch???n ???nh cho s???n ph???m.");
        } else if (!ValidationUtil.checkUrl(thumbnail)) {
            errors.put("thumbnail", "???nh sai ?????nh d???ng, vui l??ng nh???p v??o m???t link.");
        }

        if (price ==  0) {
            errors.put("price", "Vui l??ng nh???p gi?? cho s???n ph???m.");
        }

        if (price ==  0) {
            errors.put("price", "Vui l??ng nh???p gi?? cho s???n ph???m.");
        }
        return errors;
    }
}
