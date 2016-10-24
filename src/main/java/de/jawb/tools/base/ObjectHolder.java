package de.jawb.tools.base;

/**
 * @author dit
 * @param <T>
 */
public class ObjectHolder<T> {

    private final T object;

    public ObjectHolder(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((object == null) ? 0 : object.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObjectHolder<?> other = (ObjectHolder<?>) obj;
        if (object == null) {
            if (other.object != null)
                return false;
        } else if (!object.equals(other.object))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ObjectHolder [object=" + object + "]";
    }

}
