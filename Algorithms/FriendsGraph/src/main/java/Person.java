import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Person> friendsList = new ArrayList<>();
    private boolean isMangoSeller = false;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Person... people) {
        this.name = name;
        for (Person p : people) {
            friendsList.add(p);
        }
    }

    public Person(String name, boolean isMangoSeller, Person... people) {
        this(name, people);
        this.isMangoSeller = isMangoSeller;
    }

    public void addFriend(Person... persons) {
        for (Person p : persons) {
            friendsList.add(p);
        }
    }

    public List<Person> getFriendsList() {
        return friendsList;
    }

    public String getName() {
        return name;
    }

    public boolean isMangoSeller() {
        return isMangoSeller;
    }
}
