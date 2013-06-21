import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SetMergerTest {

    private static final long SEED = 5;

    private List one = new LinkedList();
    private List two = new LinkedList();

    private Random random = new Random(SEED);
    private static long[] duration = new long[7];

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 10000; ++i) {
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
        for (int i = 0; i < duration.length; ++i) {
            System.out.println(String.valueOf(i + 1) + duration[i]);
        }
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

    @Test
    public void testMergeSequences5() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences5(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[4] = endTime - startTime;
    }

    @Test
    public void testMergeSequences6() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences6(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[5] = endTime - startTime;
    }

    @Test
    public void testMergeSequences7() throws Exception {
        long startTime = System.nanoTime();

        SetMerger merger = new SetMerger();
        List result = merger.mergeSequences7(one, two);
        Set set = new HashSet(result);
        assert set.size() == result.size();

        long endTime = System.nanoTime();
        duration[6] = endTime - startTime;
    }
}