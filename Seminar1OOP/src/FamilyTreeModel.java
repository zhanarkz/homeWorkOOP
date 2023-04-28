import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.FileInputStream;

public class FamilyTreeModel {
    private List<Human> humanList;

    public FamilyTreeModel() {
        humanList = new ArrayList<>();
    }

    public void addHuman(Human human) {
        humanList.add(human);
    }

    public List<Human> getHumanByName(String name) {
        List<Human> res = new ArrayList<>();
        for (Human human : humanList) {
            if (human.getName().equals(name)) {
                res.add(human);
            }
        }
        return res;
    }

    public List<Human> getAllHuman() {
        return new ArrayList<>(humanList);
    }

    public void sortByBirthDate() {
        Collections.sort(humanList, Comparator.comparing(Human::getYearOfBirth));
    }

    public void sortByName() {
        Collections.sort(humanList, Comparator.comparing(Human::getName));
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(humanList);
        }
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            humanList = (List<Human>) ois.readObject();
        }
    }
}