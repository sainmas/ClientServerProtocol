import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 A user account has an array that can be changed by
 enters and deletes.
 */
public class GameScore
{
    private int size;
    private double[] attempts;
    private Lock attemptsChangeLock;

    /**
     Constructs a user account with space of 10 attempts.
     */
    public GameScore()
    {
        size = 0;
        attempts = new double[10];
        attemptsChangeLock = new ReentrantLock();
    }

    /**
     Constructs a user account with a certain amount of attempts.
     @param attemptAmount the amount of attempts in given account
     */
    public GameScore(int attemptAmount)
    {
        size = 1;
        attempts = new double[attemptAmount];
        attemptsChangeLock = new ReentrantLock();
    }


    /**
     * Enters a score into the user account
     * @param score the amount to add to the attempt
     */
    public void enter(double score) {

        attemptsChangeLock.lock();
        try {
            // deletes first attempt if they try to enter anything above attempts.length
            if (size == attempts.length) {
                for (int i = 0; i < size; i++) {
                    if (i == size-1) {
                        attempts[size-1] = score;
                    } else {
                        attempts[i] = attempts[i + 1];
                    }
                }
            } else {
                attempts[size] = score;
                size++;
            }
        }
        finally {
            attemptsChangeLock.unlock();
        }
    }

    /**
     * Deletes attempt from the user account.
     * @param attempt the attempt to delete
     */
    public void delete(int attempt) {

        attemptsChangeLock.lock();
        try {
            for (int i = attempt; i < size; i++) {
                if (i == 9) {
                    attempts[attempt] = 0;
                } else {
                    attempts[i] = attempts[i + 1];
                }
            }
            size--;
        }
        finally {
            attemptsChangeLock.unlock();
        }
    }


    /**
     * Gets the highest score of the account.
     * @return the highest score
     */
    public String highest() {
        StringBuilder s = new StringBuilder("[");
        // save temp variables to go back to
        int highest = -999999999;
        int savedAttempt = -1;
        // loop over every attempt to see which one is the highest
        for (int i = 0; i < size; i++) {
            if(attempts[i] > highest) {
                savedAttempt = i;
            }
        }
        s.append(savedAttempt+1).append(" - ").append(attempts[savedAttempt]).append("]");
        return s.toString();
    }

    /**
     * Gets the attempts of the account.
     * @return all the attempts
     */
    public String getAttempts() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            s.append(i+1).append(" - ").append(attempts[i]).append(", ");
        }
        s.deleteCharAt(s.length()-1);
        s.deleteCharAt(s.length()-1);
        s.append("]");
        return s.toString();
    }
}