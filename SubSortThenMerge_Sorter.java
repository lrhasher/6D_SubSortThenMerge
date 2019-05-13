/**
  Implement an insertion sort, in the Sorters structure
 */
import java.util.ArrayList;

public class SubSortThenMerge_Sorter extends Sorter {
    private ArrayList<String> localCopy;


    /**
      Construct an instance to process the user's data
     */
    public SubSortThenMerge_Sorter(  ArrayList< String> usersData) {
        super(usersData);
    }


    /**
      sort the user's data, implementing insertion sort
     */
    public void mySort() {
        mergeSort(0, elements.size());
    }

    private void mergeSort(int start , int end) {
        if( end - start > 1){
            int middle = (start + end) / 2;
            mergeSort(start , middle);
            mergeSort(middle , end);
            merge(start, middle, end);
            }
    }

    private void merge(
      // indexes of sub-list boundaries in usersData; see README
        int start0  // index of first item in list0
      , int start1  // index of first item in list1
                    // = just past end of list0
      , int end1    // index past end of list1
      ) {
        /* copy the user's data, so that its two lists
         can be merged into usersData */
        localCopy = new ArrayList<String>(elements);
        mergeRange( start0
                  , 0, start1 - start0
                  , start1 - start0, end1 - start0);
    }


    /**
      problem: Merge the user data from the given range in localCopy
        into the usersData.
     recursive abstraction: When I am asked to {problem statement},
       the recursive abstraction can merge the results of a range
       that is one item smaller.
     */
    private void mergeRange(
        int target // destination in usersData. Probably redundant.

        // boundaries of lists in localCopy, NOT usersData!
      , int localStart0  // index of first item in list0
      , int localEnd0    // just past end of list0
      , int localStart1  // index of first item in list1
      , int localEnd1    // just past end of list0
      ) {
          System.out.println(localCopy);

        // temp for debugging
        System.out.println(
            " target = "      + target
          + " localStart0 = " + localStart0
          + " localEnd0 = "   + localEnd0
          + " localStart1 = " + localStart1
          + " localEnd1 = "   + localEnd1
          );

        if( // both ranges are exhausted
            localStart0 == localEnd0 && localStart1 == localEnd1
          )
            // solution to base case
            return;  // merge is done
        else{ // there is at least 1 item remaining to merge
            if( // list0 exhausted
                localStart0 == localEnd0
              )
               // take an item from list1
                elements.set( target++, localCopy.get( localStart1++));

            // similarly for exhausted list1
            else if( localStart1 == localEnd1)
                elements.set( target++, localCopy.get( localStart0++));

            else // items remain in both lists
                // copy the smaller item
                if( localCopy.get( localStart0).compareTo(
                    localCopy.get( localStart1)) < 0)
                    elements.set( target++, localCopy.get( localStart0++));
                else
                    elements.set( target++, localCopy.get( localStart1++));
            mergeRange( target, localStart0, localEnd0, localStart1, localEnd1);
        }

    }


}
