package Modele;

public class OrdonnanceurSimple extends Thread {

    private final Object lock = new Object();
    private boolean isPaused = false;
    private int appel_descente = 400;
    public Runnable monRunnable;

    public OrdonnanceurSimple(Runnable _monRunnable) {
        monRunnable = _monRunnable;
    }

    public void pause() {
        isPaused = true;
    }

    public void setDescente(){
        this.appel_descente =  (int) (this.appel_descente * 0.8);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void resumeThread() {
        synchronized (lock) {
            isPaused = false;
            lock.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(appel_descente);
                synchronized (lock) {
                while (isPaused) {
                    lock.wait();
                    }
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            monRunnable.run();
        }
    }
}
