package utils;

/**
 *
 * @author nateesun
 */

interface ArrayDiff<T> {
    public T[] diffInsertUpdate(T[] arrA, T[] arrB);
    public String foundStatus(T m1, T[] arrB);
    public T[] getLocalDiff(T[] apiMember, T[] memberLocalList);
}
