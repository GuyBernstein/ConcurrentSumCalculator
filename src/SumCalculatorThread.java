public class SumCalculatorThread implements Runnable {
    private final NumberPool numberPool;

    public SumCalculatorThread(NumberPool pool) {//start the summing process
        numberPool = pool;
    }

    @Override
    public void run() {//use the thread's function override method run()
        numberPool.summarize();
    }
}
