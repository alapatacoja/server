package es.ieslvareda.server;

public class Result<T> {

    public static class Success<T> extends Result<T>{
        private T data;

        public Success(T data){
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Success{" +
                    "data=" + data +
                    '}';
        }
    }

    public static class Error extends Result{
        private int code;
        private String message;

        public Error(int code, String message){
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
