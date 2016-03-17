package exceptions;

/**
 *
 * @author kaspe
 */
public class ErrorMessage
{

    private int code;
    private String message;

    public ErrorMessage(Throwable ex, int code)
    {
        this.code = code;
        this.message = ex.getMessage();
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
