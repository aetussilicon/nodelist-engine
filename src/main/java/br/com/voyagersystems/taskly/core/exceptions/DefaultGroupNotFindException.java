package br.com.voyagersystems.taskly.core.exceptions;

public class DefaultGroupNotFindException extends RuntimeException {
    public DefaultGroupNotFindException() {
        super("Default group not found");
    }
}
