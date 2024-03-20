
### Simple Highscore Database Protocol (SHDP)
By Mason Sain

| Client Request | Server Response       | Description                              |
|----------------|-----------------------|------------------------------------------|
| ATTEMPTS n     | n and the attempts    | Get the attempts of account n            |
| ENTER n a      | n and the attempts    | Enter attempt a into account n           |
| DELETE n a     | n and the attempts    | Delete amount a from account n           |
| HIGHEST n      | n and highest attempt | Gets the highest score from the attempts |
| QUIT           | Quit the connection   |                                          |

## Data Structures
### GameScore class
Class GameScore will have the following private fields: <br>
Size Number <br>
Account Number <br>
Attempts Array with attempts <br>

And the following public methods: <br>
GameScore(int account); <br>
// Creates an account with an account number <br>
public void attempts(int account); <br>
// Returns all the attempts in an array format <br>
public void enter(int account, int accountScore); <br>
// Enters a new attempt under that account number and stores it <br>
public void delete(int account, int attempt); <br>
// Deletes an attempt from the account <br>
public int highest(int account); <br>
// Searches their account, decides what their highest attempt is <br>
public void quit(); <br>
// quits the connection <br>

### GameScore Collection
Array of Users(size 10) - account numbers are 0-9.

