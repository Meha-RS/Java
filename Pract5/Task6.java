import java.util.*;

public class Task6{
    public static void main(String[] args){
        Result<Integer> success = Result.ok(42);
        Result<String> failure = Result.err("boom");

        System.out.println(success.get());
        System.out.println(failure.getError());

    }

    static final class Result<T>{
        private final T value;
        private final String error;

        private Result(T value, String error){
            this.value = value;
            this.error = error;
        }

        static <T> Result<T> ok(T value){
            return new Result<>(Objects.requireNonNull(value, "value"), null);
        }

        static <T> Result<T> err(String error){
            return new Result<>(null, Objects.requireNonNull(error, "error"));
        }

        boolean isOk(){
            return error == null;
        }

        T get(){
            if(!isOk())
                throw new IllegalStateException(error);
            return value;
        }

        String getError(){
            if(isOk())
                throw new IllegalStateException("no error");
            return error;
        }
    }
}