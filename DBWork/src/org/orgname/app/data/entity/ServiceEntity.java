package org.orgname.app.data.entity;

public class ServiceEntity {
    private int id;
    private String title;
    private float cost;
    private int durationSeconds;
    private String description;
    private double discount;
    private String mainImagePath;

    public ServiceEntity(int id, String title, float cost, int durationSeconds, String description, double discount, String mainImagePath) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.durationSeconds = durationSeconds;
        this.description = description;
        this.discount = discount;
        this.mainImagePath = mainImagePath;
    }

    public ServiceEntity(String title, float cost, int durationSeconds, String description, double discount, String mainImagePath) {
        this(-1, title, cost, durationSeconds, description, discount, mainImagePath);
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", durationSeconds=" + durationSeconds +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", mainImagePath='" + mainImagePath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getMainImagePath() {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        this.mainImagePath = mainImagePath;
    }
}
