package ru.gekov.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.gekov.model.AbstractBaseEntity;
import ru.gekov.to.AbstractTo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.StringJoiner;

public class ValidationUtil {

    public static <T> T checkNotFoundOptionalWithId(Optional<T> optional, int id) {
        checkNotFoundWithId(optional.isPresent(), id);
        return optional.get();
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFoundWithIdAndDate(T object, int id, LocalDate date) {
        return checkNotFound(object, "id=" + id + " date=" + date);
    }

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void checkNew(AbstractTo to) {
        if (!to.isNew()) {
            throw new IllegalArgumentException(to + ", transfer object id must be null");

        }
    }

    public static ResponseEntity<String> processBindingErrors(BindingResult result) {
        StringJoiner joiner = new StringJoiner("<br>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (msg != null) {
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    }
                });
        return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static <T extends AbstractBaseEntity> T checkIdMatch(T entity, int id) {
        if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " expected to be with id=" + id);
        }
        return entity;
    }
}
