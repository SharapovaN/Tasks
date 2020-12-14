import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Create a graph of persons
        Person me = new Person("Natalia");
        Person bob = new Person("Bob", true);
        Person alisa = new Person("Alisa");
        Person clare = new Person("Clare");
        Person peggy = new Person("Peggy");
        Person johnny = new Person("Johnny");
        Person tom = new Person("Tom", true);
        Person andrew = new Person("Andrew");

        me.addFriend(bob, alisa, clare);
        bob.addFriend(peggy, andrew);
        alisa.addFriend(peggy);
        clare.addFriend(johnny, tom);

        //Create deque for adding person's friends
        Deque<Person> personDeque = new ArrayDeque<>();

        //Create set for adding checked friends
        Set<Person> setCheckedFriends = new HashSet<>();

        //Add my friends to friendsDeque
        for (Person p : me.getFriendsList()) {
            personDeque.addLast(p);
        }

        //Find mango seller
        while (true) {

            //Check if there is the person in the set
            Person personForChecking = personDeque.pop();
            if (!setCheckedFriends.contains(personForChecking)) {


                //If person is mango seller print his name to console and finish program
                if (personForChecking.isMangoSeller()) {
                    System.out.println("Mango seller is - " + personForChecking.getName());
                    return;
                }

                //If person isn't mango seller add him to the set, add his friends to deque
                else {
                    setCheckedFriends.add(personForChecking);
                    for (Person p : personForChecking.getFriendsList()) {
                        personDeque.addLast(p);
                    }
                }
            }

            //If deque is empty print "There isn't mango seller"
            if (personDeque.pop() == null) {
                System.out.println("There isn't mango seller");
                return;
            }
        }
    }
}
