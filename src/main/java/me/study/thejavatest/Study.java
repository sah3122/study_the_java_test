package me.study.thejavatest;

public class Study {
    private int limit;
    private StudyStatus status = StudyStatus.DRAFT;

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
}
