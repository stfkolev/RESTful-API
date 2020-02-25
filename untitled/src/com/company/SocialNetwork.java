package com.company;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class SocialNetwork implements BetterNet, Comparable {
    private String name;
    private List<User> users = new ArrayList<>();

    /*! Constructor */
    SocialNetwork(String fileName) {

        try {
            var file = new Scanner(new File(fileName));

            // Read social network name
            this.name = file.next();

            while(file.hasNextLine())

                this.users.add(new User(
                        file.next(),
                        file.next(),
                        file.next()
                ));

            file.close();
        } catch(Exception err) {
            System.out.println(err);
        }

    }

    /*! Get Methods */
    public List<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    /*! Set Methods */
    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /*! Overrides */

    @Override
    public void sravnenie(SocialNetwork obj) {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public void sort() {
        Collections.sort(this.users, Comparator.comparing(User::getId));
    }

    @Override
    public String toString() {

        var temp = new String();

        for(var user: this.users)
            temp += ( user + "\n" );

        return "Social network name: " + this.name + "\nUsers:\n\n" + temp;

    }

    public List unique_group() {
        return this.users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(User::getGroup))
                ),
                ArrayList::new
        ));
    }

    public List unique_subgroup() {
        return this.users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(User::getSubgroup))
                ),
                ArrayList::new
        ));
    }

    public static int subgroupsCount(SocialNetwork obj, String name) {
        var counter = 0;

        for(var user : obj.getUsers()) {
            if(user.getGroup().equals(name))
                counter++; // Not working, to be continued when I come back
        }

        return counter;
    }
}
