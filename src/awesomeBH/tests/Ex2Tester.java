package awesomeBH.tests;

import awesomeBH.BinomialHeap;


public class Ex2Tester
{
	final private static int SCENARIO_NUM = 8;
	final private static int TEST_NUM = 36;
	private static int testCount = 0;
	private static int successCount = 0;
	private static int lastTestInPrevScen = 0;
	private static int prevScen = 0;
	
	private static void performTest(boolean success, int scenario) {

		successCount += (success ? 1 : 0);

		if (scenario != prevScen) {
			lastTestInPrevScen = testCount;
			prevScen = scenario;
		}
		
		testCount++;
		
		if (success)
		{
			System.out.println("Success on test "+(testCount - lastTestInPrevScen)+" in scenario "+scenario); 
		}
		else
		{
			System.out.println("Failure on test "+(testCount - lastTestInPrevScen)+" in scenario "+scenario); 
		}
		
	}
	
	public static boolean treeSizeCheck(BinomialHeap heap, boolean isAfterDelete) {
		int[] sizes = heap.treesSize();
		int sum=0;
		//System.out.println("++++++++++++++++++++++++++++++++++++++++");
		for (int i=0; i<sizes.length; i++)
		{
			if (i!=sizes.length-1) {
				if (sizes[i] > sizes[i+1])
					return false;
				if (isAfterDelete && sizes[i] == sizes[i+1])
					return false;
			}
			if ((sizes[i] & sizes[i]-1)!=0)
				return false;
			sum+=sizes[i];
			
			//System.out.println("" + sizes[i]+" ");
			
		}
		//System.out.println("++++++++++++++++++++++++++++++++++++++++");
		return heap.size()==sum;
	}
	
	
	public static void main(String[] args)
	{		
		BinomialHeap heap = null;
		BinomialHeap heap2 = null;
		
		// create array of values between 700-1700
		// like this - 700, 702, 702, 704, 704, 706, 706 ...
		int[] values = new int[1000];
		for (int j=0; j<values.length; j++)
		{
			values[j] = 700 + j + j%2;
		}
		
		// hide some minimum value in it
		int hiddenMin = 113;
		values[values.length / 2] = hiddenMin;
		
		// create array of values 20,19,18,....,1
		int[] values2 = new int[20];
		for (int j=0; j<values2.length; j++)
		{
			values2[j] = values2.length - j;
		}
		
		// create custom array of values
		int[] values3 = new int[] {6,1,19,17,9,2,10,12,18,
				5,15,11,4,16,13,20,7,3,8,14};

		
		//int testCount = 0;
		int prevLen = 0;
		for (int i=0; i<SCENARIO_NUM; i++)
		{
			try
			{
				if (i==0)
				{
					// First test - Create a new heap and check if it's valid
					heap = new BinomialHeap();
					
					performTest(heap.isValid(),i);
					performTest(heap.empty(),i);
					performTest(treeSizeCheck(heap, true),i);
				}
				else if (i==1)
				{
					// Second test - Insert values and check if the heap is still valid and the minimum makes sense
					for (int value : values)
					{
						heap.insert(value);
					}

					performTest(heap.isValid(),i);
					performTest(heap.size() == values.length,i);
					performTest(heap.findMin() == hiddenMin,i);
					performTest(treeSizeCheck(heap, false),i);
				}
				else if (i==2)
				{
					// Third test - Delete the minimum multiple times and see if the heap is still valid and the minimum is updated
					heap.deleteMin();

					performTest(heap.isValid(),i);
					performTest(heap.size() == values.length-1,i);
					performTest(heap.findMin() == 700,i);
					performTest(treeSizeCheck(heap, true),i);
					
					heap.deleteMin();

					performTest(heap.isValid(),i);
					performTest(heap.size() == values.length-2,i);
					performTest(heap.findMin() == 702,i);
					performTest(treeSizeCheck(heap, true),i);
					
					heap.deleteMin();

					performTest(heap.isValid(),i);
					performTest(heap.size() == values.length-3,i);
					performTest(heap.findMin() == 702,i);
					performTest(treeSizeCheck(heap, true),i);
					
					heap.deleteMin();

					performTest(heap.isValid(),i);
					performTest(heap.size() == values.length-4,i);
					performTest(heap.findMin() == 704,i);
					performTest(treeSizeCheck(heap, true),i);
					
					prevLen = heap.size();
				}
				else if (i==3)
				{
					// 4th test - Insert values and check the heap's consistency
					int n = 0;
					for (int value : values3)
					{
						heap.insert(value);
						if (!heap.isValid() || heap.empty() || !treeSizeCheck(heap, false))
						{
							n++;
						}
					}
					
					performTest(n == 0 && heap.isValid(),i);
					performTest(heap.size() == (prevLen + values2.length),i);					
				}
				else if (i==4 || i==6)
				{
					// 5th and 7th test - multiple deletion of minimum values
					int n = 0;
					for (int j=(values2.length-1); j>=0; j--)
					{
						prevLen = heap.size();
						if (!heap.isValid() || heap.findMin()!=values2[j])
						{
							n++;
						}
						heap.deleteMin();
						if (!heap.isValid() || heap.size()!=(prevLen-1) || !treeSizeCheck(heap, true))
						{
							n++;
						}
					}
					
					// Check if the heap is intact
					performTest(n == 0,i);
					
					// Check if the minimum makes sense
					performTest(heap.findMin() == 704,i);
					
					prevLen = heap.size();
				}
				else if (i==5)
				{
					// 6th test - meld heaps
					heap = new BinomialHeap();
					heap2 = new BinomialHeap();
					for (int j=0; j<values3.length; j++)
					{
						if (j%2 == 0)
						{
							if (j>=3 && values[j] != hiddenMin)
							{
								heap.insert(values[j]);
							}
							heap2.insert(values3[j]);
						}
						else
						{
							if (j>=3 && values[j] != hiddenMin)
							{
								heap2.insert(values[j]);
							}
							heap.insert(values3[j]);
						}
					}
					for (int j=values3.length; j<values.length; j++)
					{
						if (j>=3 && values[j] != hiddenMin)
						{
							heap.insert(values[j]);
						}
					}
					heap2.meld(heap);
					
					performTest(heap2.isValid(),i);
					performTest(heap2.size() == (values3.length + values.length - 4),i);
					performTest(treeSizeCheck(heap2, false),i);
											
					heap = heap2;
				}
				else if (i==7)
				{ 	
					// 8th test - empty heap
					while (heap.size() != 0)
					{
						heap.deleteMin();
					}
					
					performTest(heap.isValid(),i);
					performTest(heap.empty(),i);
					performTest(heap.size() == 0,i);
					performTest(treeSizeCheck(heap, true),i);
					
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception Caught On Test" + i + " : "+e);
			}
		}

		System.out.println("=========================================");
		System.out.println("Total : " + successCount + "/" + TEST_NUM);
		System.out.println("Grade : " + (float) 100 * successCount / TEST_NUM);
	}
	

}
