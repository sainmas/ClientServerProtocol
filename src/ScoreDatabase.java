/**
 A database consisting of multiple user accounts.
 */
public class ScoreDatabase
{
    private GameScore[] accounts;

    /**
     Constructs a database with a given number of accounts.
     @param size the number of accounts
     */
    public ScoreDatabase(int size)
    {
        accounts = new GameScore[size];
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new GameScore();
        }
    }

    /**
     * Enters attempt into a user account.
     * @param accountNumber the account number
     * @param score the amount to enter
     */
    public void enter(int accountNumber, double score) {
        GameScore account = accounts[accountNumber];
        account.enter(score);
    }

    /**
     * Deletes attempt from the database
     * @param accountNumber the account number
     * @param attempt the attempt to delete
     */
    public void delete(int accountNumber, int attempt) {
        GameScore account = accounts[accountNumber];
        account.delete(attempt-1);
    }

    /**
     * Gets the highest score of the user account.
     * @param accountNumber the account number
     * @return the highest score
     */
    public String highest(int accountNumber) {
        GameScore account = accounts[accountNumber];
        return account.highest();
    }

    /**
     * Gets the attempts of a user account.
     * @param accountNumber the account number
     * @return the attempts
     */
    public String getAttempts(int accountNumber)
    {
        GameScore account = accounts[accountNumber];
        return account.getAttempts();
    }
}