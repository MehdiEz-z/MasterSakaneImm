package org.example.clientservice.handlers.exceptionHandler;
import java.io.Serial;
public class ResourcesNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ResourcesNotFoundException(String msg) {
        super(msg);
    }
}
