import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureDemo {
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

//       CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//           try{
//               TimeUnit.SECONDS.sleep(10);
//               return "success";
//           } catch (Exception e) {
//               e.printStackTrace();
//               return "error";
//           }
//       });
//
//        try{
//            System.out.println("done=" + completableFuture.isDone());
//            TimeUnit.SECONDS.sleep(11);
//            System.out.println("done=" + completableFuture.isDone());
//            System.out.println("result=" + completableFuture.get());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    static class IntegerTask implements Callable<Integer> {
        public Integer call() {
            return new Random().nextInt();
        }
    }
}
