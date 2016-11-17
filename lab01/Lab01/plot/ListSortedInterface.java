package plot;

public interface ListSortedInterface<T extends Comparable<T>> extends Iterable<T>
{

  public boolean isEmpty();
  public int size();
  public void add(T item) throws ListException;
  public void remove(T item) throws ListException;
  public void removeAll(); 

} 