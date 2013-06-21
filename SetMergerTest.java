import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SetMergerTest {

    private static final long SEED = 5;

    private List one = new LinkedList();
    private List two = new LinkedList();

    private Random random = new Random(SEED);
    private static long[] duration = new long[4];

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 100000; ++i) {
            int num = random.nextInt();
            if (!one.contains(num)) {
                one.add(num);
            }
            num = random.nextInt();
            if (!two.contains(num)) {
                two.add(num);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("first: " + duration[0] +
                "\nsecond: " + duration[1] +
                "\nthird: " + duration[2] +
                "\nfourth: " + duration[3]);
    }

    @Test
    public void testMergeSequences1() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences1(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[0] = endTime - startTime;
    }

    @Test
    public void testMergeSequences2() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences2(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[1] = endTime - startTime;
    }

    @Test
    public void testMergeSequences3() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences3(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[2] = endTime - startTime;
    }

    @Test
    public void testMergeSequences4() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences4(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[3] = endTime - startTime;
    }
}