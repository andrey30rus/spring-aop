package event.manager.springaop.util;
import lombok.Data;
import java.util.Collection;

@Data
public class CustomResponse<T> {
    private int code;
    private String message;
    private Collection<T> responseList;

    public CustomResponse(Collection<T> responseList, CustomStatus customStatus) {
        this.code = customStatus.getCode();
        this.message = customStatus.getMessage();
        this.responseList = responseList;

    }
}
