import java.util.*;

public class SetMerger {

    public static List mergeSequences1(List lhs, List rhs) {
        lhs.addAll(rhs);
        Set set = new HashSet(lhs);
        return new LinkedList(set);
    }

    public static List mergeSequences2(List lhs, List rhs) {
        Set one = new HashSet(lhs);
        Set two = new HashSet(rhs);
        one.addAll(two);
        return new LinkedList(one);
    }

    public static List mergeSequences3(List lhs, List rhs) {
        Set set = new HashSet(lhs);
        set.addAll(rhs);
        return new LinkedList(set);
    }

    public List mergeSequences4(List lhs, List rhs) {
        FixedHashTable set = new FixedHashTable(lhs);
        for (Object entry : rhs) {
            if (set.contains(entry)) {
                continue;
            }
            lhs.add(entry);
        }
        return lhs;
    }

    private class SquareMemoryHashTable<E> {
        private static final long SEED = 5;
        private static final int PRIME = 96557;

        private E[] table;

        private int a;
        private int b;
        private Random random = new Random(SEED);

        public SquareMemoryHashTable(Collection<? extends E> c) {
            table = (E[])new Object[c.size() * c.size()];
            build(c);
        }

        public boolean contains(E entry) {
            int hash = hash(entry);
            if (table[hash] != null && (table[hash] == entry || table[hash].equals(entry))) {
                return true;
            }
            return false;
        }

        private void generateHashFunction() {
            a = 1 + random.nextInt() % (Integer.MAX_VALUE - 1);
            b = 1 + random.nextInt() % (Integer.MAX_VALUE - 1);
        }

        private int hash(E entry) {
            return Math.abs(((a * Math.abs(entry.hashCode()) + b) % PRIME) % table.length);
        }

        private boolean build(Collection<? extends E> c) {
            clearTable();
            generateHashFunction();
            for (E entry : c) {
                int hash = hash(entry);
                if (table[hash] != null) {
                    // There might be no tail recursion in Java, but anyway
                    return build(c);
                }
                table[hash] = entry;
            }
            return true;
        }

        private void clearTable() {
            int power = table.length;
            table = (E[])new Object[power];
        }
    }

    private class FixedHashTable<E> {
        private static final long SEED = 5;
        private static final int PRIME = 63949;

        private SquareMemoryHashTable<E>[] table;
        private LinkedList<E>[] tmpTable;

        private int a;
        private int b;
        private Random random = new Random(SEED);

        public FixedHashTable(Collection<? extends E> c) {
            table = new SquareMemoryHashTable[2 * c.size()];
            build(c);
        }

        public boolean contains(E entry) {
            int hash = hash(entry);
            if (table[hash] != null && table[hash].contains(entry)) {
                return true;
            }
            return false;
        }

        private void generateHashFunction() {
            a = 1 + random.nextInt() % (Integer.MAX_VALUE - 1);
            b = 1 + random.nextInt() % (Integer.MAX_VALUE - 1);
        }

        private int hash(E entry) {
            return Math.abs(((a * entry.hashCode() + b) % PRIME) % table.length);
        }

        private boolean build(Collection<? extends E> c) {
            initTables();
            generateHashFunction();
            for (E entry : c) {
                int hash = hash(entry);
                if (tmpTable[hash] == null) {
                    tmpTable[hash] = new LinkedList<E>();
                }
                tmpTable[hash].add(entry);
            }
            if (!isLengthSmallEnough()) {
                build(c);
                return true;
            }
            for (int i = 0; i < tmpTable.length; ++i) {
                LinkedList<E> entry = tmpTable[i];
                if (entry != null) {
                    table[i] = new SquareMemoryHashTable<E>(entry);
                }
            }
            tmpTable = null;
            return true;
        }

        private void initTables() {
            tmpTable = new LinkedList[table.length];
            table = new SquareMemoryHashTable[tmpTable.length];
        }

        private boolean isLengthSmallEnough() {
            int length = 0;
            for (LinkedList<E> list : tmpTable) {
                if (list == null) {
                    continue;
                }
                length = list.size() * list.size();
            }
            return length < 3 * table.length;
        }
    }
}
