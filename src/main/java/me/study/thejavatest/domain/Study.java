package me.study.thejavatest.domain;

import me.study.thejavatest.StudyStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Study {
    @Id @GeneratedValue
    private Long id;
    private int limit;
    private StudyStatus status = StudyStatus.DRAFT;
    private Member owner;

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    private String name;

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0 보다 커야 한다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "limit=" + limit +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}
