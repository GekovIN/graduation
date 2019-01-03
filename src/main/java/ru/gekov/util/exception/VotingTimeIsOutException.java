package ru.gekov.util.exception;

public class VotingTimeIsOutException extends RuntimeException {
    public VotingTimeIsOutException(String message) {
        super(message);
    }
}
