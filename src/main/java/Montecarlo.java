
import java.util.*;

/**
 * This is a little crazy, but imagine that you have three people that are each
 * "right" 80% of the time. My question to a statistician was "If two of the
 * people have the same answer, what are the odds that they are correct?
 *
 * Since I didn't get an answer I liked, I created this Monte Carlo simulation.
 * What I'm doing here is assuming that there are 'N' questions, and that the
 * "correct" answer is always 'a'.
 */
public class Montecarlo
{
    final static int N = 100000;
    char person1[] = new char[N];
    char person2[] = new char[N];
    char person3[] = new char[N];

    public static void main(String[] args)
    {
        new Montecarlo();
    }

    public Montecarlo() {
        populateFirstPerson();
        populateSecondPerson();
        populateThirdPerson();

        p1AndP2BothHaveAForAnswer();
        p1AndP2HaveSameAnswer();
        p1p2AndP3HaveSameAnswer();
    }

    private void populateThirdPerson() {
        //person3
        Random random = new Random();
        for ( int i=0; i<N; i++ )
        {
            int randomInt = random.nextInt(100);
            if (randomInt<41 || randomInt>60) person3[i] = 'a'; else person3[i] = 'b';
        }
    }

    private void populateSecondPerson() {
        //person2
        Random random = new Random();
        for ( int i=0; i<N; i++ )
        {
            int randomInt = random.nextInt(100);
            if (randomInt>19) person2[i] = 'a'; else person2[i] = 'b';
        }
    }

    private void populateFirstPerson() {
        //person1
        Random random = new Random();
        for ( int i=0; i<N; i++ )
        {
            int randomInt = random.nextInt(100);
            if (randomInt<80) person1[i] = 'a'; else person1[i] = 'b';
        }
    }

    private void p1AndP2BothHaveAForAnswer() {
        //--------------------------------------------------------------------------
        // look at the situation where person1 and person2 both have 'a' for their
        // answer
        //--------------------------------------------------------------------------
        int numOfA = 0;
        for ( int i=0; i<N; i++ )
        {
            char c1 = person1[i];
            char c2 = person2[i];
            char c3 = person3[i];
            if (c1=='a' && c2=='a') numOfA++;
        }
        System.out.println("# of cases where person1 and person2 have 'a' for an answer: " + numOfA + " out of " + N);
    }

    //--------------------------------------------------------------------------
    // look at the situation where person1 and person2 have the same answer.
    // out of these, what percentage is correct? (i.e., what % of these are 'a'?
    //--------------------------------------------------------------------------
    private void p1AndP2HaveSameAnswer() {
        int numSame = 0;
        int numCorrect = 0;
        for ( int i=0; i<N; i++ )
        {
            char c1 = person1[i];
            char c2 = person2[i];
            if (c1==c2) {
                numSame++;
                if (c1=='a') numCorrect++;
            }
        }
        float percentCorrect = (float)numCorrect/(float)numSame * 100.0f;
        System.out.println("\nP1 = P2");
        System.out.println("numSame: " + numSame);
        System.out.println("numCorrect: " + numCorrect);
        System.out.println("% correct:  " + percentCorrect);
    }

    //--------------------------------------------------------------------------
    // look at the situation where p1, p2, and p3 have the same answer.
    // out of these, what percentage is correct? (i.e., what % of these are 'a'?
    //--------------------------------------------------------------------------
    private void p1p2AndP3HaveSameAnswer() {
        int numSame = 0;
        int numCorrect = 0;
        for ( int i=0; i<N; i++ )
        {
            char c1 = person1[i];
            char c2 = person2[i];
            char c3 = person3[i];
            if (c1==c2 && c2==c3) {
                numSame++;
                if (c1=='a') numCorrect++;
            }
        }
        float percentCorrect = (float)numCorrect/(float)numSame * 100.0f;
        System.out.println("\nP1 = P2 = P3");
        System.out.println("numSame: " + numSame);
        System.out.println("numCorrect: " + numCorrect);
        System.out.println("% correct:  " + percentCorrect);
    }
}
