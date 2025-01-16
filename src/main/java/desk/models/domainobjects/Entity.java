package main.java.desk.models.domainobjects;

public abstract class Entity<E> extends Object {

    protected abstract boolean runEquals(E other);

    @Override
    public boolean equals(Object obj) {
        var entity = (E)obj;
        if (entity.equals(null)) {
            return false;
        }

        return runEquals(entity);
    }

    protected void isNull(Object value) {
        if(value.equals(null)) {
            throw new DomainObjectException("nullです。");
        }
    }
}