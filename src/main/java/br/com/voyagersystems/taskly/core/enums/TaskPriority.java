package br.com.voyagersystems.taskly.core.enums;

import lombok.Getter;

@Getter
public enum TaskPriority {
    P1("Urgent"),
    P2("High"),
    P3("Medium"),
    P4("Low"),
    P5("No priority");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

}
