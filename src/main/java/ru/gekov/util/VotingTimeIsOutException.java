package ru.gekov.util;

public class VotingTimeIsOutException extends RuntimeException {
    public VotingTimeIsOutException(String message) {
        super(message);
    }
}
