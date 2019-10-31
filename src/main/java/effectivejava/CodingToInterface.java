package effectivejava;

import lombok.Data;

import java.util.List;

@Data
class Item {
    private String attribute;
}

interface DatabaseOperation{
    void save (Item item);
    void delete (Item item);
}

class MongoDBOperation implements DatabaseOperation{
    @Override
    public void save(Item item) {
        System.out.println("saving to mongodb");
    }

    @Override
    public void delete(Item item) {
        System.out.println("delete from mongodb");
    }
}

class CassandraOperation implements DatabaseOperation{
    @Override
    public void save(Item item) {
        System.out.println("saving to cassandra");
    }

    @Override
    public void delete(Item item) {
        System.out.println("delete cassandra");
    }
}

class Manager {
    DatabaseOperation databaseOperation;

    public void setDatabaseOperation(DatabaseOperation databaseOperation){
        this.databaseOperation = databaseOperation;
    }

    public void save(Item item){
        this.databaseOperation.save(item);
    }

    public void delete(Item item){
        this.databaseOperation.delete(item);
    }
}

interface SuperHeros {
    public List<String> getCharacters();

    static SuperHeros createDCSuperHeros() {
        return new DCSuperHeros();
    }

    static SuperHeros createMarvelSuperHeros() {
        return new MarvelSuperHeros();
    }
}

class DCSuperHeros implements SuperHeros {
    List<String> characters = List.of("Super Man", "Bat Man", "Wonder Woman", "Cyborg", "Flash");

    public List<String> getCharacters() {
        return characters;
    }
}


class MarvelSuperHeros implements SuperHeros {
    List<String> characters = List.of("Spider Man", "Captain America", "Daredevil", "Wolverine", "Iron Man");

    public List<String> getCharacters() {
        return characters;
    }
}


public class CodingToInterface {
    public static void main(String[] args) {

        Manager manager = new Manager();
        Item item = new Item();
        manager.setDatabaseOperation(new MongoDBOperation());
        manager.save(item);
        manager.delete(item);
        manager.setDatabaseOperation(new CassandraOperation());
        manager.save(item);
        manager.delete(item);


        SuperHeros marvelHeros = SuperHeros.createMarvelSuperHeros();
        SuperHeros dcHeros = SuperHeros.createDCSuperHeros();

    }
}
