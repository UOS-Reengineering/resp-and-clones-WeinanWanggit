package example.project.CodeClones;

import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity {

    public static double computeJaccardSimilarity(Set<String> set1, Set<String> set2) {
        Set<String> union = new HashSet<String>(set1);
        union.addAll(set2);

        Set<String> intersection = new HashSet<String>(set1);
        intersection.retainAll(set2);

        return (double) intersection.size() / (double) union.size();
    }



}
