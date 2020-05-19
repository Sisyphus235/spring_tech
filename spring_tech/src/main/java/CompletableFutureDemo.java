import java.util.Random;
import java.util.concurrent.*;

public class CompletableFuture {
    public static void main(String[] args) {
        //创建异步服务
        ExecutorService service = Executors.newFixedThreadPool(10);
        //接收异步任务返回的结果
        Future<Integer> future = service.submit(new IntegerTask());

        try {
            //获取任务结果，blocking
            Integer result = future.get();
            System.out.println("Result form the task is " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }
    }

    static class IntegerTask implements Callable<Integer> {
        public Integer call() {
            return new Random().nextInt();
        }
    }
}
