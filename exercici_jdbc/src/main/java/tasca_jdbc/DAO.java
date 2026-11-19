package tasca_jdbc;

import java.util.List;

public interface DAO<T> {
   boolean add(T item);
   List<T> getAll();
   List<T> getAllChosen(int item);
   // List<T> getAll(String filter);
   // boolean checkPass(String name, String pass);
   // delete / update
}
